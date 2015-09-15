/*
 * Copyright 2015 Indo Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package indo.sql.test;

import indo.sql.Query;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by jcone on 8/13/15.
 */
public class Examples {

    private DataSource dataSource = null;

    private List<Employee> allEmployees = Arrays.asList(
            new Employee(1, "Bill", "Gates"),
            new Employee(2, "Steve", "Jobs"),
            new Employee(3, "Larry", "Ellison"));

    private Employee employee = allEmployees.get(0);

    void selectExamples() {

        Query query = new Query(dataSource);

        // Find all employees with the last name "Jobs"
        List<Employee> employees = query
                .sql("    SELECT                   " +
                        "     employeeId,          " +
                        "     firstName,           " +
                        "     lastName             " +
                        " FROM                     " +
                        "     Employee             " +
                        " WHERE                    " +
                        "     lastName = :lastName ")
                .bind("lastName", "Ellison")
                .list(Employee.class);

        // Complex joins can be mapped to a collection using an "AS" mapping.  This example
        // populates a list of Department objects, each containing a list of Employee objects in a
        // single query. E.g.
        //
        // Department 1
        //    -- Employee 1
        //    -- Employee 2
        //    -- Employee 3
        // Department 2
        //    -- Employee 4
        //    -- Employee 5
        //    -- Employee 6
        List<Department> departments = query
                .sql("    SELECT                                              " +
                        "     departmentId,                                   " +
                        "     name,                                           " +
                        "     firstName AS employees_firstName,               " +
                        "     lastName  AS employees_lastName,                " +
                        "     employeeId AS employees_employeeId              " +
                        " FROM                                                " +
                        "     Department                                      " +
                        " LEFT OUTER JOIN                                     " +
                        "     Employee                                        " +
                        " ON                                                  " +
                        "     Department.departmentId = Employee.departmentId " +
                        " WHERE                                               " +
                        "     Department.name LIKE :deptName                  ")
                .bind("deptName", "%Operations%")
                .list(Department.class);

        // Similar to above, but assuming our data model doesn't match our Java object
        // the API can figure out how to map it, i.e. EMPLOYEE_ID = employeeId.
        Employee employee = query
                .sql("    SELECT                        " +
                        "      EMPLOYEE_ID,             " +
                        "      FIRST_NAME,              " +
                        "      LAST_NAME                " +
                        " FROM                          " +
                        "     EMPLOYEE                  " +
                        " WHERE                         " +
                        "     EMPLOYEE_ID > :employeeId " +
                        "     OR HIRE_DATE < :hireDate  ")
                .bind("employeeId", 2300)
                .bind("hireDate", new Date())
                .single(Employee.class);

    }

    void insertExamples() {
        Query query = new Query(dataSource);

        // Default convention: insert Employee instance into a table named Employee and
        // map each of the fields on the Employee class to table columns with the same
        // name.
        query.insert(employee).execute();

        // Override the table name to EmployeeTable and insert a record. Also, set the
        // primary key value back into employee.employeeId by default.
        query.insert(employee).in("EmployeeTable").execute();

        // Insert a single record including only the specified fields
        query.insert(employee)
                .includingOnly("firstName", "payrollId")
                .execute();

        // Insert a single record overriding the column names in the database
        query.insert(employee)
                .mapColumn("firstName", "F_NAME")
                .mapColumn("lastName", "L_NAME")
                .execute();

        // Perform a bulk insert using a collection (uses JDBC batch statement) and
        // return the generated key into payrollId (default would have been employeeId)
        // Also, count the number of rows inserted.
        Integer count = query.insert(allEmployees)
                .usingKey("payrollId")
                .count();

        // Perform a custom SQL insert and supply our own parameter values
        Integer rowCount = query
                .insert(" INSERT INTO                        " +
                        "     Employee (firstName, lastName) " +
                        " SELECT                             " +
                        "     firstName,                     " +
                        "     lastName                       " +
                        " FROM                               " +
                        "     Application                    " +
                        " WHERE                              " +
                        "     applicationId = :applicationId " +
                        "     AND status = :status           ")
                .bind("applicationId", 212)
                .bind("status", "Open")
                .count();

    }

    void updateExamples() {
        Query query = new Query(dataSource);

        // Default convention: update Employee instance into a table named Employee and
        // map each of the fields on the Employee class to table columns with the same
        // name.  By default use the primary key field employeeId as the criteria.
        query.update(employee).count();

        // Update a single record in an overridden table, just setting the first name for all employees with a
        // matching last name.
        query.update(employee)
                .in("EmployeeTable")
                .includingOnly("firstName")
                .usingKey("lastName");

        // Perform a bulk update (uses JDBC batch statement), overriding columns
        query.update(allEmployees)
                .mapColumn("firstName", "F_NAME")
                .mapColumn("lastName", "L_NAME")
                .count();

        // Perform a custom SQL update and supply our own parameter values
        query
                .update(" UPDATE                           " +
                        "     Employee                     " +
                        " SET                              " +
                        "     salary = salary * 1.03       " +
                        " WHERE                            " +
                        "     hireDate > :hireDate         " +
                        " AND                              " +
                        "     employeeId IN (:employeeIds) ")
                .bind("hireDate", new Date())
                .bind("employeeIds", Arrays.asList(1, 3))
                .count();
    }

    void deleteExamples() {
        Query query = new Query(dataSource);

        // Delete a single record that matches the employeeId on employee.
        query.delete(employee).count();

        // Perform a bulk delete (uses JDBC batch statement) using the employeeId field
        // as the matching WHERE clause.
        query.delete(allEmployees).usingKey("employeeId").execute();

        // Perform an arbitrary delete with your own SQL using the properties
        // bound from the employee instance.
        query
                .delete(" DELETE FROM                    " +
                        "     Employee                   " +
                        " WHERE                          " +
                        "     employeeId = :employeeId   " +
                        " AND                            " +
                        "     lastName = :lastName       ")
                .bind(employee)
                .execute();

    }
}
