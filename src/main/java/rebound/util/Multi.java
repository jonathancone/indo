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

import java.util.Collection;

/**
 * Utilities for working with {@link Collection}s and arrays.
 *
 * @author Jonathan Cone
 */
public class Multi {
    /**
     * Logical test to see if an array has any elements.
     *
     * @param array The array to test.
     * @return true if the specified array is not null or has a length greater than 0.
     */
    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }

    /**
     * Logical test to see if a {@link Collection} has any elements.
     *
     * @param collection The {@link Collection} to test.
     * @return true if the specified {@link Collection} is not null or has a size greater than 0.
     */
    public static boolean isNotEmpty(Collection collection) {
        return collection != null && collection.size() > 0;
    }

    public static int size(Object[] array) {
        return array != null ? array.length : 0;
    }

    public static int size(Collection collection) {
        return collection != null ? collection.size() : 0;
    }
}
