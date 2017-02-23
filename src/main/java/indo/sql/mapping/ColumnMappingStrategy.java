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

package indo.sql.mapping;

import java.util.Optional;

/**
 * This interface is used to implement various query result column mapping
 * behaviors to populate database result set values on Java object.
 *
 * @author Jonathan Cone
 */
@FunctionalInterface
public interface ColumnMappingStrategy {


    /**
     * Attempt to map a column to a target class. If the column could be mapped,
     * return the name of the property that was used for the match, otherwise
     * return an empty {@link Optional}.
     *
     * @param column The column to match to a property on the target class.
     * @param value  The value to bind on the target object.
     * @param target The target object instance to bind the value to if a match
     *               is found.
     * @return An {@link Optional} instance of the matching property name.
     */
    <T> Optional<String> findMatch(String column, Object value, T target);

}
