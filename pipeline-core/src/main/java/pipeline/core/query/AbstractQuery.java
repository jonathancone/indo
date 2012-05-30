package pipeline.core.query;

import java.sql.Connection;

import javax.sql.DataSource;

public abstract class AbstractQuery implements Query {
	private DataSource dataSource;
	private Connection connection;

	public AbstractQuery(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public AbstractQuery(Connection connection) {
		this.connection = connection;
	}

	protected DataSource getDataSource() {
		return dataSource;
	}

	protected Connection getConnection() {
		if (connection == null) {
			connection = DataSourceUtils.getConnection();
		}
		return connection;
	}

}
