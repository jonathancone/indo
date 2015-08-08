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

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * This class is used to configure connections to the SQL {@link javax.sql.DataSource}.  New instances are created
 * using one of the supplied constructors.  After construction, you can call the {@link #create()} method to obtain
 * an instance of the {@link SqlSession}.
 * <br>
 * <br>
 * <code>
 * // Use a javax.sql.DataSource for connection pooling, transactions, etc.<br>
 * SqlSession sql = new SqlConfig(dataSource).create();<br>
 * <br>
 * // Use java.util.Properties to configure datasource<br>
 * SqlSession sql = new SqlConfig(properties).create();<br>
 * <br>
 * // Use an existing java.sql.Connection<br>
 * SqlSession sql = new SqlConfig(connection).create();<br>
 * <br>
 * // Quick and dirty using JDBC URL<br>
 * SqlSession sql = new SqlConfig("jdbc://...", "user", "password", ...).create();
 * </code>
 */
public class SqlConfig {

    public static final String PROP_JDBC_URL = "jdbc.url";
    public static final String PROP_JDBC_USER = "jdbc.user";
    public static final String PROP_JDBC_PASSWORD = "jdbc.password";
    public static final String PROP_JDBC_DATASOURCE_CLASS = "jdbc.datasource.class";

    private DataSource dataSource;
    private Connection connection;
    private Properties properties;

    public SqlConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public SqlConfig(Properties properties) {
        this.properties = properties;
    }

    public SqlConfig(String url, String username, String password, String dataSourceClass) {
        this.properties = new Properties();
        this.properties.setProperty(PROP_JDBC_USER, username);
        this.properties.setProperty(PROP_JDBC_PASSWORD, password);
        this.properties.setProperty(PROP_JDBC_URL, url);
        this.properties.setProperty(PROP_JDBC_DATASOURCE_CLASS, dataSourceClass);
    }

    public SqlConfig(Connection connection) {
        this.connection = connection;
    }

    public SqlSession create() {
        if (connection == null) {
            if (dataSource == null) {
                if (properties == null) {
                    throw new UnexpectedSqlException("No data source connection properties were supplied. Consult the documentation on how to configure a data source.");
                }
                dataSource = createDataSource(properties);
            }
        }
        return new StandardSqlSession(dataSource);
    }

    protected DataSource createDataSource(Properties properties) {
        if (connection == null) {
            return new SingleConnectionDataSource(getJdbcUser(properties), getJdbcPassword(properties), getJdbcUrl(properties));
        } else {
            return new SingleConnectionDataSource(connection);
        }
    }

    protected String getSystemJdbcUser() {
        return System.getProperty(PROP_JDBC_USER);
    }

    protected String getSystemJdbcPassword() {
        return System.getProperty(PROP_JDBC_PASSWORD);
    }

    protected String getSystemJdbcUrl() {
        return System.getProperty(PROP_JDBC_URL);
    }

    protected String getSystemJdbcDataSourceClass() {
        return System.getProperty(PROP_JDBC_DATASOURCE_CLASS);
    }

    protected String getJdbcUser(Properties properties) {
        return properties.getProperty(PROP_JDBC_USER, getSystemJdbcUser());
    }

    protected String getJdbcPassword(Properties properties) {
        return properties.getProperty(PROP_JDBC_PASSWORD, getSystemJdbcPassword());
    }

    protected String getJdbcUrl(Properties properties) {
        return properties.getProperty(PROP_JDBC_URL, getSystemJdbcUrl());
    }

    protected String getJdbcDataSourceClass(Properties properties) {
        return properties.getProperty(PROP_JDBC_DATASOURCE_CLASS, getSystemJdbcDataSourceClass());
    }
}
