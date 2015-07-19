package rebound.sql;

import org.junit.Test;

import rebound.sql.test.Athlete;

public class QueryImplTest {
    private QueryImpl query;

    @Test
    private void testRunUsingPrototype() {

	Athlete bambino = new Athlete("Babe", "Ruth");

	query.run("  SELECT                          "
		+ "  	a.firstName,                 "
		+ "  	a.lastName,                  "
		+ "  	t.name,                      "
		+ "  	t.city AS team_city,         "
		+ "  	t.state AS team_state        "
		+ "  FROM                            "
		+ "  	Athlete a                    "
		+ "  INNER JOIN                      "
		+ "  	Team t                       "
		+ "  ON                              "
		+ "  	a.teamId = t.teamId          "
		+ "  WHERE                           "
		+ "  	a.firstName = :firstName     "
		+ "  	AND a.lastName = :lastName   ")
		.binding(bambino)
		.listOf(Athlete.class);

    }
}
