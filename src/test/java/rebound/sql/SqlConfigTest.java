/*
 * Copyright (c) 2015 Rebound Contributors
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

package rebound.sql;

import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * Created by jcone on 8/8/15.
 */
public class SqlConfigTest {

    @Test(expected = NullPointerException.class)
    public void testDataSourceConstructor() throws Exception {
        new SqlConfig((DataSource) null);
    }

    @Test(expected = NullPointerException.class)
    public void testPropertiesConstructor() throws Exception {
        new SqlConfig((Properties) null);
    }

    @Test(expected = NullPointerException.class)
    public void testConnectionConstructor() throws Exception {
        new SqlConfig((Connection) null);
    }

    @Test
    public void testGetSystemJdbcUser() throws Exception {

        System.setProperty(SqlConfig.PROP_JDBC_USER, "value1");

        assertEquals(new SqlConfig(new Properties()).getSystemJdbcUser(), System.getProperty(SqlConfig.PROP_JDBC_USER));
    }

    @Test
    public void testGetSystemJdbcPassword() throws Exception {

        System.setProperty(SqlConfig.PROP_JDBC_PASSWORD, "value2");

        assertEquals(new SqlConfig(new Properties()).getSystemJdbcPassword(), System.getProperty(SqlConfig.PROP_JDBC_PASSWORD));
    }

    @Test
    public void testGetSystemJdbcUrl() throws Exception {

        System.setProperty(SqlConfig.PROP_JDBC_URL, "value3");

        assertEquals(new SqlConfig(new Properties()).getSystemJdbcUrl(), System.getProperty(SqlConfig.PROP_JDBC_URL));
    }

    @Test
    public void testGetJdbcUser() throws Exception {

        Properties properties = new Properties();

        properties.setProperty(SqlConfig.PROP_JDBC_USER, "value1");

        assertEquals(new SqlConfig(properties).getJdbcUser(), properties.getProperty(SqlConfig.PROP_JDBC_USER));

    }

    @Test
    public void testGetJdbcPassword() throws Exception {
        Properties properties = new Properties();

        properties.setProperty(SqlConfig.PROP_JDBC_PASSWORD, "value2");

        assertEquals(new SqlConfig(properties).getJdbcPassword(), properties.getProperty(SqlConfig.PROP_JDBC_PASSWORD));

    }

    @Test
    public void testGetJdbcUrl() throws Exception {
        Properties properties = new Properties();

        properties.setProperty(SqlConfig.PROP_JDBC_URL, "value3");

        assertEquals(new SqlConfig(properties).getJdbcUrl(), properties.getProperty(SqlConfig.PROP_JDBC_URL));

    }

}