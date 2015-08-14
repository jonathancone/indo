# Rebound
##### An elegant interface between Java and SQL
What if there were an API that eliminated all the confusing parts of reading and writing data to a relational database?

###### Creating a Connection
```java
// Use a javax.sql.DataSource for connection pooling, transactions, etc.
Query query = new Query(dataSource);
```

###### Selecting Records
```java
// Find an employee based on the value of employeeId.
Employee employee = query.select(Employee.class)
                         .withParameter("employeeId", 729)
                         .unique();

// Find all employees with the name Steve Jobs.
List<Employee> employees = query.select(Employee.class)
                             .withParameter("firstName", "Steve")
                             .withParameter("lastName", "Jobs")
                             .list();

// Similar to above, but override some of the defaults.
List<Employee> employees = query.select(Employee.class)
                             .in("EmployeeTable")
                             .mapping("firstName", "F_NAME")
                             .mapping("lastName", "L_NAME")
                             .withParameter("firstName", "Steve")
                             .withParameter("lastName", "Jobs")
                             .list();

// Complex joins can be mapped to an object graph using "AS" mapping construct.  This example
// Populates a list of Department objects, each containing a list of Employee objects.
List<Department> depts = query.select(" SELECT                                              "
                                    + "     departmentId,                                   "
                                    + "     name,                                           "
                                    + "     employeeId,                                     "
                                    + "     firstName AS employees_firstName,               "
                                    + "     lastName  AS employees_lastName,                "
                                    + "     employeeId AS employees_employeeId              "
                                    + " FROM                                                "
                                    + "     Department                                      "
                                    + " LEFT OUTER JOIN                                     "
                                    + "     Employee                                        "
                                    + " ON                                                  "
                                    + "     Department.departmentId = Employee.departmentId "
                                    + " WHERE                                               "
                                    + "     Department.name = :deptName                     ")
                              .withParameter("deptName", "Human Resources")
                              .list(Department.class);

```

###### Inserting Records
```java

// Default convention: insert Employee instance into a table named Employee and
// map each of the fields on the Employee class to table columns with the same
// name.
query.insert(employee1).count();

// Override the table name to EmployeeTable and insert a record. Also, set the
// primary key value back into employee1.employeeId by default.
query.insert(employee1).in("EmployeeTable").generateKey().count();

// Insert a single record including only the specified fields
query.insert(employee1).includingOnly("firstName").count();

// Insert a single record overriding the column names in the database
query.insert(employee1).mapping("firstName", "F_NAME").count();

// Perform a bulk insert using a collection (uses JDBC batch statement) and
// return the multiple generated keys into the payrollId and systemId fields
// instead of the default employeeId field.
query.insert(allEmployees).generateKey("payrollId", "systemId").count();

// Perform a custom SQL insert and supply our own parameter values
Integer employeeId = query.insert(" INSERT INTO Employee (firstName, lastName) "
                             + " SELECT firstName, lastName                 "
                             + " FROM Application                           "
                             + " WHERE applicationId = :applicationId       "
                             + "       AND status = 'Open'                  ")
                        .withParameter("applicationId", 212)
                        .count();
```

###### Updating Records
```java
// Default convention: update Employee instance into a table named Employee and
// map each of the fields on the Employee class to table columns with the same
// name.  By default use the primary key field employeeId as the criteria.
query.update(employee1).count();

// Update a single record in an overridden table, just setting the first name for all employees with a
// matching last name.
query.update(employee1)
     .in("EmployeeTable")
     .includingOnly("firstName")
     .usingKey("lastName");

// Perform a bulk update (uses JDBC batch statement), overriding columns
query.update(allEmployees)
     .mapping("firstName", "F_NAME")
     .mapping("lastName", "L_NAME")
     .count();

// Perform a custom SQL update and supply our own parameter values
query.update(" UPDATE Employee                  "
           + " SET salary = salary * 1.03       "
           + " WHERE hireDate > :hireDate       "
           + " AND employeeId IN (:employeeIds) "
      .withParameter("hireDate", hireDate)
      .withParameter("employeeIds", Arrays.asList(9, 15, 19))
      .count();
```

###### Deleting Records
```java
// Delete a single record that matches the employeeId on employee1
query.delete(employee1).count();

// Perform a bulk delete (uses JDBC batch statment)
query.delete(allEmployees).usingKey("employeeId").count();

// Perform a more complexe delete using the properties bound from employee2
query.delete(" DELETE FROM Employee           "
           + " WHERE employeeId = :employeeId "
           + " AND lastName = :lastName       ")
      .withParameters(employee2)
      .count();
```

###### Stored Procedures
```java
```
