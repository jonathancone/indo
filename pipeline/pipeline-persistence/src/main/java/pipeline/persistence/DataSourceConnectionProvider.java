package pipeline.persistence;

import java.sql.Connection;

import javax.sql.DataSource;

public class DataSourceConnectionProvider implements ConnectionProvider {
	private DataSource dataSource;

	public DataSourceConnectionProvider(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

}
