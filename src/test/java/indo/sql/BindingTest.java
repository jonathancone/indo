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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by jcone on 8/4/15.
 */
@RunWith(Parameterized.class)
public class BindingTest {
    @Parameterized.Parameter(0)
    public int length;

    @Parameterized.Parameter(1)
    public String result;

    @Parameterized.Parameters
    public static Collection<Object[]> statements() {
        return Arrays.asList(new Object[][]{{-1, ""}, {0, ""}, {1, "?"}, {2, "?,?"}, {3, "?,?,?"}});
    }


    @Test
    public void testRepeatPlaceholder() {
        Assert.assertEquals(result, Binding.repeatPlaceholders(length));
    }
}