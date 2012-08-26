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
	// Member methods
	// ==============================================================
	public PreparedStatement stmt() {
		return stmt;
	}

	public PreparedWork cancel() {
		try {
			stmt().cancel();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork clearBatch() {
		try {
			stmt().clearBatch();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork clearWarnings() {
		try {
			stmt().clearWarnings();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork close() {
		try {
			stmt().close();
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public int[] executeBatch() {
		try {
			return stmt().executeBatch();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public Connection getConnection() {
		try {
			return stmt().getConnection();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public ResultSet getGeneratedKeys() {
		try {
			return stmt().getGeneratedKeys();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public boolean getMoreResults() {
		try {
			return stmt().getMoreResults();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public boolean getMoreResults(int current) {
		try {
			return stmt().getMoreResults(current);
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public ResultSet getResultSet() {
		try {
			return stmt().getResultSet();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public int getResultSetType() {
		try {
			return stmt().getResultSetType();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public int getUpdateCount() {
		try {
			return stmt().getUpdateCount();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public SQLWarning getWarnings() {
		try {
			return stmt().getWarnings();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setCursorName(String name) {
		try {
			stmt().setCursorName(name);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setEscapeProcessing(boolean enable) {
		try {
			stmt().setEscapeProcessing(enable);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setFetchDirection(int direction) {
		try {
			stmt().setFetchDirection(direction);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setFetchSize(int rows) {
		try {
			stmt().setFetchSize(rows);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setMaxFieldSize(int max) {
		try {
			stmt().setMaxFieldSize(max);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setMaxRows(int max) {
		try {
			stmt().setMaxRows(max);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setPoolable(boolean poolable) {
		try {
			stmt().setPoolable(poolable);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public PreparedWork setQueryTimeout(int seconds) {
		try {
			stmt().setQueryTimeout(seconds);
			return this;
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public ResultSet executeQuery() {
		try {

			return stmt().executeQuery();

		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public int executeUpdate() {
		try {
			return stmt().executeUpdate();
		} catch (SQLException e) {
			throw new JdbcException(e);
		}
	}

	public void setNull(int parameterIndex, int sqlType) {
		try {

		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setBoolean(int parameterIndex, boolean x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setByte(int parameterIndex, byte x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setShort(int parameterIndex, short x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setInt(int parameterIndex, int x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setLong(int parameterIndex, long x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setFloat(int parameterIndex, float x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setDouble(int parameterIndex, double x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setBigDecimal(int parameterIndex, BigDecimal x)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setString(int parameterIndex, String x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setBytes(int parameterIndex, byte[] x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setDate(int parameterIndex, Date x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setTime(int parameterIndex, Time x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setTimestamp(int parameterIndex, Timestamp x)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setAsciiStream(int parameterIndex, InputStream x, int length)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setUnicodeStream(int parameterIndex, InputStream x, int length)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setBinaryStream(int parameterIndex, InputStream x, int length)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void clearParameters() {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setObject(int parameterIndex, Object x, int targetSqlType)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setObject(int parameterIndex, Object x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public boolean execute() {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

		return false;
	}

	public void addBatch() {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setCharacterStream(int parameterIndex, Reader reader, int length)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setRef(int parameterIndex, Ref x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setBlob(int parameterIndex, Blob x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setClob(int parameterIndex, Clob x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setArray(int parameterIndex, Array x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public ResultSetMetaData getMetaData() {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

		return null;
	}

	public void setDate(int parameterIndex, Date x, Calendar cal)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setTime(int parameterIndex, Time x, Calendar cal)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal)
	{

	}

	public void setNull(int parameterIndex, int sqlType, String typeName)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setURL(int parameterIndex, URL x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public ParameterMetaData getParameterMetaData() {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

		return null;
	}

	public void setRowId(int parameterIndex, RowId x) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setNString(int parameterIndex, String value)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setNCharacterStream(int parameterIndex, Reader value,
			long length) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setNClob(int parameterIndex, NClob value) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setClob(int parameterIndex, Reader reader, long length)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setBlob(int parameterIndex, InputStream inputStream, long length)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setNClob(int parameterIndex, Reader reader, long length)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setSQLXML(int parameterIndex, SQLXML xmlObject)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setObject(int parameterIndex, Object x, int targetSqlType,
			int scaleOrLength) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setAsciiStream(int parameterIndex, InputStream x, long length)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setBinaryStream(int parameterIndex, InputStream x, long length)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setCharacterStream(int parameterIndex, Reader reader,
			long length) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setAsciiStream(int parameterIndex, InputStream x)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setBinaryStream(int parameterIndex, InputStream x)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setCharacterStream(int parameterIndex, Reader reader)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setNCharacterStream(int parameterIndex, Reader value)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setClob(int parameterIndex, Reader reader) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setBlob(int parameterIndex, InputStream inputStream)
	{
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}

	public void setNClob(int parameterIndex, Reader reader) {
		try {
		} catch (SQLException e) {
			throw new JdbcException(e);
		}

	}
}
