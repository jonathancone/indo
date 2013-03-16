package pipeline.persistence;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import pipeline.persistence.Query;
import pipeline.persistence.domain.Owner;
import pipeline.persistence.domain.Pet;

public class QueryImplTest extends BasePersistenceTest {
	@Test
	public void testname() throws Exception {

		Owner owner = new Owner()
				.withFirstName("Jack")
				.withLastName("Bauer");

		List<Pet> pets = fetchPetsOwnedBy(owner);

		assertNotNull("Some pets should have been returned.", pets);

	}

	/*
	 * Fetch all of the pets belonging to a specific owner.
	 */
	public List<Pet> fetchPetsOwnedBy(Owner owner) {

		getQueryFactory()
				.withSQL("  SELECT                             "
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
						+ "   o.last_name = :lastName          "
						+ "   AND o.first_name = :firstName    ")
				.bind("lastName", owner.getLastName())
				.bind("firstName", owner.getFirstName())
				.bindAll(owner);

		return null;

	}
}
