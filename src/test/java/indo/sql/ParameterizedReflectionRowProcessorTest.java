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
import indo.util.Reflect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link ReflectionRowProcessor}.
 *
 * @author Jonathan Cone
 */
@RunWith(Parameterized.class)
public class ParameterizedReflectionRowProcessorTest {

    @Parameterized.Parameter(0)
    public Class<?> aClass;

    @Parameterized.Parameter(1)
    public String[] properties;

    @Parameterized.Parameter(2)
    public String[] columns;

    @Parameterized.Parameter(3)
    public Object[] values;

    @Parameterized.Parameter(4)
    public MappingStrategy[] strategies;

    @Parameterized.Parameters
    public static List<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new Object[]{0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MappingStrategy[]{MappingStrategy.EXCLUSIVE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new Object[]{null, null, null, null},
                        new MappingStrategy[]{MappingStrategy.EXCLUSIVE}
                },
                {
                        InitializedEmployee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new Object[]{null, null, null, null},
                        new MappingStrategy[]{MappingStrategy.EXCLUSIVE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EmployeeId", "FirstName", "LastName", "Salary"},
                        new Object[]{0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MappingStrategy[]{MappingStrategy.CASE_INSENSITIVE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"employeeId", "firstname", "lastname", "salary"},
                        new Object[]{0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MappingStrategy[]{MappingStrategy.CASE_INSENSITIVE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EMPLOYEEID", "FIRSTNAME", "LASTNAME", "SALARY"},
                        new Object[]{0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MappingStrategy[]{MappingStrategy.CASE_INSENSITIVE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EMPLOYEEID", "FIRSTNAME", "LASTNAME", "SALARY"},
                        new Object[]{null, null, null, null},
                        new MappingStrategy[]{MappingStrategy.CASE_INSENSITIVE}
                },
                {
                        InitializedEmployee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EMPLOYEEID", "FIRSTNAME", "LASTNAME", "SALARY"},
                        new Object[]{null, null, null, null},
                        new MappingStrategy[]{MappingStrategy.CASE_INSENSITIVE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"employee_id", "first_name", "last_name", "salary"},
                        new Object[]{0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MappingStrategy[]{MappingStrategy.INCLUSIVE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "SALARY"},
                        new Object[]{0L, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MappingStrategy[]{MappingStrategy.INCLUSIVE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "SALARY"},
                        new Object[]{null, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MappingStrategy[]{MappingStrategy.INCLUSIVE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "SALARY"},
                        new Object[]{null, null, null, null},
                        new MappingStrategy[]{MappingStrategy.INCLUSIVE}
                },
                {
                        InitializedEmployee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "SALARY"},
                        new Object[]{null, null, null, null},
                        new MappingStrategy[]{MappingStrategy.INCLUSIVE}
                },

        });
    }

    @Test
    public void testMap() throws Exception {
        ResultSet mockResultSet = mock(ResultSet.class);
        ResultSetMetaData mockMeta = mock(ResultSetMetaData.class);


        when(mockResultSet.getMetaData()).thenReturn(mockMeta);
        when(mockMeta.getColumnCount()).thenReturn(columns.length);


        for (int i = 0; i < columns.length; i++) {
            when(mockMeta.getColumnName(i + 1)).thenReturn(columns[i]);
            when(mockResultSet.getObject(columns[i])).thenReturn(values[i]);
        }

        ReflectionRowProcessor<?> rowProcessor = new ReflectionRowProcessor(aClass) {
            @Override
            protected List<MappingStrategy> getMappingStrategies() {
                return Arrays.asList(strategies);
            }
        };

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
            super(true, BigDecimal.valueOf(200.00), new Date(), new Date(), 500L, 500L, "First", "Last");
        }
    }


}