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

package indo.example;

import indo.sql.SqlParameters;
import indo.sql.SqlRunner;
import indo.util.Maps;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static indo.jdbc.ResultSets.getInt;
import static indo.jdbc.ResultSets.getString;

/**
 * Examples of how to use the {@link SqlRunner} API.
 *
 * @author Jonathan Cone
 */
public class SqlRunnerExamples {
    private DataSource dataSource;

    public void queryExamples() {
        SqlRunner runner = new SqlRunner(dataSource);

        // List results by passing in a result object type and bind parameters as variable arguments.
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

        // List results and map them directly from the ResultSet using
        // the indo.jdbc.ResultSets static helper.
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
                                getInt(rs, "employeeId"),
                                getString(rs, "firstName"),
                                getString(rs, "lastName")
                        ),
                        BigDecimal.valueOf(75_000.00),
                        "Johnson");

        // List results by binding named parameters from a Map.
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

        // List results by binding named parameters from a custom SqlParameterProvider, in
        // this case a POJO.
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
}
