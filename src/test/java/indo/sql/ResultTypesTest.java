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
 * Unit tests for {@link ResultTypes}.
 *
 * @author Jonathan Cone
 * @see ResultTypes
 */
public class ResultTypesTest {

    @Test(expected = IllegalStateException.class)
    public void testEmpty() {
        ResultTypes.empty().andColumn("name", ResultType.BIG_DECIMAL);
    }

    @Test
    public void testCreateMapping() {
        ResultTypes resultTypes =
                ResultTypes.forColumn("name1", ResultType.STRING)
                        .andColumn("name2", ResultType.INTEGER)
                        .andColumn("name3", ResultType.LONG);

        assertEquals(ResultType.STRING, resultTypes.get("name1").get());
        assertEquals(ResultType.INTEGER, resultTypes.get("name2").get());
        assertEquals(ResultType.LONG, resultTypes.get("name3").get());
    }

    @Test
    public void testGet1() {
        assertEquals(ResultType.INTEGER, ResultTypes.forColumn("name1", ResultType.INTEGER).get("name1").get());
    }

    @Test
    public void testGet2() {
        assertFalse(ResultTypes.forColumn("name1", ResultType.INTEGER).get("name0").isPresent());
    }
}