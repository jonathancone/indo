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

import indo.util.Multi;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by jcone on 8/1/15.
 */
public class Parameters {

    /**
     * Given an array of {@link Object}s, create a {@link List} of {@link Parameters} with indexes corresponding to
     * the object's position in the array.
     *
     * @param parameters The array to convert into a List of Parameter objects.
     * @return An unmodifiable List containing the Parameters.
     */
    public static List<Parameter> fromArray(Object[] parameters) {

        if (Multi.isNotEmpty(parameters)) {
            return Collections.unmodifiableList(
                    IntStream.range(0, parameters.length)
                            .sequential()
                            .mapToObj(i -> new Parameter(null, parameters[i], null, i))
                            .collect(Collectors.toList()));
        }

        return Collections.emptyList();
    }

    public static List<Parameter> fromMap(Map<String, Object> parameters) {

        return null;
    }
}
