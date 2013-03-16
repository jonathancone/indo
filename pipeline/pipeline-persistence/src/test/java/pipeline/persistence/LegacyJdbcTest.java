package pipeline.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;


public class LegacyJdbcTest extends BasePersistenceTest {
	@Test
	public void testJdbcStatementWithResultSet() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = getDataSource().getConnection();

			ps = connection
					.prepareStatement("SELECT o.id, o.first_name, o.last_name FROM Owners o");

			rs = ps.executeQuery();

			while (rs.next()) {
				rs.getInt(1);
				rs.getString(2);
				rs.getString(3);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.close(rs);
			JdbcUtils.close(ps);
		}

	}
}
