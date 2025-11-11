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

    private TestInfo testInfo;

    // Maps configuration name to DatabaseConfiguration instance
    private static final Map<String, DatabaseConfiguration> DB_CONFIGS = new ConcurrentHashMap<>();

    // Tracks which configurations have been set up for the current test
    private final Map<String, Boolean> setupStatus = new ConcurrentHashMap<>();

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

    private void ensureSetup(String configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("Configuration cannot be null");
        }

        if (setupStatus.getOrDefault(configuration, false)) {
            return; // Already set up for this test
        }

        try {
            DatabaseConfiguration dbConfig = DB_CONFIGS.computeIfAbsent(configuration, config -> {
                try {
                    // Start container if one is registered for this configuration
                    DatabaseContainerConfig containerConfig = CONTAINER_REGISTRY.get(config);
                    if (containerConfig != null) {
                        info(this, "Starting container for configuration: %s", config);
                        containerConfig.start();
                    }

                    // Load properties from file
                    Properties properties = new Properties();
                    properties.load(getClass().getResourceAsStream("test/" + config + ".properties"));

                    // Override with container connection details if available
                    if (containerConfig != null) {
                        info(this, "Applying container connection details for: %s", config);
                        containerConfig.applyToProperties(properties);
                    }

                    DatabaseConfiguration dbConfiguration = DatabaseConfiguration.create(properties);
                    dbConfiguration.createDataSource();
                    dbConfiguration.createSchema();
                    return dbConfiguration;
                } catch (IOException e) {
                    throw Unchecked.exception(e);
                }
            });

            dbConfig.populateSchema(classBeforeDataSetName(), methodBeforeDataSetName());
            setupStatus.put(configuration, true);
        } catch (Exception e) {
            throw Unchecked.exception(e);
        }
    }

    @AfterEach
    public void tearDownDataSource() {
        // Assert schema for each configuration that was set up in this test
        setupStatus.forEach((config, wasSetup) -> {
            if (wasSetup) {
                DatabaseConfiguration dbConfig = DB_CONFIGS.get(config);
                if (dbConfig != null) {
                    dbConfig.assertSchema(classAfterDataSetName(), methodAfterDataSetName());
                }
            }
        });
        // Clear setup status for next test
        setupStatus.clear();
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

    protected DataSource dataSource(String configuration) {
        ensureSetup(configuration);
        return getDataSource(configuration);
    }

    protected Connection con(String configuration) {
        ensureSetup(configuration);
        return getConnection(configuration);
    }

    protected DataSource getDataSource(String configuration) {
        return getDbConfig(configuration).getDataSource();
    }

    protected Connection getConnection(String configuration) {
        return DataSources.getConnection(getDbConfig(configuration).getDataSource());
    }

    protected <T, R> void assertEqualsRowValue(String configuration, String expectedTable, String expectedColumn, List<? extends RowIdentity> results, Function<T, R> getterFunction) {
        for (int i = 0; i < results.size(); i++) {
            assertEqualsRowValue(configuration, expectedTable, expectedColumn, i, results.get(i).getRowId().intValue(), results, getterFunction);
        }
    }

    protected <T, R> void assertEqualsRowValue(String configuration, String expectedTable, String expectedColumn, int offset, int expectedRow, List<? extends RowIdentity> results, Function<T, R> getterFunction) {
        assertEqualsRowValue(configuration, expectedTable, expectedColumn, expectedRow, getterFunction.apply((T) results.get(offset)));
    }

    protected void assertEqualsRowValue(String configuration, String expectedTable, String expectedColumn, int expectedRow, Object actual) {
        Object expected = getDbConfig(configuration).getValue(expectedTable, expectedColumn, expectedRow);

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

    protected DatabaseConfiguration getDbConfig(String configuration) {
        return DB_CONFIGS.get(configuration);
    }
}
