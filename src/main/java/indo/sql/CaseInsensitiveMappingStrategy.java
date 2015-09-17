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
 * A MappingStrategy that only requires that field names contain the same letters, regardless of letter
 * case.  If multiple fields on the target object would result in a match, the first field found will be
 * selected.
 *
 * @author Jonathan Cone
 */
public class CaseInsensitiveMappingStrategy<T> implements MappingStrategy<T> {
    @Override
    public Optional<String> findMatch(String column, Set<String> fieldNames) {
        // Find the first field that matches.
        return fieldNames
                .stream()
                .filter(fieldName -> fieldName.toLowerCase().equals(column.toLowerCase()))
                .findFirst();
    }
}
