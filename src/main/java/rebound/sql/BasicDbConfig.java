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
 * an instance of the {@link SqlWorker}.
 * <br>
 * <br>
 * <code>
 * // Use a javax.sql.DataSource for connection pooling, transactions, etc.<br>
 * SqlWorker sql = new BasicDbConfig(dataSource).create();<br>
 * <br>
 * // Use java.util.Properties to configure datasource<br>
 * SqlWorker sql = new BasicDbConfig(properties).create();<br>
 * <br>
 * // Use an existing java.sql.Connection<br>
 * SqlWorker sql = new BasicDbConfig(connection).create();<br>
 * <br>
 * // Quick and dirty using JDBC URL<br>
 * SqlWorker sql = new BasicDbConfig("jdbc://...", "user", "password", ...).create();
 * </code>
 */
public class BasicDbConfig implements DbConfig {

    public static final String PROP_JDBC_URL = "jdbc.url";
    public static final String PROP_JDBC_USER = "jdbc.user";
    public static final String PROP_JDBC_PASSWORD = "jdbc.password";

    private DataSource dataSource;
    private Connection connection;
    private Properties properties;

    public BasicDbConfig(DataSource dataSource) {
        checkArguments("DataSource", dataSource);
        this.dataSource = dataSource;
    }

    public BasicDbConfig(Properties properties) {
        checkArguments("Properties", properties);
        this.properties = properties;
    }

    public BasicDbConfig(String url, String username, String password) {
        checkArguments("String (url)", url);
        checkArguments("String (username)", username);
        checkArguments("String (password)", password);
        this.properties = constructProperties(url, username, password);
    }

    public BasicDbConfig(Connection connection) {
        checkArguments("Connection", connection);
        this.connection = connection;
    }

    private void checkArguments(String name, Object value) {
        if (value == null) {
            throw new NullPointerException(String.format("Cannot create %s instance, a null %s reference was supplied.", getClass().getName(), name));
        }
    }

    protected Properties constructProperties(String url, String username, String password) {
        Properties properties = new Properties();
        properties.setProperty(PROP_JDBC_USER, username);
        properties.setProperty(PROP_JDBC_PASSWORD, password);
        properties.setProperty(PROP_JDBC_URL, url);
        return properties;
    }

    public SqlWorker create() {
        if (connection == null) {
            if (dataSource == null) {
                if (properties == null) {
                    throw new NullPointerException("No data source connection properties were supplied. Consult the documentation on how to configure a data source.");
                }
                dataSource = createDataSource(properties);
            }
        }
        return new BasicSqlWorker(dataSource);
    }

    protected DataSource createDataSource(Properties properties) {
        if (connection == null) {
            return new SingleConnectionDataSource(getJdbcUser(), getJdbcPassword(), getJdbcUrl());
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

    protected String getJdbcUser() {
        return properties.getProperty(PROP_JDBC_USER, getSystemJdbcUser());
    }

    protected String getJdbcPassword() {
        return properties.getProperty(PROP_JDBC_PASSWORD, getSystemJdbcPassword());
    }

    protected String getJdbcUrl() {
        return properties.getProperty(PROP_JDBC_URL, getSystemJdbcUrl());
    }
}
