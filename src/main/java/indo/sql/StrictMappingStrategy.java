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

import java.util.Optional;

/**
 * A MappingStrategy that requires an exact case-sensitive String match between column and property names.
 *
 * @author Jonathan Cone
 */
public class StrictMappingStrategy<T> implements MappingStrategy<T> {

    @Override
    public Optional<String> findMatch(String column, Class<T> target) {
        return Optional.ofNullable(Reflect.on(target).fieldNames().contains(column) ? column : null);
    }
}
