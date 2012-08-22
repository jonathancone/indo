Pipeline Framework
==================

The Pipeline Framework is the beginings of a Java API designed with developers in mind.  The primary goal is to assist developers in writing applications that are:

* Easy to read
* Easy to test
* Easy to maintain
* Easy to debug
* Easy to diagose problems

The API is written in a manner that should be easy to retrofit poorly constructed legacy applications with quality, testable code.

It consists modules that address the following developer headaches.

* Peristence
* Web / Servlet
* Testing tools







- Persistence sessions don't need to be stateful
- No code generation
- JSON / Rest API

- You have to know SQL to use hibernate effectively, how many questions on stackoverflow like this is it going to take? "I have this SQL, can you help me generate the HQL for it?"

- Hibernate is not debuggable -- you have to understand its internals to debug / patch it

- Hibernate parameters are 0 based, JDBC is 1 based


"  SELECT 
"    t.table_catalog,
"    t.table_schema,
"    t.table_name,
"    t.table_type,
"    c.column_name
"  FROM 
"    information_schema.tables t
"  INNER JOIN
"    information_schema.columns c
"  ON
"    t.table_catalog = c.table_catalog
"    AND t.table_schema = c.table_schema
"    AND t.table_name = c.table_nam"e
"  WHERE
"    t.table_type = 'VIEW' 
"    AND c.data_type = 'character varying'
