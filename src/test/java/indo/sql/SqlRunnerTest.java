/*
 * Copyright 2015  Jonathan Cone
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
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static indo.jdbc.ResultSets.*;
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
        List<Employee> employees = runner()
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


        for (int i = 0; i < employees.size(); i++) {
            // assertRowValue("Employee", "employeeId", i, employees.get(i).getEmployeeId());
        }

    }

    @Test
    public void testQueryResultSetCommand1() throws Exception {
    }

    protected SqlRunner runner() {
        return new SqlRunner(getDataSource());
    }
}
