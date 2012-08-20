package pipeline.persistence;

import java.sql.PreparedStatement;

import org.junit.Test;

import pipeline.persistence.jdbc.QuietConnection;
import static junit.framework.Assert.*;

public class DatabaseSmokeTest extends BasePersistenceTest {

	@Test
	public void testConnect() {
		QuietConnection connection = new QuietConnection(getConnection());

		assertNotNull("The test suite should have a connection.", connection);

		PreparedStatement ps = connection
				.prepareStatement("SELECT 'x' FROM information_schema.tables WHERE 1=?");

		// ps.setInt(1, 1);
		//
		// ps.executeQuery();
		//
		// ps.close();

	}

}
