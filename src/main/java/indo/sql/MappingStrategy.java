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

import java.util.Optional;
import java.util.Set;

/**
 * This interface is used to implement various query result mapping behaviors.
 *
 * @author Jonathan Cone
 */
public interface MappingStrategy<T> {
    /**
     * Attempt to map a column to a target class.
     *
     * @param column The column to match to the target class.
     * @param fieldNames The target field names to match the column against.
     * @return The matching property name on the target class.
     */
    Optional<String> findMatch(String column, Set<String> fieldNames);
}
