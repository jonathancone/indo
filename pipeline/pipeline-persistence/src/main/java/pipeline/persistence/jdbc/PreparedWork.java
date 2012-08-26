package pipeline.persistence.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public class PreparedWork {

	private PreparedStatement stmt;

	// ==============================================================
	// Constructors
	// ==============================================================
	protected PreparedWork(PreparedStatement stmt) {
		this.stmt = stmt;
	}

	// ==============================================================
	// Static methods
	// ==============================================================

	public static PreparedWork on(PreparedStatement stmt) {
		return new PreparedWork(stmt);
	}

	// ==============================================================
	// Methods
	// ==============================================================
	public PreparedWork addBatch() {
		try {
			getPreparedStatement().addBatch();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork cancel() {
		try {
			getPreparedStatement().cancel();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork clearBatch() {
		try {
			getPreparedStatement().clearBatch();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork clearParameters() {
		try {
			getPreparedStatement().clearParameters();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork clearWarnings() {
		try {
			getPreparedStatement().clearWarnings();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork close() {
		try {
			getPreparedStatement().close();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public boolean execute() {
		try {
			getPreparedStatement().execute();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

		return false;
	}

	public int[] executeBatch() {
		try {
			return getPreparedStatement().executeBatch();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public ResultSet executeQuery() {
		try {

			return getPreparedStatement().executeQuery();

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public int executeUpdate() {
		try {
			return getPreparedStatement().executeUpdate();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Connection getConnection() {
		try {
			return getPreparedStatement().getConnection();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public ResultSet getGeneratedKeys() {
		try {
			return getPreparedStatement().getGeneratedKeys();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public ResultSetMetaData getMetaData() {
		try {
			return getPreparedStatement().getMetaData();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public boolean getMoreResults() {
		try {
			return getPreparedStatement().getMoreResults();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public boolean getMoreResults(int current) {
		try {
			return getPreparedStatement().getMoreResults(current);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedStatement getPreparedStatement() {
		return stmt;
	}

	public ResultSet getResultSet() {
		try {
			return getPreparedStatement().getResultSet();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public int getResultSetType() {
		try {
			return getPreparedStatement().getResultSetType();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public int getUpdateCount() {
		try {
			return getPreparedStatement().getUpdateCount();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public SQLWarning getWarnings() {
		try {
			return getPreparedStatement().getWarnings();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setArray(int parameterIndex, Array x) {
		try {
			getPreparedStatement().setArray(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setAsciiStream(int parameterIndex, InputStream x) {
		try {
			getPreparedStatement().setAsciiStream(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setAsciiStream(int parameterIndex, InputStream x,
			int length) {
		try {
			getPreparedStatement().setAsciiStream(parameterIndex, x, length);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setAsciiStream(int parameterIndex, InputStream x,
			long length) {
		try {
			getPreparedStatement().setAsciiStream(parameterIndex, x, length);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setBigDecimal(int parameterIndex, BigDecimal x) {
		try {
			getPreparedStatement().setBigDecimal(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setBinaryStream(int parameterIndex, InputStream x) {
		try {
			getPreparedStatement().setBinaryStream(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setBinaryStream(int parameterIndex, InputStream x,
			int length) {
		try {
			getPreparedStatement().setBinaryStream(parameterIndex, x, length);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setBinaryStream(int parameterIndex, InputStream x,
			long length) {
		try {
			getPreparedStatement().setBinaryStream(parameterIndex, x, length);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setBlob(int parameterIndex, Blob x) {
		try {
			getPreparedStatement().setBlob(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setBlob(int parameterIndex, InputStream inputStream) {
		try {
			getPreparedStatement().setBlob(parameterIndex, inputStream);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setBlob(int parameterIndex, InputStream inputStream,
			long length) {
		try {
			getPreparedStatement().setBlob(parameterIndex, inputStream, length);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setBoolean(int parameterIndex, boolean x) {
		try {
			getPreparedStatement().setBoolean(parameterIndex, x);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setByte(int parameterIndex, byte x) {
		try {
			getPreparedStatement().setByte(parameterIndex, x);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setBytes(int parameterIndex, byte[] x) {
		try {
			getPreparedStatement().setBytes(parameterIndex, x);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setCharacterStream(int parameterIndex, Reader reader) {
		try {
			getPreparedStatement().setCharacterStream(parameterIndex, reader);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setCharacterStream(int parameterIndex, Reader reader,
			int length) {
		try {
			getPreparedStatement().setCharacterStream(parameterIndex, reader,
					length);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setCharacterStream(int parameterIndex, Reader reader,
			long length) {
		try {
			getPreparedStatement().setCharacterStream(parameterIndex, reader,
					length);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setClob(int parameterIndex, Clob x) {
		try {
			getPreparedStatement().setClob(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setClob(int parameterIndex, Reader reader) {
		try {
			getPreparedStatement().setClob(parameterIndex, reader);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setClob(int parameterIndex, Reader reader, long length) {
		try {
			getPreparedStatement().setClob(parameterIndex, reader, length);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setCursorName(String name) {
		try {
			getPreparedStatement().setCursorName(name);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setDate(int parameterIndex, Date x) {
		try {
			getPreparedStatement().setDate(parameterIndex, x);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setDate(int parameterIndex, Date x, Calendar cal) {
		try {
			getPreparedStatement().setDate(parameterIndex, x, cal);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setDouble(int parameterIndex, double x) {
		try {
			getPreparedStatement().setDouble(parameterIndex, x);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setEscapeProcessing(boolean enable) {
		try {
			getPreparedStatement().setEscapeProcessing(enable);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setFetchDirection(int direction) {
		try {
			getPreparedStatement().setFetchDirection(direction);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setFetchSize(int rows) {
		try {
			getPreparedStatement().setFetchSize(rows);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setFloat(int parameterIndex, float x) {
		try {
			getPreparedStatement().setFloat(parameterIndex, x);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setInt(int parameterIndex, int x) {
		try {
			getPreparedStatement().setInt(parameterIndex, x);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setLong(int parameterIndex, long x) {
		try {
			getPreparedStatement().setLong(parameterIndex, x);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setMaxFieldSize(int max) {
		try {
			getPreparedStatement().setMaxFieldSize(max);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setMaxRows(int max) {
		try {
			getPreparedStatement().setMaxRows(max);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setNCharacterStream(int parameterIndex, Reader value) {
		try {
			getPreparedStatement().setNCharacterStream(parameterIndex, value);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setNCharacterStream(int parameterIndex, Reader value,
			long length) {
		try {
			getPreparedStatement().setNCharacterStream(parameterIndex, value,
					length);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setNClob(int parameterIndex, NClob value) {
		try {
			getPreparedStatement().setNClob(parameterIndex, value);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setNClob(int parameterIndex, Reader reader) {
		try {
			getPreparedStatement().setNClob(parameterIndex, reader);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setNClob(int parameterIndex, Reader reader, long length) {
		try {
			getPreparedStatement().setNClob(parameterIndex, reader, length);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public PreparedWork setNString(int parameterIndex, String value) {
		try {
			getPreparedStatement().setNString(parameterIndex, value);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setNull(int parameterIndex, int sqlType) {
		try {
			getPreparedStatement().setNull(parameterIndex, sqlType);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setNull(int parameterIndex, int sqlType, String typeName) {
		try {
			getPreparedStatement().setNull(parameterIndex, sqlType, typeName);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setObject(int parameterIndex, Object x) {
		try {
			getPreparedStatement().setObject(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setObject(int parameterIndex, Object x,
			int targetSqlType) {
		try {
			getPreparedStatement().setObject(parameterIndex, x, targetSqlType);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setObject(int parameterIndex, Object x,
			int targetSqlType,
			int scaleOrLength) {
		try {
			getPreparedStatement().setObject(parameterIndex, x, targetSqlType,
					scaleOrLength);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setPoolable(boolean poolable) {
		try {
			getPreparedStatement().setPoolable(poolable);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setQueryTimeout(int seconds) {
		try {
			getPreparedStatement().setQueryTimeout(seconds);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setRef(int parameterIndex, Ref x) {
		try {
			getPreparedStatement().setRef(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setRowId(int parameterIndex, RowId x) {
		try {
			getPreparedStatement().setRowId(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setShort(int parameterIndex, short x) {
		try {
			getPreparedStatement().setShort(parameterIndex, x);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setSQLXML(int parameterIndex, SQLXML xmlObject)
	{
		try {
			getPreparedStatement().setSQLXML(parameterIndex, xmlObject);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setString(int parameterIndex, String x) {
		try {
			getPreparedStatement().setString(parameterIndex, x);
			return this;

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setTime(int parameterIndex, Time x) {
		try {
			getPreparedStatement().setTime(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setTime(int parameterIndex, Time x, Calendar cal)
	{
		try {
			getPreparedStatement().setTime(parameterIndex, x, cal);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setTimestamp(int parameterIndex, Timestamp x)
	{
		try {
			getPreparedStatement().setTimestamp(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setTimestamp(int parameterIndex, Timestamp x,
			Calendar cal) {
		try {
			getPreparedStatement().setTimestamp(parameterIndex, x, cal);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setURL(int parameterIndex, URL x) {
		try {
			getPreparedStatement().setURL(parameterIndex, x);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}
}
