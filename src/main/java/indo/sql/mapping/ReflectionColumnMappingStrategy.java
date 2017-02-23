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

import indo.log.Logger;
import indo.util.Reflect;

import java.util.Optional;
import java.util.Set;

import static indo.log.Logger.error;

/**
 * A {@link ColumnMappingStrategy} that is based upon reflection to determine
 * which fields should be mapped.
 */
public interface ReflectionColumnMappingStrategy extends ColumnMappingStrategy {

    default <T> Optional<String> findMatch(String column, Object value, T target) {

        Reflect<T> targetHandle = Reflect.on(target);

        Optional<String> match = findMatch(column, targetHandle.fieldNames());

        if (match.isPresent()) {
            try {
                Logger.debug(this, "Found matching property %s for value %s", match.get(), value);
                targetHandle.property(match.get(), value);
            } catch (NoSuchMethodError e) {
                // We had the field but couldn't match a setter of an assignable type.
                error(this, e.getMessage());

                return Optional.empty();
            }
        }

        return match;
    }

    /**
     * Find a match for a column based on a set candidate field names.
     *
     * @param column     The column to match against the field names.
     * @param fieldNames The target field names to match the column against.
     * @return An {@link Optional} instance of the matching property name.
     */
    Optional<String> findMatch(String column, Set<String> fieldNames);

}
