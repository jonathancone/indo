package indo.sql;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link SqlParameter}.
 *
 * @author Jonathan Cone
 */
public class SqlParameterTest {


    @Test
    public void testAddIndexes1() {
        SqlParameter sqlParameter = new SqlParameter("name", "value");

        sqlParameter.addIndexes(1, 1);

        assertArrayEquals(new Integer[]{1}, sqlParameter.getIndexes().toArray());
    }

    @Test
    public void testAddIndexes2() {
        SqlParameter sqlParameter = new SqlParameter("name", "value");

        sqlParameter.addIndexes(1, 4);

        assertArrayEquals(new Integer[]{1, 2, 3, 4}, sqlParameter.getIndexes().toArray());
    }

    @Test
    public void testAddIndexes3() {
        SqlParameter sqlParameter = new SqlParameter("name", "value");

        sqlParameter.addIndexes(3, 1);

        assertArrayEquals(new Integer[]{3}, sqlParameter.getIndexes().toArray());
    }

    @Test
    public void testAddIndexes4() {
        SqlParameter sqlParameter = new SqlParameter("name", "value");

        sqlParameter.addIndexes(3, 2);

        assertArrayEquals(new Integer[]{3, 4}, sqlParameter.getIndexes().toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddIndexes5() {
        SqlParameter sqlParameter = new SqlParameter("name", "value");

        sqlParameter.addIndexes(0, 1);
    }
}