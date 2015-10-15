/*
 * Copyright 2015 Indo Contributors
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

package indo.sql;

import indo.jdbc.DataSources;
import indo.sql.config.DatabaseConfig;
import indo.util.Strings;
import indo.util.Unchecked;
import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.TypeCastException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public abstract class DbTest {

    @Rule
    public TestName testName = new TestName();

    @Parameterized.Parameter(0)
    public String configuration;

    private DatabaseConfig dbConfig;

    @Parameterized.Parameters(name = "{0}")
    public static List<Object> dataSourceConfigurations() {
        return Arrays.asList(
                "h2-case-insensitive",
                "h2-case-sensitive",
                "postgres"
        );
    }

    private void x() {

        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream(configuration));


        } catch (IOException e) {
            throw Unchecked.exception(e);
        }

    }

    @Before
    public void setupDataSource() {
        getDbConfig().populateSchema(classBeforeDataSetName(), methodBeforeDataSetName());
    }

    @After
    public void tearDownDataSource() {
        getDbConfig().assertSchema(classAfterDataSetName(), methodAfterDataSetName());
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
        return Strings.before(testName.getMethodName(), '[');
    }

    protected DataSource ds() {
        return getDataSource();
    }

    protected Connection con() {
        return getConnection();
    }

    protected DataSource getDataSource() {
        return getDbConfig().getDataSource();
    }

    protected Connection getConnection() {
        return DataSources.getConnection(getDbConfig().getDataSource());
    }

    protected <T, R> void assertEqualsRowValue(String expectedTable, String expectedColumn, List<T> results, Function<T, R> getterFunction) {

        for (int i = 0; i < results.size(); i++) {
            assertEqualsRowValue(expectedTable, expectedColumn, i, results, getterFunction);
        }
    }

    protected <T, R> void assertEqualsRowValue(String expectedTable, String expectedColumn, int expectedRow, List<T> results, Function<T, R> getterFunction) {
        assertEqualsRowValue(expectedTable, expectedColumn, expectedRow, getterFunction.apply(results.get(expectedRow)));
    }

    protected void assertEqualsRowValue(String expectedTable, String expectedColumn, int expectedRow, Object actual) {
        Object expected = getDbConfig().getValue(expectedTable, expectedColumn, expectedRow);

        DataType dataType = DataType.forObject(actual);

        try {
            assertEquals("The expected column value didn't match the actual value.", dataType.typeCast(expected), actual);
        } catch (TypeCastException e) {
            throw Unchecked.exception(e);
        }
    }

    protected DatabaseConfig getDbConfig() {
        return dbConfig;
    }
}
