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

package indo.sql;

import indo.example.Employee;
import org.junit.Test;

import java.util.List;

/**
 * Unit tests for {@link SqlRunner}.
 *
 * @author Jonathan Cone
 */
public class SqlRunnerTest extends DbTest {

    @Test
    public void testQuery() {

        SqlRunner runner = new SqlRunner(ds());

        List<Employee> employees = runner.list(
                " SELECT            " +
                "   employee_id,    " +
                "   first_name,     " +
                "   last_name,      " +
                "   active,         " +
                "   hire_date,      " +
                "   departure_date, " +
                "   payroll_id,     " +
                "   salary          " +
                " FROM              " +
                "   employee        " +
                " ORDER BY          " +
                "   employee_id ASC ",
                Employee.class);


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