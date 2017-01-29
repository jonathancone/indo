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

import java.math.BigDecimal;
import java.util.List;

import static indo.jdbc.ResultSets.*;

/**
 * Unit tests for {@link SqlRunner}.
 *
 * @author Jonathan Cone
 */
public class SqlRunnerTest extends DbTest {


    @Test
    public void testListEmployeesWithLargeSalaries1() {
        SqlRunner runner = new SqlRunner(dataSource());

        // List results by specifying a result object type and bind parameters as variable arguments.
        List<Employee> employees =
                runner.list(" SELECT                   " +
                            "   e.employee_id,         " +
                            "   e.first_name,          " +
                            "   e.last_name,           " +
                            "   e.active,              " +
                            "   e.hire_date,           " +
                            "   e.departure_date,      " +
                            "   e.payroll_id,          " +
                            "   e.salary               " +
                            " FROM  employee e         " +
                            " WHERE e.salary > ?       " +
                            " AND   e.last_name LIKE ? ",
                        Employee.class,
                        BigDecimal.valueOf(75000.00),
                        "Lancaster");

        assertEmployees(employees);

    }

    @Test
    public void testListEmployeesWithLargeSalaries2() {
        SqlRunner runner = new SqlRunner(dataSource());

        // List results by specifying a result object type and named bind parameters as a map.
        List<Employee> employees =
                runner.list(" SELECT                           " +
                            "   e.employee_id,                 " +
                            "   e.first_name,                  " +
                            "   e.last_name,                   " +
                            "   e.active,                      " +
                            "   e.hire_date,                   " +
                            "   e.departure_date,              " +
                            "   e.payroll_id,                  " +
                            "   e.salary                       " +
                            " FROM  employee e                 " +
                            " WHERE e.salary > :salary         " +
                            " AND   e.last_name LIKE :lastName ",
                        Employee.class,
                        Maps.newHashMap(
                                "lastName", "Lancaster",
                                "salary", BigDecimal.valueOf(75000.00)
                        ));

        assertEmployees(employees);

    }

    @Test
    public void testListEmployeesWithLargeSalaries3() {
        SqlRunner runner = new SqlRunner(dataSource());

        // List results and map them directly from the ResultSet using
        // the indo.jdbc.ResultSets static helper.
        List<Employee> employees =
                runner.list(" SELECT                           " +
                            "   e.employee_id,                 " +
                            "   e.first_name,                  " +
                            "   e.last_name,                   " +
                            "   e.active,                      " +
                            "   e.hire_date,                   " +
                            "   e.departure_date,              " +
                            "   e.payroll_id,                  " +
                            "   e.salary                       " +
                            " FROM  employee e                 " +
                            " WHERE e.salary > :salary         " +
                            " AND   e.last_name LIKE :lastName ",
                        rs -> new Employee(
                                getBoolean(rs, "active"),
                                getBigDecimal(rs, "salary"),
                                getDate(rs, "hire_date"),
                                getDate(rs, "departure_date"),
                                getInt(rs, "employee_id"),
                                getLong(rs, "payroll_id"),
                                getString(rs, "first_name"),
                                getString(rs, "last_name")
                        ),
                        Maps.newHashMap(
                                "lastName", "Lancaster",
                                "salary", BigDecimal.valueOf(75000.00)
                        ));

        assertEmployees(employees);

    }

    @Test
    public void testListEmployeesWithLargeSalaries4() {
        SqlRunner runner = new SqlRunner(dataSource());

        // List results by binding named parameters from a custom
        // SqlParameterProvider, in this case a POJO.
        Employee example = new Employee();
        example.setLastName("Lancaster");
        example.setSalary(BigDecimal.valueOf(75000.00));

        List<Employee> employees =
                runner.list(" SELECT                           " +
                            "   e.employee_id,                 " +
                            "   e.first_name,                  " +
                            "   e.last_name,                   " +
                            "   e.active,                      " +
                            "   e.hire_date,                   " +
                            "   e.departure_date,              " +
                            "   e.payroll_id,                  " +
                            "   e.salary                       " +
                            " FROM  employee e                 " +
                            " WHERE e.salary > :salary         " +
                            " AND   e.last_name LIKE :lastName ",
                        Employee.class,
                        SqlParameters.from(example));

        assertEmployees(employees);

    }


    private void assertEmployees(List<Employee> employees) {
        assertEqualsRowValue("employee", "employee_id", employees, Employee::getEmployeeId);
        assertEqualsRowValue("employee", "first_name", employees, Employee::getFirstName);
        assertEqualsRowValue("employee", "last_name", employees, Employee::getLastName);
        assertEqualsRowValue("employee", "active", employees, Employee::isActive);
        assertEqualsRowValue("employee", "hire_date", employees, Employee::getHireDate);
        assertEqualsRowValue("employee", "departure_date", employees, Employee::getDepartureDate);
        assertEqualsRowValue("employee", "payroll_id", employees, Employee::getPayrollId);
        assertEqualsRowValue("employee", "salary", employees, Employee::getSalary);

    }
}