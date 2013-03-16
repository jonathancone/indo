package pipeline.persistence;

import java.sql.Connection;

public interface ConnectionProvider {
	Connection getConnection();
}
