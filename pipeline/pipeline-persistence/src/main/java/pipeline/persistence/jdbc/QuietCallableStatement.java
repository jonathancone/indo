package pipeline.persistence.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class QuietCallableStatement extends QuietPreparedStatement implements
		CallableStatement {

	public QuietCallableStatement(CallableStatement callableStatement) {
		super(callableStatement);
	}

	@Override
	public CallableStatement getStatement() {

		return (CallableStatement) super.getStatement();
	}

	public Array getArray(int parameterIndex) {

		return null;
	}

	public Array getArray(String parameterName) {

		return null;
	}

	public BigDecimal getBigDecimal(int parameterIndex) {

		return null;
	}

	public BigDecimal getBigDecimal(String parameterName) {

		return null;
	}

	public BigDecimal getBigDecimal(int parameterIndex, int scale) {

		return null;
	}

	public Blob getBlob(int parameterIndex) {

		return null;
	}

	public Blob getBlob(String parameterName) {

		return null;
	}

	public boolean getBoolean(int parameterIndex) {

		return false;
	}

	public boolean getBoolean(String parameterName) {

		return false;
	}

	public byte getByte(int parameterIndex) {

		return 0;
	}

	public byte getByte(String parameterName) {

		return 0;
	}

	public byte[] getBytes(int parameterIndex) {

		return null;
	}

	public byte[] getBytes(String parameterName) {

		return null;
	}

	public Reader getCharacterStream(int parameterIndex) {

		return null;
	}

	public Reader getCharacterStream(String parameterName) {

		return null;
	}

	public Clob getClob(int parameterIndex) {

		return null;
	}

	public Clob getClob(String parameterName) {

		return null;
	}

	public Date getDate(int parameterIndex) {

		return null;
	}

	public Date getDate(String parameterName) {

		return null;
	}

	public Date getDate(int parameterIndex, Calendar cal) {

		return null;
	}

	public Date getDate(String parameterName, Calendar cal) {

		return null;
	}

	public double getDouble(int parameterIndex) {

		return 0;
	}

	public double getDouble(String parameterName) {

		return 0;
	}

	public float getFloat(int parameterIndex) {

		return 0;
	}

	public float getFloat(String parameterName) {

		return 0;
	}

	public int getInt(int parameterIndex) {

		return 0;
	}

	public int getInt(String parameterName) {

		return 0;
	}

	public long getLong(int parameterIndex) {

		return 0;
	}

	public long getLong(String parameterName) {

		return 0;
	}

	public Reader getNCharacterStream(int parameterIndex) {

		return null;
	}

	public Reader getNCharacterStream(String parameterName) {

		return null;
	}

	public NClob getNClob(int parameterIndex) {

		return null;
	}

	public NClob getNClob(String parameterName) {

		return null;
	}

	public String getNString(int parameterIndex) {

		return null;
	}

	public String getNString(String parameterName) {

		return null;
	}

	public Object getObject(int parameterIndex) {

		return null;
	}

	public Object getObject(String parameterName) {

		return null;
	}

	public Object getObject(int parameterIndex, Map<String, Class<?>> map) {

		return null;
	}

	public Object getObject(String parameterName, Map<String, Class<?>> map) {

		return null;
	}

	public Ref getRef(int parameterIndex) {

		return null;
	}

	public Ref getRef(String parameterName) {

		return null;
	}

	public RowId getRowId(int parameterIndex) {

		return null;
	}

	public RowId getRowId(String parameterName) {

		return null;
	}

	public SQLXML getSQLXML(int parameterIndex) {

		return null;
	}

	public SQLXML getSQLXML(String parameterName) {

		return null;
	}

	public short getShort(int parameterIndex) {

		return 0;
	}

	public short getShort(String parameterName) {

		return 0;
	}

	public String getString(int parameterIndex) {

		return null;
	}

	public String getString(String parameterName) {

		return null;
	}

	public Time getTime(int parameterIndex) {

		return null;
	}

	public Time getTime(String parameterName) {

		return null;
	}

	public Time getTime(int parameterIndex, Calendar cal) {

		return null;
	}

	public Time getTime(String parameterName, Calendar cal) {

		return null;
	}

	public Timestamp getTimestamp(int parameterIndex) {

		return null;
	}

	public Timestamp getTimestamp(String parameterName) {

		return null;
	}

	public Timestamp getTimestamp(int parameterIndex, Calendar cal) {

		return null;
	}

	public Timestamp getTimestamp(String parameterName, Calendar cal) {

		return null;
	}

	public URL getURL(int parameterIndex) {

		return null;
	}

	public URL getURL(String parameterName) {

		return null;
	}

	public void registerOutParameter(int parameterIndex, int sqlType) {

	}

	public void registerOutParameter(String parameterName, int sqlType) {

	}

	public void registerOutParameter(int parameterIndex, int sqlType, int scale) {

	}

	public void registerOutParameter(int parameterIndex, int sqlType,
			String typeName) {

	}

	public void registerOutParameter(String parameterName, int sqlType,
			int scale) {

	}

	public void registerOutParameter(String parameterName, int sqlType,
			String typeName) {

	}

	public void setAsciiStream(String parameterName, InputStream x) {

	}

	public void setAsciiStream(String parameterName, InputStream x, int length) {

	}

	public void setAsciiStream(String parameterName, InputStream x, long length) {

	}

	public void setBigDecimal(String parameterName, BigDecimal x) {

	}

	public void setBinaryStream(String parameterName, InputStream x) {

	}

	public void setBinaryStream(String parameterName, InputStream x, int length) {

	}

	public void setBinaryStream(String parameterName, InputStream x, long length) {

	}

	public void setBlob(String parameterName, Blob x) {

	}

	public void setBlob(String parameterName, InputStream inputStream) {

	}

	public void setBlob(String parameterName, InputStream inputStream,
			long length) {

	}

	public void setBoolean(String parameterName, boolean x) {

	}

	public void setByte(String parameterName, byte x) {

	}

	public void setBytes(String parameterName, byte[] x) {

	}

	public void setCharacterStream(String parameterName, Reader reader) {

	}

	public void setCharacterStream(String parameterName, Reader reader,
			int length) {

	}

	public void setCharacterStream(String parameterName, Reader reader,
			long length) {

	}

	public void setClob(String parameterName, Clob x) {

	}

	public void setClob(String parameterName, Reader reader) {

	}

	public void setClob(String parameterName, Reader reader, long length) {

	}

	public void setDate(String parameterName, Date x) {

	}

	public void setDate(String parameterName, Date x, Calendar cal) {

	}

	public void setDouble(String parameterName, double x) {

	}

	public void setFloat(String parameterName, float x) {

	}

	public void setInt(String parameterName, int x) {

	}

	public void setLong(String parameterName, long x) {

	}

	public void setNCharacterStream(String parameterName, Reader value) {

	}

	public void setNCharacterStream(String parameterName, Reader value,
			long length) {

	}

	public void setNClob(String parameterName, NClob value) {

	}

	public void setNClob(String parameterName, Reader reader) {

	}

	public void setNClob(String parameterName, Reader reader, long length) {

	}

	public void setNString(String parameterName, String value) {

	}

	public void setNull(String parameterName, int sqlType) {

	}

	public void setNull(String parameterName, int sqlType, String typeName) {

	}

	public void setObject(String parameterName, Object x) {

	}

	public void setObject(String parameterName, Object x, int targetSqlType) {

	}

	public void setObject(String parameterName, Object x, int targetSqlType,
			int scale) {

	}

	public void setRowId(String parameterName, RowId x) {

	}

	public void setSQLXML(String parameterName, SQLXML xmlObject) {

	}

	public void setShort(String parameterName, short x) {

	}

	public void setString(String parameterName, String x) {

	}

	public void setTime(String parameterName, Time x) {

	}

	public void setTime(String parameterName, Time x, Calendar cal) {

	}

	public void setTimestamp(String parameterName, Timestamp x) {

	}

	public void setTimestamp(String parameterName, Timestamp x, Calendar cal) {

	}

	public void setURL(String parameterName, URL val) {

	}

	public boolean wasNull() {

		return false;
	}

}
