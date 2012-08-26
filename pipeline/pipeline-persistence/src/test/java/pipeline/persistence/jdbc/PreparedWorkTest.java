package pipeline.persistence.jdbc;

import java.sql.ResultSet;

import org.junit.Test;
import static pipeline.persistence.jdbc.ConnectionWork.*;

public class PreparedWorkTest extends JdbcPersistenceTest {

	@Test
	public void testname() throws Exception {
		ResultSet rs = on(connection)
				.prepare("  SELECT                             "
						+ "   o.id,                            "
						+ "   o.first_name,                    "
						+ "   o.last_name,                     "
						+ "   o.address,                       "
						+ "   o.city,                          "
						+ "   o.telephone                      "
						+ " FROM                               "
						+ "   Owners o                         "
						+ " INNER JOIN                         "
						+ "   Pets p                           "
						+ " ON                                 "
						+ "   o.id = p.owner_id                "
						+ " WHERE                              "
						+ "   o.last_name = ?                  "
						+ "   AND o.first_name = ?             ")
				.setString(1, "Henderson")
				.setString(2, "Bill")
				.executeQuery();

		while (rs.next()) {

			rs.getInt(1);
			rs.getString(2);
			rs.getString(3);
			rs.getString(4);
			rs.getString(5);
			rs.getString(6);

		}
	}
}
