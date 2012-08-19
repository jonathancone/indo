package pipeline.core.query;

import java.sql.Connection;

import javax.sql.DataSource;

import pipeline.core.ResultSetMapper;

public class SelectQuery<T> extends AbstractQuery<T> {
	public SelectQuery() {
		super();
	}

	public SelectQuery(Connection connection) {
		super(connection);

	}

	public SelectQuery(DataSource dataSource) {
		super(dataSource);
	}

	public T run(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public T run(String query, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	public T run(String query, ResultSetMapper<T> mapper, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
