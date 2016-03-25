# Indo
##### An elegant interface between Java and SQL
What if there were an API that eliminated all the confusing parts of reading and writing data to a relational database?

List results by passing in a result object type and bind parameters as variable arguments.
```java
SqlRunner runner = new SqlRunner(dataSource);

List<Employee> employees1 =
        runner.list(" SELECT              " +
                    "   e.employeeId,     " +
                    "   e.firstName,      " +
                    "   e.lastName        " +
                    " FROM                " +
                    "   Employee e        " +
                    " WHERE               " +
                    "   e.salary > ?      " +
                    " AND                 " +
                    "   e.lastName LIKE ? ",
                Employee.class,
                BigDecimal.valueOf(75_000.00),
                "Johnson");
```

List results and map them directly from the ResultSet using the `ResultSets` static helper.
```java
SqlRunner runner = new SqlRunner(dataSource);

List<Employee> employees2 =
        runner.list(" SELECT              " +
                    "   e.employeeId,     " +
                    "   e.firstName,      " +
                    "   e.lastName        " +
                    " FROM                " +
                    "   Employee e        " +
                    " WHERE               " +
                    "   e.salary > ?      " +
                    " AND                 " +
                    "   e.lastName LIKE ? ",
                rs -> new Employee(
                        getLong(rs, "employeeId"),
                        getString(rs, "firstName"),
                        getString(rs, "lastName")
                ),
                BigDecimal.valueOf(75_000.00),
                "Johnson");
```
List results by binding named parameters from a Map.
```java
SqlRunner runner = new SqlRunner(dataSource);

List<Employee> employees3 =
        runner.list(" SELECT                      " +
                    "   e.employeeId,             " +
                    "   e.firstName,              " +
                    "   e.lastName                " +
                    " FROM                        " +
                    "   Employee e                " +
                    " WHERE                       " +
                    "   e.salary > :salary        " +
                    " AND                         " +
                    "   e.lastName LIKE :lastName ",
                Employee.class,
                Maps.newHashMap(
                        "salary", BigDecimal.valueOf(75_000.00),
                        "lastName", "Johnson"
                ));
```

List results by binding named parameters from a custom `SqlParameterProvider`, in this case a POJO.

```java
SqlRunner runner = new SqlRunner(dataSource);

Employee employeeLike = new Employee();
employeeLike.setLastName("Johnson");
employeeLike.setSalary(BigDecimal.valueOf(75_000.00));

List<Employee> employees4 =
        runner.list(" SELECT                      " +
                    "   e.employeeId,             " +
                    "   e.firstName,              " +
                    "   e.lastName                " +
                    " FROM                        " +
                    "   Employee e                " +
                    " WHERE                       " +
                    "   e.salary > :salary        " +
                    " AND                         " +
                    "   e.lastName LIKE :lastName ",
                Employee.class,
                SqlParameters.from(employeeLike));
}
```
