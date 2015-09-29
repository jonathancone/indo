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
public class SqlRunnerTest extends AbstractDbUnitTest {

    @Test
    public void testQuery() {
        SqlRunner runner = new SqlRunner(ds());

        List<Employee> employees = runner.list("SELECT * FROM Employee", Employee.class);

        employees.stream().forEach(e -> assertEqualsRowValue("Employee", "employee_id", 1, e));

    }

    @Test
    public void testQuery1() {

    }

    @Test
    public void testQuery2() {

    }

    @Test
    public void testQuery3() {

    }

    @Test
    public void testQuery4() {

    }
}