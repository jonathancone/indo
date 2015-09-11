/*
 * Copyright 2015  Jonathan Cone
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

import indo.util.Strings;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class AbstractDbUnitTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractDbUnitTest.class);

    @Rule
    public TestName testName = new TestName();

    @Parameterized.Parameter(0)
    public AbstractDbUnitConfigurer dbUnitConfigurer;

    @Parameterized.Parameters
    public static List<Object[]> dataSourceConfigurations() {
        Object[][] configs = new Object[][]{
                {new H2DbUnitConfigurer("sa", "sa", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=true", "default-schema.sql", "org.h2.Driver")}
        };

        return Arrays.asList(configs);
    }

    @Before
    public void setupDataSource() {
        dbUnitConfigurer.populateSchema(beforeDataSetName());
    }

    @After
    public void tearDownDataSource() {
        dbUnitConfigurer.assertSchema(afterDataSetName());
    }

    protected String beforeDataSetName() {
        return fullyQualifiedPath(getClass().getSimpleName() + "-" + getSimpleTestMethodName() + "-before.xml");
    }

    protected String afterDataSetName() {
        return fullyQualifiedPath(getClass().getSimpleName() + "-" + getSimpleTestMethodName() + "-after.xml");
    }

    protected String fullyQualifiedPath(String dataSetName) {
        URL url = getClass().getResource(dataSetName);
        return url != null ? url.toString() : dataSetName;
    }

    protected String getSimpleTestMethodName() {
        return Strings.before(testName.getMethodName(), '[');
    }

    protected DataSource getDataSource() {
        return dbUnitConfigurer.getDataSource();
    }

    protected void assertRowValue(String expectedTable, String expectedColumn, int expectedRow, Object actual) {
        Object expected = dbUnitConfigurer.getValue(expectedTable, expectedColumn, expectedRow);
        Assert.assertEquals(expected, actual);
    }

    protected void log(String message, Object... args) {
        LoggerFactory.getLogger(getClass()).debug(message, args);
    }
}
