/*
 * Copyright 2017 Indo Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package indo.sql.test;

import indo.example.RowIdentity;
import indo.jdbc.DataSources;
import indo.log.Logger;
import indo.util.Strings;
import indo.util.Unchecked;
import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.TypeCastException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Stream;

import static indo.log.Logger.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class DbTest {

    protected String configuration;
    private TestInfo testInfo;
    private DatabaseConfiguration dbConfig;
    private boolean isSetup = false;

    // Container registry: maps configuration name to container config
    private static final Map<String, DatabaseContainerConfig> CONTAINER_REGISTRY = new ConcurrentHashMap<>();

    static {
        // Register container configurations for each database type
        registerContainer("h2-case-insensitive", new H2ContainerConfig());
        registerContainer("h2-case-sensitive", new H2ContainerConfig());
        registerContainer("postgres", new PostgresContainerConfig());
        registerContainer("mysql", new MySqlContainerConfig());
    }

    /**
     * Registers a container configuration for a specific database configuration name.
     *
     * @param configName the configuration name (e.g., "postgres", "mysql")
     * @param containerConfig the container configuration
     */
    protected static void registerContainer(String configName, DatabaseContainerConfig containerConfig) {
        CONTAINER_REGISTRY.put(configName, containerConfig);
        info(DbTest.class, "Registered container config for: %s", configName);
    }

    public static Stream<String> dataSourceConfigurations() {
        return Stream.of(
                "h2-case-insensitive",
                "h2-case-sensitive",
                "postgres",
                "mysql"
        );
    }

    @BeforeEach
    public void captureTestInfo(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    private void ensureSetup() {
        if (!isSetup && configuration != null) {
            try {
                if (dbConfig == null) {
                    // Start container if one is registered for this configuration
                    DatabaseContainerConfig containerConfig = CONTAINER_REGISTRY.get(configuration);
                    if (containerConfig != null) {
                        info(this, "Starting container for configuration: %s", configuration);
                        containerConfig.start();
                    }

                    // Load properties from file
                    Properties properties = new Properties();
                    properties.load(getClass().getResourceAsStream("test/" + configuration + ".properties"));

                    // Override with container connection details if available
                    if (containerConfig != null) {
                        info(this, "Applying container connection details for: %s", configuration);
                        containerConfig.applyToProperties(properties);
                    }

                    dbConfig = DatabaseConfiguration.create(properties);
                    dbConfig.createDataSource();
                    dbConfig.createSchema();
                }

                dbConfig.populateSchema(classBeforeDataSetName(), methodBeforeDataSetName());
                isSetup = true;
            } catch (IOException e) {
                throw Unchecked.exception(e);
            }
        }
    }

    @AfterEach
    public void tearDownDataSource() {
        if (isSetup && dbConfig != null) {
            dbConfig.assertSchema(classAfterDataSetName(), methodAfterDataSetName());
            isSetup = false;
        }
    }

    protected String classBeforeDataSetName() {
        return fullyQualifiedPath(getClass().getSimpleName() + "-before.xml");
    }

    protected String classAfterDataSetName() {
        return fullyQualifiedPath(getClass().getSimpleName() + "-after.xml");
    }

    protected String methodBeforeDataSetName() {
        return fullyQualifiedPath(getClass().getSimpleName() + "-" + getSimpleTestMethodName() + "-before.xml");
    }

    protected String methodAfterDataSetName() {
        return fullyQualifiedPath(getClass().getSimpleName() + "-" + getSimpleTestMethodName() + "-after.xml");
    }

    protected String fullyQualifiedPath(String dataSetName) {
        URL url = getClass().getResource(dataSetName);
        return url != null ? url.toString() : dataSetName;
    }

    protected String getSimpleTestMethodName() {
        return Strings.before(testInfo.getTestMethod().map(m -> m.getName()).orElse(""), '[');
    }

    protected DataSource dataSource() {
        ensureSetup();
        return getDataSource();
    }

    protected Connection con() {
        ensureSetup();
        return getConnection();
    }

    protected DataSource getDataSource() {
        return getDbConfig().getDataSource();
    }

    protected Connection getConnection() {
        return DataSources.getConnection(getDbConfig().getDataSource());
    }

    protected <T, R> void assertEqualsRowValue(String expectedTable, String expectedColumn, List<? extends RowIdentity> results, Function<T, R> getterFunction) {
        for (int i = 0; i < results.size(); i++) {
            assertEqualsRowValue(expectedTable, expectedColumn, i, results.get(i).getRowId().intValue(), results, getterFunction);
        }
    }

    protected <T, R> void assertEqualsRowValue(String expectedTable, String expectedColumn, int offset, int expectedRow, List<? extends RowIdentity> results, Function<T, R> getterFunction) {
        assertEqualsRowValue(expectedTable, expectedColumn, expectedRow, getterFunction.apply((T) results.get(offset)));
    }

    protected void assertEqualsRowValue(String expectedTable, String expectedColumn, int expectedRow, Object actual) {
        Object expected = getDbConfig().getValue(expectedTable, expectedColumn, expectedRow);

        DataType dataType = DataType.forObject(actual);
        try {
            Object o = dataType.typeCast(expected);

            assertEquals(o, actual,
                    String.format("The expected column value (%s.%s) didn't match the actual value.", expectedTable, expectedColumn));

            Logger.debug(this, "Expected %s, returned %s", o, actual);
        } catch (TypeCastException e) {
            throw Unchecked.exception(e);
        }
    }

    protected DatabaseConfiguration getDbConfig() {
        return dbConfig;
    }
}
