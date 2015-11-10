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

import java.sql.ResultSet;
import java.util.Map;

/**
 * A functional interface which allows for custom row processing for SQL result
 * sets.
 *
 * @author Jonathan Cone
 * @see ResultSet
 */
@FunctionalInterface
public interface RowProcessor<T> {

    static <S> RowProcessor<S> using(Class<S> type, Map<String, Integer> types) {
        return new ReflectionRowProcessor<>(type, types);
    }

    static <S> RowProcessor<S> using(Class<S> type) {
        return new ReflectionRowProcessor<>(type);
    }

    /**
     * Maps a {@link ResultSet} to an object with the assumption that the
     * ResultSet cursor is correctly pointing to the row that should be
     * processed.
     *
     * @param rs The result set to transform.
     * @return an object specified by the parameterized type.
     */
    T map(ResultSet rs);
}
