package pipeline.persistence.jdbc;

import javax.sql.DataSource;

import pipeline.persistence.AbstractQueryFactory;
import pipeline.persistence.Query;

public class JdbcQueryFactoryImpl extends AbstractQueryFactory {

	private ConnectionProvider connectionProvider;

	public JdbcQueryFactoryImpl(DataSource dataSource) {
		this(new DataSourceConnectionProvider(dataSource));
	}

	public JdbcQueryFactoryImpl(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	protected ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public <T> Query<T> on(Class<T> type) {
		return new JdbcQueryImpl<T>(getConnectionProvider(), type);
	}

	public <T> Query<T> on(T root) {
		return new JdbcQueryImpl<T>(getConnectionProvider(), root);
	}

}
