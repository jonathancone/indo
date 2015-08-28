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

package rebound.util;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Unit tests for {@link JdbcException}.
 *
 * @author Jonathan Cone
 */
public class JdbcExceptionTest {

    @Test
    public void testHasSQLExceptionCause1() {
        assertTrue(new JdbcException(new SQLException()).hasSQLExceptionCause());
    }

    @Test
    public void testHasSQLExceptionCause2() {
        assertFalse(new JdbcException(new Exception()).hasSQLExceptionCause());
    }

    @Test
    public void testGetSQLExceptionCause1() throws Exception {
        SQLException initial = new SQLException();

        assertEquals(initial, new JdbcException(initial).getSQLExceptionCause());
    }

    @Test(expected = ClassCastException.class)
    public void testGetSQLExceptionCause2() throws Exception {
        Exception initial = new Exception();

        assertNotEquals(initial, new JdbcException(initial).getSQLExceptionCause());
    }
}