package pipeline.persistence.jdbc;

import pipeline.persistence.AbstractQuery;

public class JdbcQueryImpl<T> extends AbstractQuery<T> {

	public JdbcQueryImpl(String query, T root) {
		super(query, root);
	}

}
