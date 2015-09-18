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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 * Utility methods for working with {@link Statement}, {@link PreparedStatement} and {@link CallableStatement}
 * objects without throwing checked {@link SQLException}s.
 *
 * @author Jonathan Cone
 * @see Statement
 * @see PreparedStatement
 * @see CallableStatement
 */
public class Statements {
    public static ResultSet executeQuery(Statement s, String sql) {
        try {
            return s.executeQuery(sql);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int executeUpdate(Statement s, String sql) {
        try {
            return s.executeUpdate(sql);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void close(Statement s) {
        try {
            s.close();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getMaxFieldSize(Statement s) {
        try {
            return s.getMaxFieldSize();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setMaxFieldSize(Statement s, int max) {
        try {
            s.setMaxFieldSize(max);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getMaxRows(Statement s) {
        try {
            return s.getMaxRows();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setMaxRows(Statement s, int max) {
        try {
            s.setMaxRows(max);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setEscapeProcessing(Statement s, boolean enable) {
        try {
            s.setEscapeProcessing(enable);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getQueryTimeout(Statement s) {
        try {
            return s.getQueryTimeout();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setQueryTimeout(Statement s, int seconds) {
        try {
            s.setQueryTimeout(seconds);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void cancel(Statement s) {
        try {
            s.cancel();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static SQLWarning getWarnings(Statement s) {
        try {
            return s.getWarnings();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void clearWarnings(Statement s) {
        try {
            s.clearWarnings();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setCursorName(Statement s, String name) {
        try {
            s.setCursorName(name);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean execute(Statement s, String sql) {
        try {
            return s.execute(sql);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static ResultSet getResultSet(Statement s) {
        try {
            return s.getResultSet();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getUpdateCount(Statement s) {
        try {
            return s.getUpdateCount();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean getMoreResults(Statement s) {
        try {
            return s.getMoreResults();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setFetchDirection(Statement s, int direction) {
        try {
            s.setFetchDirection(direction);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getFetchDirection(Statement s) {
        try {
            return s.getFetchDirection();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setFetchSize(Statement s, int rows) {
        try {
            s.setFetchSize(rows);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getFetchSize(Statement s) {
        try {
            return s.getFetchSize();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getResultSetConcurrency(Statement s) {
        try {
            return s.getResultSetConcurrency();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getResultSetType(Statement s) {
        try {
            return s.getResultSetType();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void addBatch(Statement s, String sql) {
        try {
            s.addBatch(sql);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void clearBatch(Statement s) {
        try {
            s.clearBatch();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int[] executeBatch(Statement s) {
        try {
            s.executeBatch();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
        return new int[0];
    }

    public static Connection getConnection(Statement s) {
        try {
            return s.getConnection();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean getMoreResults(Statement s, int current) {
        try {
            return s.getMoreResults(current);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static ResultSet getGeneratedKeys(Statement s) {
        try {
            return s.getGeneratedKeys();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int executeUpdate(Statement s, String sql, int autoGeneratedKeys) {
        try {
            return s.executeUpdate(sql, autoGeneratedKeys);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int executeUpdate(Statement s, String sql, int[] columnIndexes) {
        try {
            return s.executeUpdate(sql, columnIndexes);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int executeUpdate(Statement s, String sql, String[] columnNames) {
        try {
            return s.executeUpdate(sql, columnNames);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean execute(Statement s, String sql, int autoGeneratedKeys) {
        try {
            return s.execute(sql, autoGeneratedKeys);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean execute(Statement s, String sql, int[] columnIndexes) {
        try {
            return s.execute(sql, columnIndexes);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean execute(Statement s, String sql, String[] columnNames) {
        try {
            return s.execute(sql, columnNames);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static int getResultSetHoldability(Statement s) {
        try {
            return s.getResultSetHoldability();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isClosed(Statement s) {
        try {
            return s.isClosed();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void setPoolable(Statement s, boolean poolable) {
        try {
            s.setPoolable(poolable);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isPoolable(Statement s) {
        try {
            return s.isPoolable();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static void closeOnCompletion(Statement s) {
        try {
            s.closeOnCompletion();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isCloseOnCompletion(Statement s) {
        try {
            return s.isCloseOnCompletion();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static <T> T unwrap(Statement s, Class<T> iface) {
        try {
            return s.unwrap(iface);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static boolean isWrapperFor(Statement s, Class<?> iface) {
        try {
            return s.isWrapperFor(iface);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    // PreparedStatement

    public static ResultSet executeQuery(PreparedStatement ps) {
        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static int executeUpdate(PreparedStatement ps) {
        try {
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setNull(PreparedStatement ps, int parameterIndex, int sqlType) {
        try {
            ps.setNull(parameterIndex, sqlType);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setBoolean(PreparedStatement ps, int parameterIndex, boolean x) {
        try {
            ps.setBoolean(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setByte(PreparedStatement ps, int parameterIndex, byte x) {
        try {
            ps.setByte(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setShort(PreparedStatement ps, int parameterIndex, short x) {
        try {
            ps.setShort(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setInt(PreparedStatement ps, int parameterIndex, int x) {
        try {
            ps.setInt(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setLong(PreparedStatement ps, int parameterIndex, long x) {
        try {
            ps.setLong(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setFloat(PreparedStatement ps, int parameterIndex, float x) {
        try {
            ps.setFloat(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setDouble(PreparedStatement ps, int parameterIndex, double x) {
        try {
            ps.setDouble(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setBigDecimal(PreparedStatement ps, int parameterIndex, BigDecimal x) {
        try {
            ps.setBigDecimal(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setString(PreparedStatement ps, int parameterIndex, String x) {
        try {
            ps.setString(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setBytes(PreparedStatement ps, int parameterIndex, byte x[]) {
        try {
            ps.setBytes(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setDate(PreparedStatement ps, int parameterIndex, java.sql.Date x) {
        try {
            ps.setDate(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setTime(PreparedStatement ps, int parameterIndex, java.sql.Time x) {
        try {
            ps.setTime(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setTimestamp(PreparedStatement ps, int parameterIndex, java.sql.Timestamp x) {
        try {
            ps.setTimestamp(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setAsciiStream(PreparedStatement ps, int parameterIndex, InputStream x, int length) {
        try {
            ps.setAsciiStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setBinaryStream(PreparedStatement ps, int parameterIndex, InputStream x, int length) {
        try {
            ps.setBinaryStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void clearParameters(PreparedStatement ps) {
        try {
            ps.clearParameters();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setObject(PreparedStatement ps, int parameterIndex, Object x, int targetSqlType) {
        try {
            ps.setObject(parameterIndex, x, targetSqlType);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setObject(PreparedStatement ps, int parameterIndex, Object x) {
        try {
            ps.setObject(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static boolean execute(PreparedStatement ps) {
        try {
            return ps.execute();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void addBatch(PreparedStatement ps) {
        try {
            ps.addBatch();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setCharacterStream(PreparedStatement ps, int parameterIndex, Reader reader, int length) {
        try {
            ps.setCharacterStream(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setRef(PreparedStatement ps, int parameterIndex, Ref x) {
        try {
            ps.setRef(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setBlob(PreparedStatement ps, int parameterIndex, Blob x) {
        try {
            ps.setBlob(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setClob(PreparedStatement ps, int parameterIndex, Clob x) {
        try {
            ps.setClob(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setArray(PreparedStatement ps, int parameterIndex, Array x) {
        try {
            ps.setArray(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static ResultSetMetaData getMetaData(PreparedStatement ps) {
        try {
            return ps.getMetaData();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setDate(PreparedStatement ps, int parameterIndex, java.sql.Date x, Calendar cal) {
        try {
            ps.setDate(parameterIndex, x, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setTime(PreparedStatement ps, int parameterIndex, java.sql.Time x, Calendar cal) {
        try {
            ps.setTime(parameterIndex, x, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setTimestamp(PreparedStatement ps, int parameterIndex, java.sql.Timestamp x, Calendar cal) {
        try {
            ps.setTimestamp(parameterIndex, x, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setNull(PreparedStatement ps, int parameterIndex, int sqlType, String typeName) {
        try {
            ps.setNull(parameterIndex, sqlType, typeName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setURL(PreparedStatement ps, int parameterIndex, java.net.URL x) {
        try {
            ps.setURL(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static ParameterMetaData getParameterMetaData(PreparedStatement ps) {
        try {
            return ps.getParameterMetaData();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setRowId(PreparedStatement ps, int parameterIndex, RowId x) {
        try {
            ps.setRowId(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setNString(PreparedStatement ps, int parameterIndex, String value) {
        try {
            ps.setNString(parameterIndex, value);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setNCharacterStream(PreparedStatement ps, int parameterIndex, Reader value, long length) {
        try {
            ps.setNCharacterStream(parameterIndex, value, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setNClob(PreparedStatement ps, int parameterIndex, NClob value) {
        try {
            ps.setNClob(parameterIndex, value);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setClob(PreparedStatement ps, int parameterIndex, Reader reader, long length) {
        try {
            ps.setClob(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setBlob(PreparedStatement ps, int parameterIndex, InputStream inputStream, long length) {
        try {
            ps.setBlob(parameterIndex, inputStream, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setNClob(PreparedStatement ps, int parameterIndex, Reader reader, long length) {
        try {
            ps.setNClob(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setSQLXML(PreparedStatement ps, int parameterIndex, SQLXML xmlObject) {
        try {
            ps.setSQLXML(parameterIndex, xmlObject);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setObject(PreparedStatement ps, int parameterIndex, Object x, int targetSqlType, int scaleOrLength) {
        try {
            ps.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setAsciiStream(PreparedStatement ps, int parameterIndex, InputStream x, long length) {
        try {
            ps.setAsciiStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setBinaryStream(PreparedStatement ps, int parameterIndex, InputStream x, long length) {
        try {
            ps.setBinaryStream(parameterIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setCharacterStream(PreparedStatement ps, int parameterIndex, Reader reader, long length) {
        try {
            ps.setCharacterStream(parameterIndex, reader, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setAsciiStream(PreparedStatement ps, int parameterIndex, InputStream x) {
        try {
            ps.setAsciiStream(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setBinaryStream(PreparedStatement ps, int parameterIndex, InputStream x) {
        try {
            ps.setBinaryStream(parameterIndex, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setCharacterStream(PreparedStatement ps, int parameterIndex, Reader reader) {
        try {
            ps.setCharacterStream(parameterIndex, reader);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setNCharacterStream(PreparedStatement ps, int parameterIndex, Reader value) {
        try {
            ps.setNCharacterStream(parameterIndex, value);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setClob(PreparedStatement ps, int parameterIndex, Reader reader) {
        try {
            ps.setClob(parameterIndex, reader);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setBlob(PreparedStatement ps, int parameterIndex, InputStream inputStream) {
        try {
            ps.setBlob(parameterIndex, inputStream);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setNClob(PreparedStatement ps, int parameterIndex, Reader reader) {
        try {
            ps.setNClob(parameterIndex, reader);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setObject(PreparedStatement ps, int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) {
        try {
            ps.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }


    public static void setObject(PreparedStatement ps, int parameterIndex, Object x, SQLType targetSqlType) {
        try {
            ps.setObject(parameterIndex, x, targetSqlType);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    public static long executeLargeUpdate(PreparedStatement ps) {
        try {
            return ps.executeLargeUpdate();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    // CallableStatement

    public static void registerOutParameter(CallableStatement cs, int parameterIndex, int sqlType) {
        try {
            cs.registerOutParameter(parameterIndex, sqlType);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void registerOutParameter(CallableStatement cs, int parameterIndex, int sqlType, int scale) {
        try {
            cs.registerOutParameter(parameterIndex, sqlType, scale);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static boolean wasNull(CallableStatement cs) {
        try {
            return cs.wasNull();
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static String getString(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getString(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static boolean getBoolean(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getBoolean(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static byte getByte(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getByte(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static short getShort(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getShort(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static int getInt(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getInt(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static long getLong(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getLong(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static float getFloat(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getFloat(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static double getDouble(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getDouble(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static byte[] getBytes(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getBytes(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Date getDate(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getDate(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Time getTime(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getTime(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Timestamp getTimestamp(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getTimestamp(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Object getObject(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getObject(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static BigDecimal getBigDecimal(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getBigDecimal(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Object getObject(CallableStatement cs, int parameterIndex, Map<String, Class<?>> map) {
        try {
            return cs.getObject(parameterIndex, map);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Ref getRef(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getRef(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Blob getBlob(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getBlob(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Clob getClob(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getClob(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Array getArray(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getArray(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Date getDate(CallableStatement cs, int parameterIndex, Calendar cal) {
        try {
            return cs.getDate(parameterIndex, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Time getTime(CallableStatement cs, int parameterIndex, Calendar cal) {
        try {
            return cs.getTime(parameterIndex, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Timestamp getTimestamp(CallableStatement cs, int parameterIndex, Calendar cal) {
        try {
            return cs.getTimestamp(parameterIndex, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void registerOutParameter(CallableStatement cs, int parameterIndex, int sqlType, String typeName) {
        try {
            cs.registerOutParameter(parameterIndex, sqlType, typeName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void registerOutParameter(CallableStatement cs, String parameterName, int sqlType) {
        try {
            cs.registerOutParameter(parameterName, sqlType);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void registerOutParameter(CallableStatement cs, String parameterName, int sqlType, int scale) {
        try {
            cs.registerOutParameter(parameterName, sqlType, scale);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void registerOutParameter(CallableStatement cs, String parameterName, int sqlType, String typeName) {
        try {
            cs.registerOutParameter(parameterName, sqlType, typeName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static URL getURL(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getURL(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setURL(CallableStatement cs, String parameterName, URL val) {
        try {
            cs.setURL(parameterName, val);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setNull(CallableStatement cs, String parameterName, int sqlType) {
        try {
            cs.setNull(parameterName, sqlType);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setBoolean(CallableStatement cs, String parameterName, boolean x) {
        try {
            cs.setBoolean(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setByte(CallableStatement cs, String parameterName, byte x) {
        try {
            cs.setByte(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setShort(CallableStatement cs, String parameterName, short x) {
        try {
            cs.setShort(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setInt(CallableStatement cs, String parameterName, int x) {
        try {
            cs.setInt(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setLong(CallableStatement cs, String parameterName, long x) {
        try {
            cs.setLong(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setFloat(CallableStatement cs, String parameterName, float x) {
        try {
            cs.setFloat(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setDouble(CallableStatement cs, String parameterName, double x) {
        try {
            cs.setDouble(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setBigDecimal(CallableStatement cs, String parameterName, BigDecimal x) {
        try {
            cs.setBigDecimal(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setString(CallableStatement cs, String parameterName, String x) {
        try {
            cs.setString(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setBytes(CallableStatement cs, String parameterName, byte x[]) {
        try {
            cs.setBytes(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setDate(CallableStatement cs, String parameterName, java.sql.Date x) {
        try {
            cs.setDate(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setTime(CallableStatement cs, String parameterName, java.sql.Time x) {
        try {
            cs.setTime(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setTimestamp(CallableStatement cs, String parameterName, java.sql.Timestamp x) {
        try {
            cs.setTimestamp(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setAsciiStream(CallableStatement cs, String parameterName, InputStream x, int length) {
        try {
            cs.setAsciiStream(parameterName, x, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setBinaryStream(CallableStatement cs, String parameterName, InputStream x, int length) {
        try {
            cs.setBinaryStream(parameterName, x, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setObject(CallableStatement cs, String parameterName, Object x, int targetSqlType, int scale) {
        try {
            cs.setObject(parameterName, x, targetSqlType, scale);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setObject(CallableStatement cs, String parameterName, Object x, int targetSqlType) {
        try {
            cs.setObject(parameterName, x, targetSqlType);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setObject(CallableStatement cs, String parameterName, Object x) {
        try {
            cs.setObject(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setCharacterStream(CallableStatement cs, String parameterName, Reader reader, int length) {
        try {
            cs.setCharacterStream(parameterName, reader, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setDate(CallableStatement cs, String parameterName, java.sql.Date x, Calendar cal) {
        try {
            cs.setDate(parameterName, x, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setTime(CallableStatement cs, String parameterName, java.sql.Time x, Calendar cal) {
        try {
            cs.setTime(parameterName, x, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setTimestamp(CallableStatement cs, String parameterName, java.sql.Timestamp x, Calendar cal) {
        try {
            cs.setTimestamp(parameterName, x, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setNull(CallableStatement cs, String parameterName, int sqlType, String typeName) {
        try {
            cs.setNull(parameterName, sqlType, typeName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static String getString(CallableStatement cs, String parameterName) {
        try {
            return cs.getString(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static boolean getBoolean(CallableStatement cs, String parameterName) {
        try {
            return cs.getBoolean(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static byte getByte(CallableStatement cs, String parameterName) {
        try {
            return cs.getByte(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static short getShort(CallableStatement cs, String parameterName) {
        try {
            return cs.getShort(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static int getInt(CallableStatement cs, String parameterName) {
        try {
            return cs.getInt(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static long getLong(CallableStatement cs, String parameterName) {
        try {
            return cs.getLong(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static float getFloat(CallableStatement cs, String parameterName) {
        try {
            return cs.getFloat(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static double getDouble(CallableStatement cs, String parameterName) {
        try {
            return cs.getDouble(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static byte[] getBytes(CallableStatement cs, String parameterName) {
        try {
            return cs.getBytes(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Date getDate(CallableStatement cs, String parameterName) {
        try {
            return cs.getDate(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Time getTime(CallableStatement cs, String parameterName) {
        try {
            return cs.getTime(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Timestamp getTimestamp(CallableStatement cs, String parameterName) {
        try {
            return cs.getTimestamp(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Object getObject(CallableStatement cs, String parameterName) {
        try {
            return cs.getObject(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static BigDecimal getBigDecimal(CallableStatement cs, String parameterName) {
        try {
            return cs.getBigDecimal(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Object getObject(CallableStatement cs, String parameterName, java.util.Map<String, Class<?>> map) {
        try {
            return cs.getObject(parameterName, map);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Ref getRef(CallableStatement cs, String parameterName) {
        try {
            return cs.getRef(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Blob getBlob(CallableStatement cs, String parameterName) {
        try {
            return cs.getBlob(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Clob getClob(CallableStatement cs, String parameterName) {
        try {
            return cs.getClob(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Array getArray(CallableStatement cs, String parameterName) {
        try {
            return cs.getArray(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Date getDate(CallableStatement cs, String parameterName, Calendar cal) {
        try {
            return cs.getDate(parameterName, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Time getTime(CallableStatement cs, String parameterName, Calendar cal) {
        try {
            return cs.getTime(parameterName, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static java.sql.Timestamp getTimestamp(CallableStatement cs, String parameterName, Calendar cal) {
        try {
            return cs.getTimestamp(parameterName, cal);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static URL getURL(CallableStatement cs, String parameterName) {
        try {
            return cs.getURL(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static RowId getRowId(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getRowId(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static RowId getRowId(CallableStatement cs, String parameterName) {
        try {
            return cs.getRowId(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setRowId(CallableStatement cs, String parameterName, RowId x) {
        try {
            cs.setRowId(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setNString(CallableStatement cs, String parameterName, String value) {
        try {
            cs.setNString(parameterName, value);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setNCharacterStream(CallableStatement cs, String parameterName, Reader value, long length) {
        try {
            cs.setNCharacterStream(parameterName, value, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setNClob(CallableStatement cs, String parameterName, NClob value) {
        try {
            cs.setNClob(parameterName, value);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setClob(CallableStatement cs, String parameterName, Reader reader, long length) {
        try {
            cs.setClob(parameterName, reader, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setBlob(CallableStatement cs, String parameterName, InputStream inputStream, long length) {
        try {
            cs.setBlob(parameterName, inputStream, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setNClob(CallableStatement cs, String parameterName, Reader reader, long length) {
        try {
            cs.setNClob(parameterName, reader, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static NClob getNClob(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getNClob(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static NClob getNClob(CallableStatement cs, String parameterName) {
        try {
            return cs.getNClob(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setSQLXML(CallableStatement cs, String parameterName, SQLXML xmlObject) {
        try {
            cs.setSQLXML(parameterName, xmlObject);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static SQLXML getSQLXML(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getSQLXML(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static SQLXML getSQLXML(CallableStatement cs, String parameterName) {
        try {
            return cs.getSQLXML(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static String getNString(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getNString(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static String getNString(CallableStatement cs, String parameterName) {
        try {
            return cs.getNString(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Reader getNCharacterStream(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getNCharacterStream(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Reader getNCharacterStream(CallableStatement cs, String parameterName) {
        try {
            return cs.getNCharacterStream(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Reader getCharacterStream(CallableStatement cs, int parameterIndex) {
        try {
            return cs.getCharacterStream(parameterIndex);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static Reader getCharacterStream(CallableStatement cs, String parameterName) {
        try {
            return cs.getCharacterStream(parameterName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setBlob(CallableStatement cs, String parameterName, Blob x) {
        try {
            cs.setBlob(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }


    public static void setClob(CallableStatement cs, String parameterName, Clob x) {
        try {
            cs.setClob(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setAsciiStream(CallableStatement cs, String parameterName, InputStream x, long length) {
        try {
            cs.setAsciiStream(parameterName, x, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setBinaryStream(CallableStatement cs, String parameterName, InputStream x, long length) {
        try {
            cs.setBinaryStream(parameterName, x, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setCharacterStream(CallableStatement cs, String parameterName, Reader reader, long length) {
        try {
            cs.setCharacterStream(parameterName, reader, length);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setAsciiStream(CallableStatement cs, String parameterName, InputStream x) {
        try {
            cs.setAsciiStream(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setBinaryStream(CallableStatement cs, String parameterName, InputStream x) {
        try {
            cs.setBinaryStream(parameterName, x);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setCharacterStream(CallableStatement cs, String parameterName, Reader reader) {
        try {
            cs.setCharacterStream(parameterName, reader);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setNCharacterStream(CallableStatement cs, String parameterName, Reader value) {
        try {
            cs.setNCharacterStream(parameterName, value);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setClob(CallableStatement cs, String parameterName, Reader reader) {
        try {
            cs.setClob(parameterName, reader);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setBlob(CallableStatement cs, String parameterName, InputStream inputStream) {
        try {
            cs.setBlob(parameterName, inputStream);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setNClob(CallableStatement cs, String parameterName, Reader reader) {
        try {
            cs.setNClob(parameterName, reader);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static <T> T getObject(CallableStatement cs, int parameterIndex, Class<T> type) {
        try {
            return cs.getObject(parameterIndex, type);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static <T> T getObject(CallableStatement cs, String parameterName, Class<T> type) {
        try {
            return cs.getObject(parameterName, type);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setObject(CallableStatement cs, String parameterName, Object x, SQLType targetSqlType, int scaleOrLength) {
        try {
            cs.setObject(parameterName, x, targetSqlType, scaleOrLength);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void setObject(CallableStatement cs, String parameterName, Object x, SQLType targetSqlType) {
        try {
            cs.setObject(parameterName, x, targetSqlType);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void registerOutParameter(CallableStatement cs, int parameterIndex, SQLType sqlType) {
        try {
            cs.registerOutParameter(parameterIndex, sqlType);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void registerOutParameter(CallableStatement cs, int parameterIndex, SQLType sqlType, int scale) {
        try {
            cs.registerOutParameter(parameterIndex, sqlType, scale);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void registerOutParameter(CallableStatement cs, int parameterIndex, SQLType sqlType, String typeName) {
        try {
            cs.registerOutParameter(parameterIndex, sqlType, typeName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void registerOutParameter(CallableStatement cs, String parameterName, SQLType sqlType) {
        try {
            cs.registerOutParameter(parameterName, sqlType);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void registerOutParameter(CallableStatement cs, String parameterName, SQLType sqlType, int scale) {
        try {
            cs.registerOutParameter(parameterName, sqlType, scale);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

    public static void registerOutParameter(CallableStatement cs, String parameterName, SQLType sqlType, String typeName) {
        try {
            cs.registerOutParameter(parameterName, sqlType, typeName);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }

    }

}
