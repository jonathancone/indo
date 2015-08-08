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
 * Created by jcone on 8/7/15.
 */
public class SqlConfig {

    public static final String PROP_JDBC_URL = "jdbc.url";
    public static final String PROP_JDBC_USER = "jdbc.user";
    public static final String PROP_JDBC_PASSWORD = "jdbc.password";

    private DataSource dataSource;
    private Connection connection;
    private Properties properties;

    public SqlConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public SqlConfig(Properties properties) {
        this.properties = properties;
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

            }
            connection = Quietly.getConnection(dataSource);
        }
        return null;
    }

    private String getSystemJdbcUser() {
        return System.getProperty(PROP_JDBC_USER);
    }

    private String getSystemJdbcPassword() {
        return System.getProperty(PROP_JDBC_PASSWORD);
    }

    private String getSystemJdbcUrl() {
        return System.getProperty(PROP_JDBC_URL);
    }

    private String getJdbcUser() {
        return properties.getProperty(PROP_JDBC_USER);
    }

    private String getJdbcPassword() {
        return properties.getProperty(PROP_JDBC_PASSWORD);
    }

    private String getJdbcUrl() {
        return properties.getProperty(PROP_JDBC_URL);
    }
}
