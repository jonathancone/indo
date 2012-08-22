package pipeline.persistence.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementCallback {
	void execute(Connection con, PreparedStatement ps) throws SQLException;
}
