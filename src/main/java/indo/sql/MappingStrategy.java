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

import indo.util.Reflect;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * This interface is used to implement various query result mapping behaviors.  This allows database columns to be
 * mapped to a POJO that adheres to JavaBeans conventions based on a variety of techniques.
 *
 * @author Jonathan Cone
 */
@FunctionalInterface
public interface MappingStrategy {

    MappingStrategy EXCLUSIVE = new Exclusive();
    MappingStrategy INCLUSIVE = new Inclusive();
    MappingStrategy CASE_INSENSITIVE = new CaseInsensitive();

    List<MappingStrategy> DEFAULTS = Arrays.asList(INCLUSIVE);


    /**
     * Attempt to map a column to a target class. If the column could be mapped, return the name of the property that
     * was used for the match, otherwise return an empty {@link Optional}.
     *
     * @param column       The column to match to the target class.
     * @param value        The value to bind on the target object.
     * @param targetObject The target object instance to bind the value to if a match is found.
     * @return An {@link Optional} instance of the matching property name.
     */
    <T> Optional<String> findMatch(String column, Object value, T targetObject);

    /**
     * A {@link MappingStrategy} that depends upon reflection to determine which fields should be mapped.
     */
    interface ReflectionBased extends MappingStrategy {

        default <T> Optional<String> findMatch(String column, Object value, T targetObject) {

            Reflect<T> targetHandle = Reflect.on(targetObject);

            Optional<String> match = findMatch(column, targetHandle.fieldNames());

            if (match.isPresent()) {
                targetHandle.property(match.get(), value);
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

    /**
     * A MappingStrategy that tries to match automatically overcoming a variety of criteria such as:
     * <ul>
     * <li>letter case ("A" vs. "a")</li>
     * <li>special characters ("_", ".", "-", etc.)</li>
     * </ul>
     * <p>
     * This allows for combinations like the ones below to be matched:
     * <table>
     * <tr>
     * <th>Example Bean Property</th>
     * <th>Example Table Column</th>
     * <th>Notes</th>
     * </tr>
     * <tr>
     * <td>employeeId</td>
     * <td>employeeId</td>
     * <td>Straight-forward match</td>
     * </tr>
     * <tr>
     * <td>employeeId</td>
     * <td>EmployeeID</td>
     * <td>Case difference</td>
     * </tr>
     * <tr>
     * <td>employeeId</td>
     * <td>EMPLOYEE_ID</td>
     * <td>Special characters</td>
     * </tr>
     * </table>
     */
    class Inclusive implements ReflectionBased {
        private Inclusive() {
        }

        @Override
        public Optional<String> findMatch(String column, Set<String> fieldNames) {

            StringBuilder javaSafeColumn = new StringBuilder();

            column.chars()
                    .filter(Character::isJavaIdentifierPart)
                    .filter(c -> c != '_')
                    .forEachOrdered(i -> javaSafeColumn.append((char) i));

            // Find the first field that matches.
            return fieldNames.stream()
                    .filter(fieldName -> fieldName.toLowerCase().equals(javaSafeColumn.toString().toLowerCase()))
                    .findFirst();
        }
    }

    /**
     * A MappingStrategy that requires an exact case-sensitive String match between column and property names.
     */
    class Exclusive implements ReflectionBased {
        private Exclusive() {
        }

        @Override
        public Optional<String> findMatch(String column, Set<String> fieldNames) {
            return Optional.ofNullable(fieldNames.contains(column) ? column : null);
        }
    }

    /**
     * A MappingStrategy that only requires that field names contain the same letters, regardless of letter
     * case.  If multiple fields on the target object would result in a match, the first field found will be
     * selected.
     */
    class CaseInsensitive implements ReflectionBased {
        private CaseInsensitive() {
        }

        @Override
        public Optional<String> findMatch(String column, Set<String> fieldNames) {
            // Find the first field that matches.
            return fieldNames
                    .stream()
                    .filter(fieldName -> fieldName.toLowerCase().equals(column.toLowerCase()))
                    .findFirst();
        }
    }

}
