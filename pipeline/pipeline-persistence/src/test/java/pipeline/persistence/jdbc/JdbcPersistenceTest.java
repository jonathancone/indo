package pipeline.persistence.jdbc;

import javax.sql.DataSource;

import org.unitils.database.annotations.TestDataSource;

import pipeline.persistence.AbstractPersistenceTest;
import pipeline.persistence.QueryBuilderFactory;

public class JdbcPersistenceTest extends AbstractPersistenceTest {
	@TestDataSource
	private DataSource dataSource;

	protected DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected QueryBuilderFactory createFactory() {
		return new JdbcQueryBuilderFactoryImpl(getDataSource());
	}

}
