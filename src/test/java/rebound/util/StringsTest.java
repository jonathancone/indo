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

import static org.junit.Assert.assertEquals;

/**
 * Created by jcone on 8/25/15.
 */
public class StringsTest {
    @Test
    public void testWordCase1() throws Exception {
        assertEquals("", Strings.wordCase(""));
    }

    @Test
    public void testWordCase2() throws Exception {
        assertEquals(" ", Strings.wordCase(" "));
    }

    @Test
    public void testWordCase3() throws Exception {
        assertEquals("  ", Strings.wordCase("  "));
    }

    @Test
    public void testWordCase4() throws Exception {
        assertEquals(null, Strings.wordCase(null));
    }

    @Test
    public void testWordCase5() throws Exception {
        assertEquals("EmployeeId", Strings.wordCase("employeeId"));
    }

    @Test
    public void testWordCase6() throws Exception {
        assertEquals("Employee", Strings.wordCase("Employee"));
    }
}