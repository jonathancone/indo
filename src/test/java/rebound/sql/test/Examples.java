/*
 * Copyright (c) 2015 Rebound Contributors
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

package rebound.sql.test;

import rebound.sql.Query;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jcone on 8/13/15.
 */
public class Examples {

    private DataSource dataSource = null;

    private Employee employee1 = new Employee("Bill", "Gates");
    private Employee employee2 = new Employee("Larry", "Ellison");

    private List<Employee> allEmployees = Arrays.asList(employee1, employee2);

    void selectExamples() {

        Query<Employee> query = new Query<>(dataSource);

        // Find an employee based on the value of employeeId.
        Employee employee = query.select(Employee.class)
                .withParameter("employeeId", 729)
                .single();

        // Find all employees with the name Steve Jobs.
        List<Employee> employees1 = query.select(Employee.class)
                .withParameter("firstName", "Steve")
                .withParameter("lastName", "Jobs")
                .list();

        // Similar to above, but override some of the defaults.
        List<Employee> employees2 = query.select(Employee.class)
                .in("EmployeeTable")
                .mapping("firstName", "F_NAME")
                .mapping("lastName", "L_NAME")
                .withParameter("firstName", "Steve")
                .withParameter("lastName", "Jobs")
                .list();

        // Complex joins can be mapped to an object graph using "AS" mapping construct.  This example
        // Populates a list of Department objects, each containing a list of Employee objects.
        Query<Department> departmentQuery = new Query<>(dataSource);

        List<Department> departments = departmentQuery
                .select("   SELECT                                              "
                        + "     departmentId,                                   "
                        + "     name,                                           "
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
                .list();
    }

    void insertExamples() {
        Query<Employee> query = new Query<>(dataSource);

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
        Integer employeeId = query
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
                .withParameter("applicationId", 212)
                .withParameter("status", "Open")
                .count();

    }
}
