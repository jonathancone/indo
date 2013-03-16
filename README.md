Pipeline Persistence
====================
__Java. SQL. Simple.__

Pipeline is a simple Java API you can use to query your database. Maybe you've tried Hibernate and JPA and realized they're too complex and heavyweight.  Pipeline is a lightweight API that puts the control back in your hands while taking care of all the hard work for you.

__By Example__




    getQueryFactory().newQuery(
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
              + " WHERE                                  "
              + "   o.first_name = :firstName            ")
              .bind("firstName", firstName)
              .executeForList(Owner.class);

