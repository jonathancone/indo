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

package rebound.sql;

import org.junit.Test;
import rebound.sql.test.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by jcone on 8/16/15.
 */
public class SqlTest extends AbstractDataSourceTest {
    private Sql sql;

    @Test
    public void testGetDataSource() throws Exception {
        assertEquals(getDataSource(), new Sql(getDataSource()).getDataSource());
    }

    @Test
    public void testQuery1() throws Exception {
        sql().query("SELECT employeeId, firstName, lastName FROM Employee", new ResultMapper<Employee>() {
            @Override
            public Employee map(ResultSet rs, int rowNumber) throws SQLException {
                return new Employee(rs.getInt("employeeId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"));
            }
        });
    }

    private Sql sql() {
        if (sql == null) {
            sql = new Sql(getDataSource());
        }
        return sql;
    }
}
