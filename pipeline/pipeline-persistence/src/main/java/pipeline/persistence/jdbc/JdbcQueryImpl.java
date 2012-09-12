package pipeline.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pipeline.persistence.AbstractQuery;
import pipeline.persistence.Query;

public class JdbcQueryImpl<T> extends AbstractQuery<T> {

	private static final Log LOG = LogFactory.getLog(JdbcQueryImpl.class);

	private ConnectionProvider connectionProvider;

	public JdbcQueryImpl(ConnectionProvider connectionProvider, Class<T> type) {
		super(type);
		this.connectionProvider = connectionProvider;
	}

	public JdbcQueryImpl(ConnectionProvider connectionProvider, T root) {
		super(root);
		this.connectionProvider = connectionProvider;
	}

	@Override
	public List<T> asList() {

		return super.asList();
	}

	private void buildParameters() {

	}

	public ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public Connection getConnection() {
		return getConnectionProvider().getConnection();
	}

}
