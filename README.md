# rebound
##### A simple database API for Java

Creating a Connection
```java
// Use a javax.sql.DataSource for connection pooling, transactions, etc.
SqlSession sql = new SqlSession(dataSource);

// Use an existing java.sql.Connection
SqlSession sql = new SqlSession(connection);
```

Inserting Records
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
insert(collection).into("Employee").returningKey("employeeId");
```

Updating Records
```java
// Update a single record that matches the employeeId on employee1
sql.update(employee1).into("Employee").keyedOn("employeeId");

// Update a single record, just setting the first name for all employees with a matching last name
sql.update(employee1, "firstName").into("Employee").keyedOn("lastName");

// Update a collection, overriding columns
sql.update(collection, "firstName AS FIRST_NAME", "lastName AS LAST_NAME").into("Employee").keyedOn("employeeId");
```
