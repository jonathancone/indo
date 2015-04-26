package rebound.sql;

import org.junit.Test;

import rebound.sql.model.Athlete;

public class QueryImplTest {
    private QueryImpl query;

    @Test
    private void testRunUsingPrototype() {

	Athlete prototype = new Athlete("Mickey", "Mantle");

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
		.binding(prototype)
		.listOf(Athlete.class);

    }
}
