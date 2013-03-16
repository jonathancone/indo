Pipeline Persistence
====================
__Java. SQL. Simple.__

You've spent hours telling Hibernate or JPA how to do something you could code the SQL for in a couple of minutes.  The complexity is astounding.  Mapping rules, caching configuration, attachment, detachment, you name it, its a mess.  Pipeline is a lightweight API that takes care of the hard work while providing some very compelling features.  Check out the examples, we think you'll be pleased.

__By Example__


_Fetch a collection of Owners as well as their Pets_

    List<Owner> owners = getQueryFactory()
                            .newQuery(
                                "  SELECT                                "
                              + "   o.id,                                "
                              + "   o.first_name  AS firstName,          "
                              + "   o.last_name   AS lastName,           "
                              + "   o.city,                              "              
                              + "   p.id          AS pets_id,            "
                              + "   p.name        AS pets_name,          "
                              + "   p.birth_date  AS pets_birthDate,     "
                              + " FROM Owners o                          "
                              + " INNER JOIN Pets p ON o.id = p.owner_id "
                              + " WHERE o.first_name = :firstName        ")
                            .bind("firstName", firstName)
                            .executeForList(Owner.class);

