package pipeline.persistence;

import javax.sql.DataSource;


public class QueryFactoryImpl implements QueryFactory {

	private ConnectionProvider connectionProvider;

	public QueryFactoryImpl(DataSource dataSource) {
		this(new DataSourceConnectionProvider(dataSource));
	}

	public QueryFactoryImpl(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;
	}

	protected ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public Query withSQL(String sql) {
		return null;
	}

}
