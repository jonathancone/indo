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

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

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