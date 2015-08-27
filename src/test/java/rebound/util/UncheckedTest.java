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

import static org.junit.Assert.assertEquals;

/**
 * Created by jcone on 8/26/15.
 */
public class UncheckedTest {

    @Test
    public void testExceptionWrapExceptionWithMatch() throws Exception {
        SQLException initial = new SQLException();

        JdbcException actual = Unchecked.exception(initial, SQLException.class, JdbcException.class);

        assertEquals(initial, actual.getCause());
    }

    @Test
    public void testExceptionWrapExceptionWithoutMatch() throws Exception {
        Exception initial = new Exception();

        RuntimeException actual = Unchecked.exception(initial, SQLException.class, RuntimeException.class);

        assertEquals(initial, actual.getCause());

    }

    @Test
    public void testExceptionDontWrapException() throws Exception {
        RuntimeException initial = new RuntimeException();

        RuntimeException actual = Unchecked.exception(initial, SQLException.class, RuntimeException.class);

        assertEquals(initial, actual);

    }

    @Test
    public void testExceptionNoArguments() throws Exception {
        SQLException initial = new SQLException();

        RuntimeException actual = Unchecked.exception(initial);

        assertEquals(initial, actual.getCause());

    }


}