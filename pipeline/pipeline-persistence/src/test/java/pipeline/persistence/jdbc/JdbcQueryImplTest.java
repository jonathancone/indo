package pipeline.persistence.jdbc;

import java.util.List;

import org.junit.Test;

import pipeline.persistence.Query;
import pipeline.persistence.domain.Pet;

import static org.junit.Assert.*;

public class JdbcQueryImplTest extends JdbcPersistenceTest {
	@Test
	public void testname() throws Exception {

		List<Pet> pets = getPets("Spot", "Cone", "Jon");

		assertNotNull("Some pets should have been returned.", pets);

	}

	public List<Pet> getPets(String petName, String ownerLastName,
			String ownerFirstName) {

		String getPets = "SELECT                               "
				+ "         p.id,                              "
				+ "         p.name,                            "
				+ "         p.birth_date,                      "
				+ "         p.type_id,                         "
				+ "         o.id AS owner_id,                  "
				+ "         o.first_name AS owner_firstName,   "
				+ "         o.last_name AS owner_lastName,     "
				+ "         o.address AS owner_address,        "
				+ "         o.city AS owner_city,              "
				+ "         o.state AS owner_state             "
				+ "       FROM                                 "
				+ "         Pets p                             "
				+ "       INNER JOIN                           "
				+ "         Owners o                           "
				+ "       ON                                   "
				+ "         p.owner_id = o.id                  "
				+ "       WHERE                                "
				+ "         p.name = :petName                  "
				+ "         AND o.last_name = :ownerLastName   "
				+ "         AND o.first_name = :ownerFirstName "
				+ "       FETCH FIRST 10 ROWS ONLY             ";

		Query<Pet> query = getQueryBuilder().createQuery(getPets, Pet.class);

		query.setParameter("petName", petName);
		query.setParameter("ownerLastName", ownerLastName);
		query.setParameter("ownerFirstName", ownerFirstName);

		List<Pet> pets = query.list();

		return pets;
	}
}
