package indo.sql;

import indo.sql.test.Employee;
import indo.util.Reflect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by JCone on 9/16/2015.
 */
@RunWith(Parameterized.class)
public class MapRowTest {

    @Parameterized.Parameter(0)
    public Class<?> aClass;

    @Parameterized.Parameter(1)
    public String[] properties;

    @Parameterized.Parameter(2)
    public String[] columns;

    @Parameterized.Parameter(3)
    public Object[] values;

    @Parameterized.Parameter(4)
    public MapMode[] modes;

    @Parameterized.Parameters
    public static List<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new Object[]{0, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MapMode[]{}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EmployeeId", "FirstName", "LastName", "Salary"},
                        new Object[]{0, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MapMode[]{MapMode.IGNORE_CASE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"employeeId", "firstname", "lastname", "salary"},
                        new Object[]{0, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MapMode[]{MapMode.IGNORE_CASE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EMPLOYEEID", "FIRSTNAME", "LASTNAME", "SALARY"},
                        new Object[]{0, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MapMode[]{MapMode.IGNORE_CASE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"employee_id", "first_name", "last_name", "salary"},
                        new Object[]{0, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MapMode[]{MapMode.IGNORE_CASE, MapMode.IGNORE_UNDERSCORE}
                },
                {
                        Employee.class,
                        new String[]{"employeeId", "firstName", "lastName", "salary"},
                        new String[]{"EMPLOYEE_ID", "FIRST_NAME", "LAST_NAME", "SALARY"},
                        new Object[]{0, "firstName", "lastName", BigDecimal.valueOf(33.33)},
                        new MapMode[]{MapMode.IGNORE_CASE, MapMode.IGNORE_UNDERSCORE}
                }
        });
    }

    @Test
    public void testTo() throws Exception {
        ResultSet mockResultSet = mock(ResultSet.class);
        ResultSetMetaData mockMeta = mock(ResultSetMetaData.class);


        when(mockResultSet.getMetaData()).thenReturn(mockMeta);
        when(mockMeta.getColumnCount()).thenReturn(columns.length);


        for (int i = 0; i < columns.length; i++) {
            when(mockMeta.getColumnName(i)).thenReturn(columns[i]);
            when(mockResultSet.getObject(columns[i])).thenReturn(values[i]);
        }

        Object container = Reflect.on(aClass).newInstanceNow();

        Object result = MapRow.to(mockResultSet, container, modes);

        for (int i = 0; i < columns.length; i++) {
            assertEquals(values[i], Reflect.on(result).property(properties[i]).get());
        }

    }

}