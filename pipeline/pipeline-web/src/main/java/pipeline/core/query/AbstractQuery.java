package pipeline.core.query;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

public abstract class AbstractQuery<T> implements Query<T> {
	private DataSource dataSource;
	private Connection connection;

	public AbstractQuery() {
	}

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
			connection = DataSourceUtils.getConnection(getDataSource());
		}
		return connection;
	}

}
