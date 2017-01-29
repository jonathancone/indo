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

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit tests for {@link ColumnTypes}.
 *
 * @author Jonathan Cone
 * @see ColumnTypes
 */
public class ColumnTypesTest {

    @Test(expected = IllegalStateException.class)
    public void testEmpty() {
        ColumnTypes.empty().andType("name", Type.BIG_DECIMAL);
    }

    @Test
    public void testCreateMapping() {
        ColumnTypes columnTypes =
                ColumnTypes.type("name1", Type.STRING)
                        .andType("name2", Type.INTEGER)
                        .andType("name3", Type.LONG);

        assertEquals(Type.STRING, columnTypes.get("name1").get());
        assertEquals(Type.INTEGER, columnTypes.get("name2").get());
        assertEquals(Type.LONG, columnTypes.get("name3").get());
    }

    @Test
    public void testGet1() {
        assertEquals(Type.INTEGER, ColumnTypes.type("name1", Type.INTEGER).get("name1").get());
    }

    @Test
    public void testGet2() {
        assertFalse(ColumnTypes.type("name1", Type.INTEGER).get("name0").isPresent());
    }
}