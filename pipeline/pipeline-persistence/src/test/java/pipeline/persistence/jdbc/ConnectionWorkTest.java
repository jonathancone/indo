package pipeline.persistence.jdbc;

import org.junit.Test;
import static pipeline.persistence.jdbc.ConnectionWork.*;

public class ConnectionWorkTest extends JdbcPersistenceTest {
	@Test
	public void testname() throws Exception {

		on(getConnection());

	}
}
