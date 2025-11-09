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
import indo.sql.mapping.CaseInsensitiveColumnMappingStrategy;
import indo.sql.mapping.ColumnMappingStrategy;
import indo.sql.mapping.ExclusiveColumnMappingStrategy;
import indo.sql.mapping.InclusiveColumnMappingStrategy;
import indo.util.Reflect;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ReflectionRowProcessor}.
 *
 * @author Jonathan Cone
 */
public class ParameterizedReflectionRowProcessorTest {

    static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(
                        Employee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new Object[] {0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new ExclusiveColumnMappingStrategy()
                ),
                Arguments.of(
                        Employee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new Object[] {null, null, null, null},
                        new ExclusiveColumnMappingStrategy()
                ),
                Arguments.of(
                        InitializedEmployee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new Object[] {null, null, null, null},
                        new ExclusiveColumnMappingStrategy()
                ),
                Arguments.of(
                        Employee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"EmployeeId", "FirstName", "LastName", "Salary"},
                        new Object[] {0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new CaseInsensitiveColumnMappingStrategy()
                ),
                Arguments.of(
                        Employee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"employeeId", "firstname", "lastname", "salary"},
                        new Object[] {0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new CaseInsensitiveColumnMappingStrategy()
                ),
                Arguments.of(
                        Employee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"EMPLOYEEID", "FIRSTNAME", "LASTNAME", "SALARY"},
                        new Object[] {0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new CaseInsensitiveColumnMappingStrategy()
                ),
                Arguments.of(
                        Employee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"EMPLOYEEID", "FIRSTNAME", "LASTNAME", "SALARY"},
                        new Object[] {null, null, null, null},
                        new CaseInsensitiveColumnMappingStrategy()
                ),
                Arguments.of(
                        InitializedEmployee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"EMPLOYEEID", "FIRSTNAME", "LASTNAME", "SALARY"},
                        new Object[] {null, null, null, null},
                        new CaseInsensitiveColumnMappingStrategy()
                ),
                Arguments.of(
                        Employee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"employee_id", "first_name", "last_name", "salary"},
                        new Object[] {0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new InclusiveColumnMappingStrategy()
                ),
                Arguments.of(
                        Employee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "SALARY"},
                        new Object[] {0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new InclusiveColumnMappingStrategy()
                ),
                Arguments.of(
                        Employee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "SALARY"},
                        new Object[] {null, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new InclusiveColumnMappingStrategy()
                ),
                Arguments.of(
                        Employee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "SALARY"},
                        new Object[] {null, null, null, null},
                        new InclusiveColumnMappingStrategy()
                ),
                Arguments.of(
                        InitializedEmployee.class,
                        new String[] {"employeeId", "firstName", "lastName", "salary"},
                        new String[] {"EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "SALARY"},
                        new Object[] {null, null, null, null},
                        new InclusiveColumnMappingStrategy()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void testMap(Class<?> aClass, String[] properties, String[] columns, Object[] values, ColumnMappingStrategy strategy) throws Exception {
        ResultSet mockResultSet = mock(ResultSet.class);
        ResultSetMetaData mockMeta = mock(ResultSetMetaData.class);


        when(mockResultSet.getMetaData()).thenReturn(mockMeta);
        when(mockMeta.getColumnCount()).thenReturn(columns.length);


        for (int i = 0; i < columns.length; i++) {
            when(mockMeta.getColumnName(i + 1)).thenReturn(columns[i]);
            when(mockResultSet.getObject(columns[i])).thenReturn(values[i]);
        }

        ReflectionRowProcessor<?> rowProcessor = new ReflectionRowProcessor<>(aClass, ResultTypes.empty(), strategy);

        Object result = rowProcessor.map(mockResultSet);
        Reflect<Object> reflect = Reflect.on(result);

        for (int i = 0; i < columns.length; i++) {

            Optional<Object> property = reflect.property(properties[i]);

            if (property.isPresent()) {
                assertEquals(values[i], property.get());
            } else {
                assertNull(values[i]);
            }
        }

    }

    /**
     * This test class is used to assert that null values can actually override
     * pre-initialized values on the bean class.
     */
    public static class InitializedEmployee extends Employee {
        public InitializedEmployee() {
            super(500L, "First", "Last", new Date(), BigDecimal.valueOf(200.00), true);
        }
    }


}