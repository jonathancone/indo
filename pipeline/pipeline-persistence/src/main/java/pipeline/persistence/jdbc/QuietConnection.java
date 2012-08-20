package pipeline.persistence.jdbc;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

import pipeline.persistence.DataAccessException;

public class QuietConnection implements Connection {

	protected Connection connection;

	public QuietConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public boolean isWrapperFor(Class<?> iface) {
		try {
			return getConnection().isWrapperFor(iface);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public <T> T unwrap(Class<T> iface) {
		try {
			return getConnection().unwrap(iface);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public void clearWarnings() {
		try {
			getConnection().clearWarnings();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public void close() {
		try {
			getConnection().close();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public void commit() {
		try {
			getConnection().commit();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public Array createArrayOf(String typeName, Object[] elements) {
		try {
			return getConnection().createArrayOf(typeName, elements);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public Blob createBlob() {
		try {
			return getConnection().createBlob();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public Clob createClob() {
		try {
			return getConnection().createClob();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public NClob createNClob() {
		try {
			return getConnection().createNClob();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public SQLXML createSQLXML() {
		try {
			return getConnection().createSQLXML();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public Statement createStatement() {
		try {
			return getConnection().createStatement();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public Statement createStatement(int resultSetType, int resultSetConcurrency) {
		try {
			return getConnection().createStatement(resultSetType,
					resultSetConcurrency);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability) {
		try {
			return getConnection().createStatement(resultSetType,
					resultSetConcurrency, resultSetHoldability);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public Struct createStruct(String typeName, Object[] attributes) {
		try {
			return getConnection().createStruct(typeName, attributes);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public boolean getAutoCommit() {
		try {
			return getConnection().getAutoCommit();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public String getCatalog() {
		try {
			return getConnection().getCatalog();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public Properties getClientInfo() {
		try {
			return getConnection().getClientInfo();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public String getClientInfo(String name) {
		try {
			return getConnection().getClientInfo(name);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public int getHoldability() {
		try {
			return getConnection().getHoldability();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public DatabaseMetaData getMetaData() {
		try {
			return getConnection().getMetaData();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public int getTransactionIsolation() {
		try {
			return getConnection().getTransactionIsolation();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public Map<String, Class<?>> getTypeMap() {
		try {
			return getConnection().getTypeMap();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public SQLWarning getWarnings() {
		try {
			return getConnection().getWarnings();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public boolean isClosed() {
		try {
			return getConnection().isClosed();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public boolean isReadOnly() {
		try {
			return getConnection().isReadOnly();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public boolean isValid(int timeout) {
		try {
			return getConnection().isValid(timeout);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public String nativeSQL(String sql) {
		try {
			return getConnection().nativeSQL(sql);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public CallableStatement prepareCall(String sql) {
		try {
			return getConnection().prepareCall(sql);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) {
		try {
			return getConnection().prepareCall(sql, resultSetType,
					resultSetConcurrency);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability) {
		try {
			return getConnection().prepareCall(sql, resultSetType,
					resultSetConcurrency, resultSetHoldability);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public PreparedStatement prepareStatement(String sql) {
		try {
			return getConnection().prepareStatement(sql);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) {
		try {
			return getConnection().prepareStatement(sql, autoGeneratedKeys);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) {
		try {
			return getConnection().prepareStatement(sql, columnIndexes);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public PreparedStatement prepareStatement(String sql, String[] columnNames) {
		try {
			return getConnection().prepareStatement(sql, columnNames);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) {
		try {
			return getConnection().prepareStatement(sql, resultSetType,
					resultSetConcurrency);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability) {
		try {
			return getConnection().prepareStatement(sql, resultSetType,
					resultSetConcurrency, resultSetHoldability);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public void releaseSavepoint(Savepoint savepoint) {
		try {
			getConnection().releaseSavepoint(savepoint);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}

	}

	public void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}

	}

	public void rollback(Savepoint savepoint) {
		try {
			getConnection().rollback(savepoint);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}

	}

	public void setAutoCommit(boolean autoCommit) {
		try {
			getConnection().setAutoCommit(autoCommit);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}

	}

	public void setCatalog(String catalog) {
		try {
			getConnection().setCatalog(catalog);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}

	}

	public void setClientInfo(Properties properties) {
		try {
			getConnection().setClientInfo(properties);
		} catch (SQLClientInfoException e) {

			throw new DataAccessException(e);
		}

	}

	public void setClientInfo(String name, String value) {
		try {
			getConnection().setClientInfo(name, value);
		} catch (SQLClientInfoException e) {

			throw new DataAccessException(e);
		}

	}

	public void setHoldability(int holdability) {
		try {
			getConnection().setHoldability(holdability);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}

	}

	public void setReadOnly(boolean readOnly) {
		try {
			getConnection().setReadOnly(readOnly);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}

	}

	public Savepoint setSavepoint() {
		try {
			return getConnection().setSavepoint();
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public Savepoint setSavepoint(String name) {
		try {
			return getConnection().setSavepoint(name);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

	public void setTransactionIsolation(int level) {
		try {
			getConnection().setTransactionIsolation(level);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}

	}

	public void setTypeMap(Map<String, Class<?>> map) {
		try {
			getConnection().setTypeMap(map);
		} catch (SQLException e) {

			throw new DataAccessException(e);
		}
	}

}
