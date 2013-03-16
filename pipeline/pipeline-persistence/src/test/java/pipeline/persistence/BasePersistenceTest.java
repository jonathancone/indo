package pipeline.persistence;

import java.sql.Connection;

import javax.sql.DataSource;

import org.unitils.database.annotations.TestDataSource;

import pipeline.persistence.QueryFactory;
import pipeline.persistence.QueryFactoryImpl;

public class BasePersistenceTest extends AbstractPersistenceTest {
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
		return new QueryFactoryImpl(getDataSource());
	}

	protected Connection getConnection() {
		connection = DataSourceUtils.getConnection(getDataSource());

		return connection;
	}
}
