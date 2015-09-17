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
 *
 * @author Jonathan Cone
 */
public class InclusiveMappingStrategy<T> implements MappingStrategy<T> {
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
