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


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by jcone on 8/4/15.
 */

public class BinderTest {

    public static Collection<Object[]> statements() {
        return Arrays.asList(new Object[][]{{-1, ""}, {0, ""}, {1, "?"}, {2, "?,?"}, {3, "?,?,?"}});
    }


    @ParameterizedTest
    @MethodSource("statements")
    public void testRepeatPlaceholder(int length, String result) {
        assertEquals(result, Binder.repeatPlaceholders(length));
    }
}