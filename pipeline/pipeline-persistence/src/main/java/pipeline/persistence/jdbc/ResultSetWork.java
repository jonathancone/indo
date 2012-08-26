package pipeline.persistence.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class ResultSetWork {

	private ResultSet resultSet;

	public ResultSet getResultSet() {
		return resultSet;
	}

	public ResultSetWork clearWarnings() {
		try {
			getResultSet().clearWarnings();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public ResultSetWork close() {
		try {
			getResultSet().close();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public int findColumn(String columnLabel) {
		try {
			return getResultSet().findColumn(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Array getArray(int columnIndex) {
		try {
			return getResultSet().getArray(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public Array getArray(String columnLabel) {
		try {
			return getResultSet().getArray(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public InputStream getAsciiStream(int columnIndex) {
		try {
			return getResultSet().getAsciiStream(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public InputStream getAsciiStream(String columnLabel) {
		try {
			return getResultSet().getAsciiStream(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public BigDecimal getBigDecimal(int columnIndex) {
		try {
			return getResultSet().getBigDecimal(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public BigDecimal getBigDecimal(String columnLabel) {
		try {
			return getResultSet().getBigDecimal(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public InputStream getBinaryStream(int columnIndex) {
		try {
			return getResultSet().getBinaryStream(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public InputStream getBinaryStream(String columnLabel) {
		try {
			return getResultSet().getBinaryStream(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Blob getBlob(int columnIndex) {
		try {
			return getResultSet().getBlob(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Blob getBlob(String columnLabel) {
		try {
			return getResultSet().getBlob(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public boolean getBoolean(int columnIndex) {
		try {
			return getResultSet().getBoolean(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public boolean getBoolean(String columnLabel) {
		try {
			return getResultSet().getBoolean(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public byte getByte(int columnIndex) {
		try {
			return getResultSet().getByte(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public byte getByte(String columnLabel) {
		try {
			return getResultSet().getByte(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public byte[] getBytes(int columnIndex) {
		try {
			return getResultSet().getBytes(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public byte[] getBytes(String columnLabel) {
		try {
			return getResultSet().getBytes(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Reader getCharacterStream(int columnIndex) {
		try {
			return getResultSet().getCharacterStream(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Reader getCharacterStream(String columnLabel) {
		try {
			return getResultSet().getCharacterStream(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Clob getClob(int columnIndex) {
		try {
			return getResultSet().getClob(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Clob getClob(String columnLabel) {
		try {
			return getResultSet().getClob(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Date getDate(int columnIndex) {
		try {
			return getResultSet().getDate(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Date getDate(int columnIndex, Calendar cal) {
		try {
			return getResultSet().getDate(columnIndex, cal);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Date getDate(String columnLabel) {
		try {
			return getResultSet().getDate(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Date getDate(String columnLabel, Calendar cal) {
		try {
			return getResultSet().getDate(columnLabel, cal);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public double getDouble(int columnIndex) {
		try {
			return getResultSet().getDouble(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public double getDouble(String columnLabel) {
		try {
			return getResultSet().getDouble(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public float getFloat(int columnIndex) {
		try {
			return getResultSet().getFloat(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public float getFloat(String columnLabel) {
		try {
			return getResultSet().getFloat(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public int getInt(int columnIndex) {
		try {
			return getResultSet().getInt(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public int getInt(String columnLabel) {
		try {
			return getResultSet().getInt(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public long getLong(int columnIndex) {
		try {
			return getResultSet().getLong(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public long getLong(String columnLabel) {
		try {
			return getResultSet().getLong(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public ResultSetMetaData getMetaData() {
		try {
			return getResultSet().getMetaData();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Reader getNCharacterStream(int columnIndex) {
		try {
			return getResultSet().getNCharacterStream(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Reader getNCharacterStream(String columnLabel) {
		try {
			return getResultSet().getNCharacterStream(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public NClob getNClob(int columnIndex) {
		try {
			return getResultSet().getNClob(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public NClob getNClob(String columnLabel) {
		try {
			return getResultSet().getNClob(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public String getNString(int columnIndex) {
		try {
			return getResultSet().getNString(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public String getNString(String columnLabel) {
		try {
			return getResultSet().getNString(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Object getObject(int columnIndex) {
		try {
			return getResultSet().getObject(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Object getObject(int columnIndex, Map<String, Class<?>> map) {
		try {
			return getResultSet().getBlob(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Object getObject(String columnLabel) {
		try {
			return getResultSet().getObject(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Object getObject(String columnLabel, Map<String, Class<?>> map) {
		try {
			return getResultSet().getObject(columnLabel, map);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Ref getRef(int columnIndex) {
		try {
			return getResultSet().getRef(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Ref getRef(String columnLabel) {
		try {
			return getResultSet().getRef(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public int getRow() {
		try {
			return getResultSet().getRow();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public RowId getRowId(int columnIndex) {
		try {
			return getResultSet().getRowId(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public RowId getRowId(String columnLabel) {
		try {
			return getResultSet().getRowId(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public short getShort(int columnIndex) {
		try {
			return getResultSet().getShort(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public short getShort(String columnLabel) {
		try {
			return getResultSet().getShort(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public SQLXML getSQLXML(int columnIndex) {
		try {
			return getResultSet().getSQLXML(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public SQLXML getSQLXML(String columnLabel) {
		try {
			return getResultSet().getSQLXML(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public String getString(int columnIndex) {
		try {
			return getResultSet().getString(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public String getString(String columnLabel) {
		try {
			return getResultSet().getString(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Time getTime(int columnIndex) {
		try {
			return getResultSet().getTime(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Time getTime(int columnIndex, Calendar cal) {
		try {
			return getResultSet().getTime(columnIndex, cal);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Time getTime(String columnLabel) {
		try {
			return getResultSet().getTime(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Time getTime(String columnLabel, Calendar cal) {
		try {
			return getResultSet().getTime(columnLabel, cal);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Timestamp getTimestamp(int columnIndex) {
		try {
			return getResultSet().getTimestamp(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Timestamp getTimestamp(int columnIndex, Calendar cal) {
		try {
			return getResultSet().getTimestamp(columnIndex, cal);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Timestamp getTimestamp(String columnLabel) {
		try {
			return getResultSet().getTimestamp(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Timestamp getTimestamp(String columnLabel, Calendar cal) {
		try {
			return getResultSet().getTimestamp(columnLabel, cal);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public URL getURL(int columnIndex) {
		try {
			return getResultSet().getURL(columnIndex);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public URL getURL(String columnLabel) {
		try {
			return getResultSet().getURL(columnLabel);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public boolean isClosed() {
		try {
			return getResultSet().isClosed();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public boolean next() {
		try {
			return getResultSet().next();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public ResultSetWork setFetchSize(int rows) {
		try {
			getResultSet().setFetchSize(rows);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public boolean wasNull() {
		try {
			return getResultSet().wasNull();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

}
