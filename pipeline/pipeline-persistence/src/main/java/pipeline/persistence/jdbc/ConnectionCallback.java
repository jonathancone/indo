package pipeline.persistence.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionCallback {
	void execute(Connection con) throws SQLException;
}
