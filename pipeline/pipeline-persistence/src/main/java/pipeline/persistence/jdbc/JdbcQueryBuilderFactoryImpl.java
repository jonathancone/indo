package pipeline.persistence.jdbc;

import javax.sql.DataSource;

import pipeline.persistence.QueryBuilder;
import pipeline.persistence.QueryBuilderFactory;

public class JdbcQueryBuilderFactoryImpl implements QueryBuilderFactory {
	private DataSource dataSource;

	public JdbcQueryBuilderFactoryImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected DataSource getDataSource() {
		return dataSource;
	}

	public QueryBuilder getQueryBuilder() {
		return new JdbcQueryBuilderImpl(getDataSource());
	}

}
