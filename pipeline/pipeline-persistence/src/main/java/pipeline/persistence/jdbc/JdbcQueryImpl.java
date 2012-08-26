package pipeline.persistence.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pipeline.persistence.AbstractQuery;

public class JdbcQueryImpl<T> extends AbstractQuery<T> {
	private ConnectionProvider connectionProvider;

	// Constructors

	public JdbcQueryImpl(ConnectionProvider connectionProvider, Class<T> type) {
		super(type);
		this.connectionProvider = connectionProvider;
	}

	public JdbcQueryImpl(ConnectionProvider connectionProvider, T root) {
		super(root);
		this.connectionProvider = connectionProvider;
	}

	// Logic

	@Override
	public List<T> asList() {

		Connection connection = getConnection();

		try {
			connection.prepareStatement(getQuery());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return super.asList();
	}

	// Accessors

	public ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public Connection getConnection() {
		return getConnectionProvider().getConnection();
	}

}
