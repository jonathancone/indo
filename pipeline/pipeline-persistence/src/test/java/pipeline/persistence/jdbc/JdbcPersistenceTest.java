package pipeline.persistence.jdbc;

import java.sql.Connection;

import javax.sql.DataSource;

import org.unitils.database.annotations.TestDataSource;

import pipeline.persistence.AbstractPersistenceTest;
import pipeline.persistence.QueryFactory;

public class JdbcPersistenceTest extends AbstractPersistenceTest {
	@TestDataSource
	private DataSource dataSource;

	protected Connection connection;

	protected DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected QueryFactory createFactory() {
		return new JdbcQueryFactoryImpl(getDataSource());
	}

	protected Connection getConnection() {
		connection = DataSourceUtils.getConnection(getDataSource());

		return connection;
	}
}
