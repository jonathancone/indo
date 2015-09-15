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

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by jcone on 9/14/15.
 */
public class Connections {
    private Connections() {
    }

    public static Statement createStatement(Connection c) {
        try {
            return c.createStatement();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static PreparedStatement prepareStatement(Connection c, String sql) {
        try {
            return c.prepareStatement(sql);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static CallableStatement prepareCall(Connection c, String sql) {
        try {
            return c.prepareCall(sql);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String nativeSQL(Connection c, String sql) {
        try {
            return c.nativeSQL(sql);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setAutoCommit(Connection c, boolean autoCommit) {
        try {
            c.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean getAutoCommit(Connection c) {
        try {
            return c.getAutoCommit();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void commit(Connection c) {
        try {
            c.commit();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void rollback(Connection c) {
        try {
            c.rollback();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void close(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isClosed(Connection c) {
        try {
            return c.isClosed();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static DatabaseMetaData getMetaData(Connection c) {
        try {
            return c.getMetaData();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setReadOnly(Connection c, boolean readOnly) {
        try {
            c.setReadOnly(readOnly);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isReadOnly(Connection c) {
        try {
            return c.isReadOnly();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setCatalog(Connection c, String catalog) {
        try {
            c.setCatalog(catalog);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String getCatalog(Connection c) {
        try {
            return c.getCatalog();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setTransactionIsolation(Connection c, int level) {
        try {
            c.setTransactionIsolation(level);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getTransactionIsolation(Connection c) {
        try {
            return c.getTransactionIsolation();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static SQLWarning getWarnings(Connection c) {
        try {
            return c.getWarnings();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void clearWarnings(Connection c) {
        try {
            c.clearWarnings();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Statement createStatement(Connection c, int resultSetType, int resultSetConcurrency) {
        try {
            return c.createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static PreparedStatement prepareStatement(Connection c, String sql, int resultSetType, int resultSetConcurrency) {
        try {
            return c.prepareStatement(sql, resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static CallableStatement prepareCall(Connection c, String sql, int resultSetType, int resultSetConcurrency) {
        try {
            return c.prepareCall(sql, resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Map<String, Class<?>> getTypeMap(Connection c) {
        try {
            return c.getTypeMap();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setTypeMap(Connection c, Map<String, Class<?>> map) {
        try {
            c.setTypeMap(map);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setHoldability(Connection c, int holdability) {
        try {
            c.setHoldability(holdability);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getHoldability(Connection c) {
        try {
            return c.getHoldability();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Savepoint setSavepoint(Connection c) {
        try {
            return c.setSavepoint();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Savepoint setSavepoint(Connection c, String name) {
        try {
            return c.setSavepoint(name);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void rollback(Connection c, Savepoint savepoint) {
        try {
            c.rollback(savepoint);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void releaseSavepoint(Connection c, Savepoint savepoint) {
        try {
            c.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Statement createStatement(Connection c, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
        try {
            return c.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static PreparedStatement prepareStatement(Connection c, String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
        try {
            return c.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static CallableStatement prepareCall(Connection c, String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
        try {
            return c.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static PreparedStatement prepareStatement(Connection c, String sql, int autoGeneratedKeys) {
        try {
            return c.prepareStatement(sql, autoGeneratedKeys);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static PreparedStatement prepareStatement(Connection c, String sql, int[] columnIndexes) {
        try {
            return c.prepareStatement(sql, columnIndexes);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static PreparedStatement prepareStatement(Connection c, String sql, String[] columnNames) {
        try {
            return c.prepareStatement(sql, columnNames);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Clob createClob(Connection c) {
        try {
            return c.createClob();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Blob createBlob(Connection c) {
        try {
            return c.createBlob();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static NClob createNClob(Connection c) {
        try {
            return c.createNClob();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static SQLXML createSQLXML(Connection c) {
        try {
            return c.createSQLXML();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isValid(Connection c, int timeout) {
        try {
            return c.isValid(timeout);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setClientInfo(Connection c, String name, String value) {
        try {
            c.setClientInfo(name, value);
        } catch (SQLClientInfoException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setClientInfo(Connection c, Properties properties) {
        try {
            c.setClientInfo(properties);
        } catch (SQLClientInfoException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String getClientInfo(Connection c, String name) {
        try {
            return c.getClientInfo(name);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Properties getClientInfo(Connection c) {
        try {
            return c.getClientInfo();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Array createArrayOf(Connection c, String typeName, Object[] elements) {
        try {
            return c.createArrayOf(typeName, elements);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static Struct createStruct(Connection c, String typeName, Object[] attributes) {
        try {
            return c.createStruct(typeName, attributes);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setSchema(Connection c, String schema) {
        try {
            c.setSchema(schema);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static String getSchema(Connection c) {
        try {
            return c.getSchema();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void abort(Connection c, Executor executor) {
        try {
            c.abort(executor);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setNetworkTimeout(Connection c, Executor executor, int milliseconds) {
        try {
            c.setNetworkTimeout(executor, milliseconds);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getNetworkTimeout(Connection c) {
        try {
            return c.getNetworkTimeout();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static <T> T unwrap(Connection c, Class<T> iface) {
        try {
            return c.unwrap(iface);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isWrapperFor(Connection c, Class<?> iface) {
        try {
            return c.isWrapperFor(iface);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }
}
