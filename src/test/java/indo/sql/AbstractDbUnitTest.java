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
import java.net.URL;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AbstractDbUnitTest {

    @Rule
    public TestName testName = new TestName();

    @Parameterized.Parameter(0)
    public String dbName;

    @Parameterized.Parameter(1)
    public AbstractDbUnitConfigurer dbUnitConfigurer;

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> dataSourceConfigurations() {
        Object[][] configs = new Object[][]{
                {"H2 Database (Case-insensitive)", new H2DbUnitConfigurer("sa", "sa", "jdbc:h2:mem:testcasei;DB_CLOSE_DELAY=-1", "h2-default-schema.sql", "org.h2.Driver", false)},
                //        {"H2 Database (Case-sensitive)", new H2DbUnitConfigurer("sa", "sa", "jdbc:h2:mem:testcases;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false", "h2-default-schema.sql", "org.h2.Driver", true)}
        };

        return Arrays.asList(configs);
    }

    @Before
    public void setupDataSource() {
        dbUnitConfigurer.populateSchema(classBeforeDataSetName(), methodBeforeDataSetName());
    }

    @After
    public void tearDownDataSource() {
        dbUnitConfigurer.assertSchema(classAfterDataSetName(), methodAfterDataSetName());
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
        return dbUnitConfigurer.getDataSource();
    }

    protected Connection getConnection() {
        return DataSources.getConnection(dbUnitConfigurer.getDataSource());
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
        Object expected = dbUnitConfigurer.getValue(expectedTable, expectedColumn, expectedRow);

        DataType dataType = DataType.forObject(actual);

        try {
            assertEquals("The expected column value didn't match the actual value.", dataType.typeCast(expected), actual);
        } catch (TypeCastException e) {
            throw Unchecked.exception(e);
        }
    }
}
