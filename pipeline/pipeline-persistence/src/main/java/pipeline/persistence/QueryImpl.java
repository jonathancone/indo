package pipeline.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class QueryImpl extends AbstractQuery {

	private static final Log LOG = LogFactory.getLog(QueryImpl.class);

	private ConnectionProvider connectionProvider;

	public ConnectionProvider getConnectionProvider() {
		return connectionProvider;
	}

	public Connection getConnection() {
		return getConnectionProvider().getConnection();
	}

}
