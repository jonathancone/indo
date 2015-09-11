/*
 * Copyright (c) 2015 Indo Contributors
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

package indo.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link Multi}.
 *
 * @author Jonathan Cone
 */
public class MultiTest {

    @Test
    public void testCollectionIsNotEmpty1() {
        assertTrue(Multi.isNotEmpty(Arrays.asList("1")));
    }

    @Test
    public void testCollectionIsNotEmpty2() {
        assertTrue(Multi.isNotEmpty(Arrays.asList("1", "2")));
    }

    @Test
    public void testCollectionIsNotEmpty3() {
        assertFalse(Multi.isNotEmpty((Collection) null));
    }

    @Test
    public void testCollectionIsNotEmpty4() {
        assertFalse(Multi.isNotEmpty(new ArrayList()));
    }

    @Test
    public void testArrayIsNotEmpty1() {
        assertTrue(Multi.isNotEmpty(new String[]{"1"}));
    }

    @Test
    public void testArrayIsNotEmpty2() {
        assertTrue(Multi.isNotEmpty(new String[]{"1", "2"}));
    }

    @Test
    public void testArrayIsNotEmpty3() {
        assertFalse(Multi.isNotEmpty((Object[]) null));
    }

    @Test
    public void testArrayIsNotEmpty4() {
        assertFalse(Multi.isNotEmpty(new String[]{}));
    }

}