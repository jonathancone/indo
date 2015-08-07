# Rebound
##### An elegant interface between Java and SQL
What if there were an API that eliminated all the confusing parts of reading and writing data to a relational database?

###### Creating a Connection
You can create a connection to the database using any of the standard ways you would expect.
```java
// Use a javax.sql.DataSource for connection pooling, transactions, etc.
SqlSession sql = new SqlConfig(dataSource).create();

// Use java.util.Properties to configure datasource
SqlSession sql = new SqlConfig(properties).create();

// Use an existing java.sql.Connection
SqlSession sql = SqlConfig(connection).create();

// Quick and dirty using JDBC URL
SqlSession sql = SqlConfig("jdbc://...", "user", "password").create();
```

######Inserting Records
Simple records can be inserted using the DSL, more complex scenarios make use of the query API.
```java
Employee employee1 = new Employee("Bill", "Gates");
Employee employee2 = new Employee("Larry", "Ellison");
Collection collection = Arrays.asList(employee1, employee2);

// Insert a single record and set the primary key value back into employee1.employeeId
sql.insert(employee1).into("Employee").returningKey("employeeId");

// Insert a single record using only the specified columns, pass null for everything not listed
sql.insert(employee1, "firstName").into("Employee").returningNothing();

// Insert a single record overriding the column names in the database
sql.insert(employee1, "firstName AS FIRST_NAME", "lastName AS LAST_NAME").into("Employee").returningNothing();

// Perform a bulk insert (uses JDBC batch statement)
sql.insert(collection).into("Employee").returningKey("employeeId");

// Perform a more complex insert
Integer employeeId = sql.query(" INSERT INTO Employee (firstName, lastName) " 
                             + " SELECT firstName, lastName                 "
                             + " FROM Application                           "
                             + " WHERE applicationId = :applicationId       "
                             + "       AND status = 'Open'                  ")
                        .withParameter("applicationId", 212)
                        .returningKey();
```

######Updating Records
Simple records can be updated using the DSL, more complex scenarios make use of the query API.
```java
// Update a single record that matches the employeeId on employee1
sql.update(employee1).into("Employee").usingKey("employeeId");

// Update a single record, just setting the first name for all employees with a matching last name
sql.update(employee1, "firstName").into("Employee").usingKey("lastName");

// Perform a bulk update (uses JDBC batch statement), overriding columns
sql.update(collection, "firstName AS FIRST_NAME", "lastName AS LAST_NAME").into("Employee").usingKey("employeeId");
```

######Deleting Records
Simple records can be deleted using the DSL, more complex scenarios make use of the query API.
```java
// Delete a single record that matches the employeeId on employee1
sql.delete(employee1).keyedOn("employeeId");

// Perform a bulk delete (uses JDBC batch statment)
sql.delete(collection).keyedOn("employeeId");
```

######Selecting Records 
Simple records can be selected using the DSL, more complex scenarios make use of the query API.
```java
// Find an employee based on their ID
Employee employee3 = sql.select(Employee.class)
                        .from("Employee")
                        .withParameter("employeeId", 729)
                        .unique(); 

// Find all employees with the name Steve Jobs while overriding column names
List<Employee> steves = sql.select(Employee.class, "firstName AS FIRST_NAME", "lastName AS LAST_NAME")
                        .from("Employee")
                        .withParameter("firstName", "Steve")
                        .withParameter("lastName", "Jobs")
                        .list(); 
```

Stored Procedures
