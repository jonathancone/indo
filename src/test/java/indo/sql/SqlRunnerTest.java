/*
 * Copyright 2017 Indo Contributors
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

package indo.sql;

import indo.example.Employee;
import indo.sql.test.DbTest;
import indo.util.Maps;
import org.junit.Test;

import java.util.List;

import static indo.jdbc.ResultSets.*;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link SqlRunner}.
 *
 * @author Jonathan Cone
 */
public class SqlRunnerTest extends DbTest {

    private static final String SELECT_EMPLOYEE_ORDINAL_PARAMS =
            " SELECT                   " +
            "   e.employee_id,         " +
            "   e.first_name,          " +
            "   e.last_name,           " +
            "   e.active,              " +
            "   e.hire_date,           " +
            "   e.salary               " +
            " FROM  employee e         " +
            " WHERE e.salary > ?       " +
            " AND   e.last_name LIKE ? ";

    private static final String SELECT_EMPLOYEE_NAMED_PARAMS =
            " SELECT                           " +
            "   e.employee_id,                 " +
            "   e.first_name,                  " +
            "   e.last_name,                   " +
            "   e.active,                      " +
            "   e.hire_date,                   " +
            "   e.salary                       " +
            " FROM  employee e                 " +
            " WHERE e.salary > :salary         " +
            " AND   e.last_name LIKE :lastName ";

    private static final String SELECT_EMPLOYEE_AND_SHIFTS_NAMED_PARAMS =
            " SELECT                                     " +
            "   e.employee_id,                           " +
            "   e.first_name,                            " +
            "   e.last_name,                             " +
            "   e.active,                                " +
            "   e.hire_date,                             " +
            "   e.salary,                                " +
            "   t.timecard_id AS timecard_timecard_id,   " +
            "   t.employee_id AS timecard_employee_id,   " +
            "   t.week_of_year AS timecard_week_of_year, " +
            "   t.actual_hours AS timecard_actual_hours  " +
            " FROM  employee e                           " +
            " LEFT OUTER JOIN timecard t                 " +
            "   ON e.employee_id = t.employee_id         " +
            " WHERE e.salary > :salary                   " +
            " AND   e.last_name LIKE :lastName           ";


    @Test
    public void testListEmployeesWithLargeSalaries1() {
        SqlRunner runner = new SqlRunner(dataSource());

        // List results by specifying a result object type and bind
        // parameters as variable arguments.
        List<Employee> employees =
                runner.list(SELECT_EMPLOYEE_ORDINAL_PARAMS,
                        Employee.class,
                        75000.00,
                        "Lancaster");

        assertEmployees(employees);

    }

    @Test
    public void testListEmployeesWithLargeSalaries2() {
        SqlRunner runner = new SqlRunner(dataSource());

        // List results by binding named parameters from a custom
        // SqlParameterProvider, in this case a POJO.
        List<Employee> employees =
                runner.list(SELECT_EMPLOYEE_NAMED_PARAMS,
                        Employee.class,
                        SqlParameters.from(new Employee("Jill", "Lancaster", 75000.00)));

        assertEmployees(employees);

    }

    @Test
    public void testListEmployeesWithLargeSalaries3() {
        SqlRunner runner = new SqlRunner(dataSource());

        // List results by specifying a result object type and
        // named bind parameters as a map.
        List<Employee> employees =
                runner.list(SELECT_EMPLOYEE_NAMED_PARAMS,
                        Employee.class,
                        Maps.newHashMap(
                                "lastName", "Lancaster",
                                "salary", 75000.00
                        ));

        assertEmployees(employees);

    }

    @Test
    public void testListEmployeesWithLargeSalaries4() {
        SqlRunner runner = new SqlRunner(dataSource());

        // List results and map them directly from the ResultSet using
        // the indo.jdbc.ResultSets static helper.
        List<Employee> employees =
                runner.list(SELECT_EMPLOYEE_NAMED_PARAMS,
                        rs -> new Employee(
                                getLong(rs, "employee_id"),
                                getString(rs, "first_name"),
                                getString(rs, "last_name"),
                                getDate(rs, "hire_date"),
                                getBigDecimal(rs, "salary"),
                                getBoolean(rs, "active")),
                        Maps.newHashMap(
                                "lastName", "Lancaster",
                                "salary", 75000.00
                        ));

        assertEmployees(employees);

    }

    @Test
    public void testListEmployeesWithLargeSalaries5() {
        SqlRunner runner = new SqlRunner(dataSource());

        // List results by specifying a result object type and bind
        // parameters as variable arguments.
        List<Employee> employees =
                runner.list(SELECT_EMPLOYEE_ORDINAL_PARAMS,
                        Employee.class,
                        ResultTypes
                                .forColumn("hire_date", ResultType.DATE)
                                .andColumn("active", ResultType.BOOLEAN),
                        75000.00,
                        "Lancaster");

        assertEmployees(employees);

    }

    private void assertEmployees(List<Employee> employees) {

        assertTrue(employees.size() > 0);

        assertEqualsRowValue("employee", "employee_id", employees, Employee::getEmployeeId);
        assertEqualsRowValue("employee", "first_name", employees, Employee::getFirstName);
        assertEqualsRowValue("employee", "last_name", employees, Employee::getLastName);
        assertEqualsRowValue("employee", "active", employees, Employee::isActive);
        assertEqualsRowValue("employee", "hire_date", employees, Employee::getHireDate);
        assertEqualsRowValue("employee", "salary", employees, Employee::getSalary);

    }
}