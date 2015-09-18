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

package indo.jdbc;

import indo.util.Unchecked;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Utility methods for working with {@link DataSource} objects without throwing checked {@link SQLException}s.
 *
 * @author Jonathan Cone
 * @see DataSource
 */
public class DataSources {
    public static Connection getConnection(DataSource ds) {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Connection getConnection(DataSource ds, String username, String password) {
        try {
            return ds.getConnection(username, password);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static PrintWriter getLogWriter(DataSource ds) {
        try {
            return ds.getLogWriter();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setLogWriter(DataSource ds, PrintWriter out) {
        try {
            ds.setLogWriter(out);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getLoginTimeout(DataSource ds) {
        try {
            return ds.getLoginTimeout();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setLoginTimeout(DataSource ds, int seconds) {
        try {
            ds.setLoginTimeout(seconds);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Logger getParentLogger(DataSource ds) {
        try {
            return ds.getParentLogger();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static <T> T unwrap(DataSource ds, Class<T> iface) {
        try {
            return ds.unwrap(iface);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isWrapperFor(DataSource ds, Class<?> iface) {
        try {
            return ds.isWrapperFor(iface);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }
}
