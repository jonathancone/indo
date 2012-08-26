package pipeline.persistence.jdbc;

import org.junit.Test;

public class PreparedWorkTest extends JdbcPersistenceTest {

	@Test
	public void testname() throws Exception {
		ConnectionWork.
				on(connection)
				.prepare("  SELECT                             "
						+ "   o.id,                            "
						+ "   o.first_name  AS firstName,      "
						+ "   o.last_name   AS lastName,       "
						+ "   o.address,                       "
						+ "   o.city,                          "
						+ "   o.telephone,                     "
						+ "   p.id          AS pets_id,        "
						+ "   p.name        AS pets_name,      "
						+ "   p.birth_date  AS pets_birthDate, "
						+ "   p.type_id     AS pets_typeId,    "
						+ "   p.owner_id    AS pets_ownerId    "
						+ " FROM                               "
						+ "   Owners o                         "
						+ " INNER JOIN                         "
						+ "   Pets p                           "
						+ " ON                                 "
						+ "   o.id = p.owner_id                "
						+ " WHERE                              "
						+ "   o.last_name = ?                  "
						+ "   AND o.first_name = ?             ")
						.setString(1, "Murphy")
						.setString(2,"Jack")
				.executeQuery();
	}
}
