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
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by jcone on 9/11/2015.
 */
public class ResultSets {
    public static boolean next(ResultSet rs) {
        try {
            return rs.next();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean wasNull(ResultSet rs) {
        try {
            return rs.wasNull();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static String getString(ResultSet rs, int columnIndex) {
        try {
            return rs.getString(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean getBoolean(ResultSet rs, int columnIndex) {
        try {
            return rs.getBoolean(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static byte getByte(ResultSet rs, int columnIndex) {
        try {
            return rs.getByte(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static short getShort(ResultSet rs, int columnIndex) {
        try {
            return rs.getShort(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static int getInt(ResultSet rs, int columnIndex) {
        try {
            return rs.getInt(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static long getLong(ResultSet rs, int columnIndex) {
        try {
            return rs.getLong(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static float getFloat(ResultSet rs, int columnIndex) {
        try {
            return rs.getFloat(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static double getDouble(ResultSet rs, int columnIndex) {
        try {
            return rs.getDouble(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static byte[] getBytes(ResultSet rs, int columnIndex) {
        try {
            rs.getBytes(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return new byte[0];
    }


    public static Date getDate(ResultSet rs, int columnIndex) {
        try {
            return rs.getDate(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Time getTime(ResultSet rs, int columnIndex) {
        try {
            return rs.getTime(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Timestamp getTimestamp(ResultSet rs, int columnIndex) {
        try {
            return rs.getTimestamp(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static InputStream getAsciiStream(ResultSet rs, int columnIndex) {
        try {
            return rs.getAsciiStream(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static InputStream getBinaryStream(ResultSet rs, int columnIndex) {
        try {
            return rs.getBinaryStream(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static String getString(ResultSet rs, String columnLabel) {
        try {
            return rs.getString(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean getBoolean(ResultSet rs, String columnLabel) {
        try {
            return rs.getBoolean(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static byte getByte(ResultSet rs, String columnLabel) {
        try {
            return rs.getByte(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static short getShort(ResultSet rs, String columnLabel) {
        try {
            return rs.getShort(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static int getInt(ResultSet rs, String columnLabel) {
        try {
            return rs.getInt(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static long getLong(ResultSet rs, String columnLabel) {
        try {
            return rs.getLong(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static float getFloat(ResultSet rs, String columnLabel) {
        try {
            return rs.getFloat(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static double getDouble(ResultSet rs, String columnLabel) {
        try {
            return rs.getDouble(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static byte[] getBytes(ResultSet rs, String columnLabel) {
        try {
            rs.getBytes(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
        return new byte[0];
    }


    public static Date getDate(ResultSet rs, String columnLabel) {
        try {
            return rs.getDate(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Time getTime(ResultSet rs, String columnLabel) {
        try {
            return rs.getTime(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Timestamp getTimestamp(ResultSet rs, String columnLabel) {
        try {
            return rs.getTimestamp(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static InputStream getAsciiStream(ResultSet rs, String columnLabel) {
        try {
            return rs.getAsciiStream(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static InputStream getBinaryStream(ResultSet rs, String columnLabel) {
        try {
            return rs.getBinaryStream(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static SQLWarning getWarnings(ResultSet rs) {
        try {
            return rs.getWarnings();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void clearWarnings(ResultSet rs) {
        try {
            rs.clearWarnings();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static String getCursorName(ResultSet rs) {
        try {
            return rs.getCursorName();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static ResultSetMetaData getMetaData(ResultSet rs) {
        try {
            return rs.getMetaData();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Object getObject(ResultSet rs, int columnIndex) {
        try {
            return rs.getObject(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Object getObject(ResultSet rs, String columnLabel) {
        try {
            return rs.getObject(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static int findColumn(ResultSet rs, String columnLabel) {
        try {
            return rs.findColumn(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Reader getCharacterStream(ResultSet rs, int columnIndex) {
        try {
            return rs.getCharacterStream(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Reader getCharacterStream(ResultSet rs, String columnLabel) {
        try {
            return rs.getCharacterStream(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static BigDecimal getBigDecimal(ResultSet rs, int columnIndex) {
        try {
            return rs.getBigDecimal(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static BigDecimal getBigDecimal(ResultSet rs, String columnLabel) {
        try {
            return rs.getBigDecimal(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean isBeforeFirst(ResultSet rs) {
        try {
            return rs.isBeforeFirst();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean isAfterLast(ResultSet rs) {
        try {
            return rs.isAfterLast();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean isFirst(ResultSet rs) {
        try {
            return rs.isFirst();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean isLast(ResultSet rs) {
        try {
            return rs.isLast();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void beforeFirst(ResultSet rs) {
        try {
            rs.beforeFirst();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void afterLast(ResultSet rs) {
        try {
            rs.afterLast();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean first(ResultSet rs) {
        try {
            return rs.first();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean last(ResultSet rs) {
        try {
            return rs.last();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static int getRow(ResultSet rs) {
        try {
            return rs.getRow();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean absolute(ResultSet rs, int row) {
        try {
            return rs.absolute(row);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean relative(ResultSet rs, int rows) {
        try {
            return rs.relative(rows);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean previous(ResultSet rs) {
        try {
            return rs.previous();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void setFetchDirection(ResultSet rs, int direction) {
        try {
            rs.setFetchDirection(direction);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static int getFetchDirection(ResultSet rs) {
        try {
            return rs.getFetchDirection();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void setFetchSize(ResultSet rs, int rows) {
        try {
            rs.setFetchSize(rows);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static int getFetchSize(ResultSet rs) {
        try {
            return rs.getFetchSize();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static int getType(ResultSet rs) {
        try {
            return rs.getType();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static int getConcurrency(ResultSet rs) {
        try {
            return rs.getConcurrency();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean rowUpdated(ResultSet rs) {
        try {
            return rs.rowUpdated();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean rowInserted(ResultSet rs) {
        try {
            return rs.rowInserted();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean rowDeleted(ResultSet rs) {
        try {
            return rs.rowDeleted();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNull(ResultSet rs, int columnIndex) {
        try {
            rs.updateNull(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBoolean(ResultSet rs, int columnIndex, boolean x) {
        try {
            rs.updateBoolean(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateByte(ResultSet rs, int columnIndex, byte x) {
        try {
            rs.updateByte(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateShort(ResultSet rs, int columnIndex, short x) {
        try {
            rs.updateShort(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateInt(ResultSet rs, int columnIndex, int x) {
        try {
            rs.updateInt(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateLong(ResultSet rs, int columnIndex, long x) {
        try {
            rs.updateLong(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateFloat(ResultSet rs, int columnIndex, float x) {
        try {
            rs.updateFloat(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateDouble(ResultSet rs, int columnIndex, double x) {
        try {
            rs.updateDouble(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBigDecimal(ResultSet rs, int columnIndex, BigDecimal x) {
        try {
            rs.updateBigDecimal(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateString(ResultSet rs, int columnIndex, String x) {
        try {
            rs.updateString(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBytes(ResultSet rs, int columnIndex, byte[] x) {
        try {
            rs.updateBytes(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateDate(ResultSet rs, int columnIndex, Date x) {
        try {
            rs.updateDate(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateTime(ResultSet rs, int columnIndex, Time x) {
        try {
            rs.updateTime(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateTimestamp(ResultSet rs, int columnIndex, Timestamp x) {
        try {
            rs.updateTimestamp(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateAsciiStream(ResultSet rs, int columnIndex, InputStream x, int length) {
        try {
            rs.updateAsciiStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBinaryStream(ResultSet rs, int columnIndex, InputStream x, int length) {
        try {
            rs.updateBinaryStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateCharacterStream(ResultSet rs, int columnIndex, Reader x, int length) {
        try {
            rs.updateCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateObject(ResultSet rs, int columnIndex, Object x, int scaleOrLength) {
        try {
            rs.updateObject(columnIndex, x, scaleOrLength);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateObject(ResultSet rs, int columnIndex, Object x) {
        try {
            rs.updateObject(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNull(ResultSet rs, String columnLabel) {
        try {
            rs.updateNull(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBoolean(ResultSet rs, String columnLabel, boolean x) {
        try {
            rs.updateBoolean(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateByte(ResultSet rs, String columnLabel, byte x) {
        try {
            rs.updateByte(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateShort(ResultSet rs, String columnLabel, short x) {
        try {
            rs.updateShort(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateInt(ResultSet rs, String columnLabel, int x) {
        try {
            rs.updateInt(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateLong(ResultSet rs, String columnLabel, long x) {
        try {
            rs.updateLong(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateFloat(ResultSet rs, String columnLabel, float x) {
        try {
            rs.updateFloat(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateDouble(ResultSet rs, String columnLabel, double x) {
        try {
            rs.updateDouble(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBigDecimal(ResultSet rs, String columnLabel, BigDecimal x) {
        try {
            rs.updateBigDecimal(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateString(ResultSet rs, String columnLabel, String x) {
        try {
            rs.updateString(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBytes(ResultSet rs, String columnLabel, byte[] x) {
        try {
            rs.updateBytes(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateDate(ResultSet rs, String columnLabel, Date x) {
        try {
            rs.updateDate(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateTime(ResultSet rs, String columnLabel, Time x) {
        try {
            rs.updateTime(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateTimestamp(ResultSet rs, String columnLabel, Timestamp x) {
        try {
            rs.updateTimestamp(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateAsciiStream(ResultSet rs, String columnLabel, InputStream x, int length) {
        try {
            rs.updateAsciiStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBinaryStream(ResultSet rs, String columnLabel, InputStream x, int length) {
        try {
            rs.updateBinaryStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateCharacterStream(ResultSet rs, String columnLabel, Reader reader, int length) {
        try {
            rs.updateCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateObject(ResultSet rs, String columnLabel, Object x, int scaleOrLength) {
        try {
            rs.updateObject(columnLabel, x, scaleOrLength);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateObject(ResultSet rs, String columnLabel, Object x) {
        try {
            rs.updateObject(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void insertRow(ResultSet rs) {
        try {
            rs.insertRow();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateRow(ResultSet rs) {
        try {
            rs.updateRow();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void deleteRow(ResultSet rs) {
        try {
            rs.deleteRow();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void refreshRow(ResultSet rs) {
        try {
            rs.refreshRow();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void cancelRowUpdates(ResultSet rs) {
        try {
            rs.cancelRowUpdates();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void moveToInsertRow(ResultSet rs) {
        try {
            rs.moveToInsertRow();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void moveToCurrentRow(ResultSet rs) {
        try {
            rs.moveToCurrentRow();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Statement getStatement(ResultSet rs) {
        try {
            return rs.getStatement();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Object getObject(ResultSet rs, int columnIndex, Map<String, Class<?>> map) {
        try {
            return rs.getObject(columnIndex, map);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Ref getRef(ResultSet rs, int columnIndex) {
        try {
            return rs.getRef(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Blob getBlob(ResultSet rs, int columnIndex) {
        try {
            return rs.getBlob(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Clob getClob(ResultSet rs, int columnIndex) {
        try {
            return rs.getClob(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Array getArray(ResultSet rs, int columnIndex) {
        try {
            return rs.getArray(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Object getObject(ResultSet rs, String columnLabel, Map<String, Class<?>> map) {
        try {
            return rs.getObject(columnLabel, map);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Ref getRef(ResultSet rs, String columnLabel) {
        try {
            return rs.getRef(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Blob getBlob(ResultSet rs, String columnLabel) {
        try {
            return rs.getBlob(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Clob getClob(ResultSet rs, String columnLabel) {
        try {
            return rs.getClob(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Array getArray(ResultSet rs, String columnLabel) {
        try {
            return rs.getArray(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Date getDate(ResultSet rs, int columnIndex, Calendar cal) {
        try {
            return rs.getDate(columnIndex, cal);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Date getDate(ResultSet rs, String columnLabel, Calendar cal) {
        try {
            return rs.getDate(columnLabel, cal);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Time getTime(ResultSet rs, int columnIndex, Calendar cal) {
        try {
            return rs.getTime(columnIndex, cal);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Time getTime(ResultSet rs, String columnLabel, Calendar cal) {
        try {
            return rs.getTime(columnLabel, cal);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Timestamp getTimestamp(ResultSet rs, int columnIndex, Calendar cal) {
        try {
            return rs.getTimestamp(columnIndex, cal);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Timestamp getTimestamp(ResultSet rs, String columnLabel, Calendar cal) {
        try {
            return rs.getTimestamp(columnLabel, cal);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static URL getURL(ResultSet rs, int columnIndex) {
        try {
            return rs.getURL(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static URL getURL(ResultSet rs, String columnLabel) {
        try {
            return rs.getURL(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateRef(ResultSet rs, int columnIndex, Ref x) {
        try {
            rs.updateRef(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateRef(ResultSet rs, String columnLabel, Ref x) {
        try {
            rs.updateRef(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBlob(ResultSet rs, int columnIndex, Blob x) {
        try {
            rs.updateBlob(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBlob(ResultSet rs, String columnLabel, Blob x) {
        try {
            rs.updateBlob(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateClob(ResultSet rs, int columnIndex, Clob x) {
        try {
            rs.updateClob(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateClob(ResultSet rs, String columnLabel, Clob x) {
        try {
            rs.updateClob(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateArray(ResultSet rs, int columnIndex, Array x) {
        try {
            rs.updateArray(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateArray(ResultSet rs, String columnLabel, Array x) {
        try {
            rs.updateArray(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static RowId getRowId(ResultSet rs, int columnIndex) {
        try {
            return rs.getRowId(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static RowId getRowId(ResultSet rs, String columnLabel) {
        try {
            return rs.getRowId(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateRowId(ResultSet rs, int columnIndex, RowId x) {
        try {
            rs.updateRowId(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateRowId(ResultSet rs, String columnLabel, RowId x) {
        try {
            rs.updateRowId(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static int getHoldability(ResultSet rs) {
        try {
            return rs.getHoldability();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean isOpen(ResultSet rs) {
        return !isClosed(rs);
    }

    public static boolean isClosed(ResultSet rs) {
        try {
            return rs.isClosed();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNString(ResultSet rs, int columnIndex, String nString) {
        try {
            rs.updateNString(columnIndex, nString);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNString(ResultSet rs, String columnLabel, String nString) {
        try {
            rs.updateNString(columnLabel, nString);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNClob(ResultSet rs, int columnIndex, NClob nClob) {
        try {
            rs.updateNClob(columnIndex, nClob);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNClob(ResultSet rs, String columnLabel, NClob nClob) {
        try {
            rs.updateNClob(columnLabel, nClob);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static NClob getNClob(ResultSet rs, int columnIndex) {
        try {
            return rs.getNClob(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static NClob getNClob(ResultSet rs, String columnLabel) {
        try {
            return rs.getNClob(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static SQLXML getSQLXML(ResultSet rs, int columnIndex) {
        try {
            return rs.getSQLXML(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static SQLXML getSQLXML(ResultSet rs, String columnLabel) {
        try {
            return rs.getSQLXML(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateSQLXML(ResultSet rs, int columnIndex, SQLXML xmlObject) {
        try {
            rs.updateSQLXML(columnIndex, xmlObject);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateSQLXML(ResultSet rs, String columnLabel, SQLXML xmlObject) {
        try {
            rs.updateSQLXML(columnLabel, xmlObject);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static String getNString(ResultSet rs, int columnIndex) {
        try {
            return rs.getNString(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static String getNString(ResultSet rs, String columnLabel) {
        try {
            return rs.getNString(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Reader getNCharacterStream(ResultSet rs, int columnIndex) {
        try {
            return rs.getNCharacterStream(columnIndex);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static Reader getNCharacterStream(ResultSet rs, String columnLabel) {
        try {
            return rs.getNCharacterStream(columnLabel);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNCharacterStream(ResultSet rs, int columnIndex, Reader x, long length) {
        try {
            rs.updateNCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNCharacterStream(ResultSet rs, String columnLabel, Reader reader, long length) {
        try {
            rs.updateNCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateAsciiStream(ResultSet rs, int columnIndex, InputStream x, long length) {
        try {
            rs.updateAsciiStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBinaryStream(ResultSet rs, int columnIndex, InputStream x, long length) {
        try {
            rs.updateBinaryStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateCharacterStream(ResultSet rs, int columnIndex, Reader x, long length) {
        try {
            rs.updateCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateAsciiStream(ResultSet rs, String columnLabel, InputStream x, long length) {
        try {
            rs.updateAsciiStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBinaryStream(ResultSet rs, String columnLabel, InputStream x, long length) {
        try {
            rs.updateBinaryStream(columnLabel, x, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateCharacterStream(ResultSet rs, String columnLabel, Reader reader, long length) {
        try {
            rs.updateCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBlob(ResultSet rs, int columnIndex, InputStream inputStream, long length) {
        try {
            rs.updateBlob(columnIndex, inputStream, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBlob(ResultSet rs, String columnLabel, InputStream inputStream, long length) {
        try {
            rs.updateBlob(columnLabel, inputStream, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateClob(ResultSet rs, int columnIndex, Reader reader, long length) {
        try {
            rs.updateClob(columnIndex, reader, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateClob(ResultSet rs, String columnLabel, Reader reader, long length) {
        try {
            rs.updateClob(columnLabel, reader, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNClob(ResultSet rs, int columnIndex, Reader reader, long length) {
        try {
            rs.updateNClob(columnIndex, reader, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNClob(ResultSet rs, String columnLabel, Reader reader, long length) {
        try {
            rs.updateNClob(columnLabel, reader, length);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNCharacterStream(ResultSet rs, int columnIndex, Reader x) {
        try {
            rs.updateNCharacterStream(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNCharacterStream(ResultSet rs, String columnLabel, Reader reader) {
        try {
            rs.updateNCharacterStream(columnLabel, reader);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateAsciiStream(ResultSet rs, int columnIndex, InputStream x) {
        try {
            rs.updateAsciiStream(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBinaryStream(ResultSet rs, int columnIndex, InputStream x) {
        try {
            rs.updateBinaryStream(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateCharacterStream(ResultSet rs, int columnIndex, Reader x) {
        try {
            rs.updateCharacterStream(columnIndex, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateAsciiStream(ResultSet rs, String columnLabel, InputStream x) {
        try {
            rs.updateAsciiStream(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBinaryStream(ResultSet rs, String columnLabel, InputStream x) {
        try {
            rs.updateBinaryStream(columnLabel, x);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateCharacterStream(ResultSet rs, String columnLabel, Reader reader) {
        try {
            rs.updateCharacterStream(columnLabel, reader);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBlob(ResultSet rs, int columnIndex, InputStream inputStream) {
        try {
            rs.updateBlob(columnIndex, inputStream);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateBlob(ResultSet rs, String columnLabel, InputStream inputStream) {
        try {
            rs.updateBlob(columnLabel, inputStream);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateClob(ResultSet rs, int columnIndex, Reader reader) {
        try {
            rs.updateClob(columnIndex, reader);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateClob(ResultSet rs, String columnLabel, Reader reader) {
        try {
            rs.updateClob(columnLabel, reader);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNClob(ResultSet rs, int columnIndex, Reader reader) {
        try {
            rs.updateNClob(columnIndex, reader);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static void updateNClob(ResultSet rs, String columnLabel, Reader reader) {
        try {
            rs.updateNClob(columnLabel, reader);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static <T> T getObject(ResultSet rs, int columnIndex, Class<T> type) {
        try {
            return rs.getObject(columnIndex, type);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static <T> T getObject(ResultSet rs, String columnLabel, Class<T> type) {
        try {
            return rs.getObject(columnLabel, type);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static <T> T unwrap(ResultSet rs, Class<T> iface) {
        try {
            return rs.unwrap(iface);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }


    public static boolean isWrapperFor(ResultSet rs, Class<?> iface) {
        try {
            return rs.isWrapperFor(iface);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }

    public static Stream<ResultSet> stream(PreparedStatement ps) {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(
                        new ResultSetIterator(ps), 0), false);
    }
}
