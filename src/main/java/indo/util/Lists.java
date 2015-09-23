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

package indo.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Static utility methods for working with lists.
 *
 * @author Jonathan Cone
 * @see List
 */
public class Lists {
    /**
     * Create a new null-safe parameterized list from a typed array.
     *
     * @param type   The instance type that the list should contain.
     * @param source The source array.
     * @param <T>    The parameterized type of list elements.
     * @return A new list containing the supplied elements, or an empty list if
     * the source array was null.
     */
    public static <T> List<T> fromArray(Class<T> type, T[] source) {
        return Arrays.asList(Optional.ofNullable(source).orElse((T[]) Array.newInstance(type, 0)));
    }

    /**
     * Create a new null-safe  list from a typed array.
     *
     * @param source The source array.
     * @return A new list containing the supplied elements, or an empty list if
     * the source array was null.
     */
    public static List<?> fromArray(Object[] source) {
        return fromArray(Object.class, source);
    }
}
