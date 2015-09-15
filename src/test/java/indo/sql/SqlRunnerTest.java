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

import indo.sql.test.Employee;
import indo.util.SqlCollector;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jcone on 8/16/15.
 */
public class SqlRunnerTest extends AbstractDbUnitTest {
    private static final String SELECT_EMPLOYEE
            = " SELECT           " +
            "     employeeId,    " +
            "     firstName,     " +
            "     lastName,      " +
            "     active,        " +
            "     departureDate, " +
            "     hireDate,      " +
            "     payrollId,     " +
            "     salary         " +
            "   FROM             " +
            "     Employee       ";

    @Test
    public void testGetDataSource() throws Exception {
        assertEquals(getDataSource(), runner().getDataSource());
    }

    @Test
    public void testStream1() throws Exception {
/*
        List<Row> list0 = runner()
                .stream(SELECT_EMPLOYEE)
                .map(Row::from)
                .collect(Collectors.toList());

        List<Employee> list1 = runner()
                .stream(SELECT_EMPLOYEE)
                .map(Employee::new)
                .collect(Collectors.toList());


        List<Employee> list2 = runner()
                .stream(SELECT_EMPLOYEE)
                .map((rs) ->
                        new Employee(
                                getBoolean(rs, "active"),
                                getBigDecimal(rs, "salary"),
                                getDate(rs, "hireDate"),
                                getDate(rs, "departureDate"),
                                getInt(rs, "employeeId"),
                                getInt(rs, "payrollId"),
                                getString(rs, "firstName"),
                                getString(rs, "lastName")
                        ))
                .collect(Collectors.toList());

        List<Employee> list3 = runner()
                .stream(SELECT_EMPLOYEE)
                .map(rs -> Bean.to(rs, Employee.class))
                .collect(Collectors.toList());


        List<Employee> list4 = runner()
                .stream(SELECT_EMPLOYEE)
                .collect(new SqlCollector());

        List<Employee> list5 = runner()
                .stream(SELECT_EMPLOYEE)
                .collect(Collector.of(ArrayList::new,
                        (employees, resultSet) -> employees.add(new Employee(-1, getString(resultSet, "firstName"), null)),
                        (left, right) -> {
                            left.addAll(right);
                            return left;
                        }
                ));
*/

        List<Employee> list6 = runner()
                .stream(SELECT_EMPLOYEE)
                .collect(SqlCollector.toListOf(Employee.class));

        System.out.println(list6);
    }

    @Test
    public void testQueryResultSetCommand1() throws Exception {
    }

    protected SqlRunner runner() {
        return new SqlRunner(getDataSource());
    }
}
