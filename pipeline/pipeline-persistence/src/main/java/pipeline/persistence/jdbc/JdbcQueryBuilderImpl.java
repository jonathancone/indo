package pipeline.persistence.jdbc;

import javax.sql.DataSource;

import pipeline.persistence.AbstractQueryBuilder;
import pipeline.persistence.ParameterSource;
import pipeline.persistence.Query;
import pipeline.persistence.QueryBuilderFactory;

public class JdbcQueryBuilderImpl extends AbstractQueryBuilder {
	private DataSource dataSource;
	private QueryBuilderFactory queryFactory;

	public JdbcQueryBuilderImpl(DataSource dataSource) {
		this(dataSource, null);
	}

	public JdbcQueryBuilderImpl(DataSource dataSource,
			QueryBuilderFactory queryFactory) {
		this.dataSource = dataSource;
		this.queryFactory = queryFactory;
	}

	public <T> Query<T> createQuery(String query, Class<T> root) {
		return null;
	}

	public <T> Query<T> createQuery(String query, Class<T> root,
			Object... params) {
		return null;
	}

	public <T> Query<T> createQuery(String query, Class<T> root,
			ParameterSource params) {
		return null;
	}

	protected DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected QueryBuilderFactory getQueryFactory() {
		return queryFactory;
	}

	public void setQueryFactory(QueryBuilderFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

}
