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
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import pipeline.persistence.DataAccessException;

public class QuietPreparedStatement extends QuietStatement implements
		PreparedStatement {

	public QuietPreparedStatement(PreparedStatement preparedStatement) {
		super(preparedStatement);
	}

	@Override
	public PreparedStatement getStatement() {
		return (PreparedStatement) super.getStatement();
	}

	public void addBatch() {
		try {
			getStatement().addBatch();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void clearParameters() {
		try {
			getStatement().clearParameters();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public boolean execute() {
		try {
			return getStatement().execute();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public ResultSet executeQuery() {
		try {
			return getStatement().executeQuery();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public int executeUpdate() {
		try {
			return getStatement().executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public ResultSetMetaData getMetaData() {
		try {
			return getStatement().getMetaData();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public ParameterMetaData getParameterMetaData() {
		try {
			return getStatement().getParameterMetaData();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setArray(int parameterIndex, Array x) {
		try {
			getStatement().setArray(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setAsciiStream(int parameterIndex, InputStream x) {
		try {
			getStatement().setAsciiStream(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setAsciiStream(int parameterIndex, InputStream x, int length) {
		try {
			getStatement().setAsciiStream(parameterIndex, x, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setAsciiStream(int parameterIndex, InputStream x, long length) {
		try {
			getStatement().setAsciiStream(parameterIndex, x, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setBigDecimal(int parameterIndex, BigDecimal x) {
		try {
			getStatement().setBigDecimal(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setBinaryStream(int parameterIndex, InputStream x) {
		try {
			getStatement().setBinaryStream(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setBinaryStream(int parameterIndex, InputStream x, int length) {
		try {
			getStatement().setBinaryStream(parameterIndex, x, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setBinaryStream(int parameterIndex, InputStream x, long length) {
		try {
			getStatement().setBinaryStream(parameterIndex, x, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setBlob(int parameterIndex, Blob x) {
		try {
			getStatement().setBlob(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setBlob(int parameterIndex, InputStream inputStream) {
		try {
			getStatement().setBlob(parameterIndex, inputStream);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setBlob(int parameterIndex, InputStream inputStream, long length) {
		try {
			getStatement().setBlob(parameterIndex, inputStream, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setBoolean(int parameterIndex, boolean x) {
		try {
			getStatement().setBoolean(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}

	}

	public void setByte(int parameterIndex, byte x) {
		try {
			getStatement().setByte(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setBytes(int parameterIndex, byte[] x) {
		try {
			getStatement().setBytes(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setCharacterStream(int parameterIndex, Reader reader) {
		try {
			getStatement().setCharacterStream(parameterIndex, reader);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setCharacterStream(int parameterIndex, Reader reader, int length) {
		try {
			getStatement().setCharacterStream(parameterIndex, reader, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setCharacterStream(int parameterIndex, Reader reader,
			long length) {
		try {
			getStatement().setCharacterStream(parameterIndex, reader, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setClob(int parameterIndex, Clob x) {
		try {
			getStatement().setClob(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setClob(int parameterIndex, Reader reader) {
		try {
			getStatement().setClob(parameterIndex, reader);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setClob(int parameterIndex, Reader reader, long length) {
		try {
			getStatement().setClob(parameterIndex, reader, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setDate(int parameterIndex, Date x) {
		try {
			getStatement().setDate(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setDate(int parameterIndex, Date x, Calendar cal) {
		try {
			getStatement().setDate(parameterIndex, x, cal);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setDouble(int parameterIndex, double x) {
		try {
			getStatement().setDouble(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setFloat(int parameterIndex, float x) {
		try {
			getStatement().setFloat(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setInt(int parameterIndex, int x) {
		try {
			getStatement().setInt(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setLong(int parameterIndex, long x) {
		try {
			getStatement().setLong(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setNCharacterStream(int parameterIndex, Reader value) {
		try {
			getStatement().setNCharacterStream(parameterIndex, value);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setNCharacterStream(int parameterIndex, Reader value,
			long length) {
		try {
			getStatement().setNCharacterStream(parameterIndex, value, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setNClob(int parameterIndex, NClob value) {
		try {
			getStatement().setNClob(parameterIndex, value);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setNClob(int parameterIndex, Reader reader) {
		try {
			getStatement().setNClob(parameterIndex, reader);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setNClob(int parameterIndex, Reader reader, long length) {
		try {
			getStatement().setNClob(parameterIndex, reader, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setNString(int parameterIndex, String value) {
		try {
			getStatement().setNString(parameterIndex, value);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setNull(int parameterIndex, int sqlType) {
		try {
			getStatement().setNull(parameterIndex, sqlType);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setNull(int parameterIndex, int sqlType, String typeName) {
		try {
			getStatement().setNull(parameterIndex, sqlType, typeName);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setObject(int parameterIndex, Object x) {
		try {
			getStatement().setObject(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType) {
		try {
			getStatement().setObject(parameterIndex, x, targetSqlType);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setObject(int parameterIndex, Object x, int targetSqlType,
			int scaleOrLength) {
		try {
			getStatement().setObject(parameterIndex, x, targetSqlType,
					scaleOrLength);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setRef(int parameterIndex, Ref x) {
		try {
			getStatement().setRef(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setRowId(int parameterIndex, RowId x) {
		try {
			getStatement().setRowId(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setSQLXML(int parameterIndex, SQLXML xmlObject) {
		try {
			getStatement().setSQLXML(parameterIndex, xmlObject);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setShort(int parameterIndex, short x) {
		try {
			getStatement().setShort(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setString(int parameterIndex, String x) {
		try {
			getStatement().setString(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setTime(int parameterIndex, Time x) {
		try {
			getStatement().setTime(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setTime(int parameterIndex, Time x, Calendar cal) {
		try {
			getStatement().setTime(parameterIndex, x, cal);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setTimestamp(int parameterIndex, Timestamp x) {
		try {
			getStatement().setTimestamp(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) {
		try {
			getStatement().setTimestamp(parameterIndex, x, cal);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setURL(int parameterIndex, URL x) {
		try {
			getStatement().setURL(parameterIndex, x);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public void setUnicodeStream(int parameterIndex, InputStream x, int length) {
		try {
			getStatement().setUnicodeStream(parameterIndex, x, length);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

}
