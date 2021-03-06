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

import indo.util.Collects;
import indo.util.Lists;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A class which serves as a container for {@link SqlParameter} instances. This
 * class also contains a static API to allow for the creation of immutable
 * instances from regular POJO parameters.
 *
 * @author Jonathan Cone
 * @see SqlParameter
 */
public class SqlParameters implements SqlParameterProvider {

    private static final SqlParameters EMPTY = new SqlParameters();

    private List<SqlParameter> parameterList;

    /**
     * Create a new empty instance.
     */
    private SqlParameters() {
        this(0);
    }

    /**
     * Create a new instance based on an input set of {@link SqlParameter}
     * instances. The supplied collection instance is not retained.
     *
     * @param sqlParameters The parameters to build this collection from.
     */
    private SqlParameters(Collection<SqlParameter> sqlParameters) {
        this.parameterList = new ArrayList<>(sqlParameters);
    }

    /**
     * Create a new instance with a capacity for a specific number of
     * parameters.
     *
     * @param capacity The parameter capacity of this instance.
     */
    private SqlParameters(int capacity) {
        this.parameterList = new ArrayList<>(capacity);
    }

    /**
     * @return An empty instance.
     */
    public static SqlParameters empty() {
        return EMPTY;
    }

    /**
     * Create a new instance based on a {@link Map}.
     *
     * @param map A {@link Map} keyed on parameter name. The value of the map
     *            entry is the value of the parameter to be bound.
     * @return The newly created instance.
     */
    public static SqlParameters fromMap(Map<String, ?> map) {
        if (Collects.isNotEmpty(map)) {
            return new SqlParameters(map.keySet().stream()
                    .map(key -> new SqlParameter(key, map.get(key)))
                    .collect(Collectors.toList()));
        } else {
            return empty();
        }
    }

    /**
     * Create a new instance based on a {@link List}.
     *
     * @param list A {@link List} which is ordered based on the parameter
     *             number. The value of a specific list index is the value that
     *             should be bound to the parameter for that index.
     * @return The newly created instance.
     */
    public static SqlParameters fromList(List<?> list) {
        if (Collects.isNotEmpty(list)) {

            SqlParameters sqlParameters = new SqlParameters(list.size());

            int size = list.size();

            for (int i = 0; i < size; i++) {
                sqlParameters.parameterList.add(new SqlParameter(i + 1, list.get(i)));
            }

            return sqlParameters;
        } else {
            return empty();
        }
    }

    /**
     * Create a new instance based on an array.
     *
     * @param t An array which is ordered based on the parameter number. The
     *          value of a specific index is the value that should be bound to
     *          the parameter for that index.
     * @return The newly created instance.
     */
    public static <T> SqlParameters fromArray(T[] t) {
        return fromList(Lists.fromArray(t));
    }

    /**
     * Create a new instance of this class backed by the supplied object.
     *
     * @param t   The target object used to back the {@link SqlParameterProvider}.
     * @param <T> The target object type.
     * @return The newly created instance.
     */
    public static <T> PojoSqlParameters<T> from(T t) {
        return new PojoSqlParameters<>(t);
    }

    /**
     * Create an intsance from a {@link Collection} of other parameters. The
     * original Collection is not retained.
     *
     * @param parameters The parameters to use for this instance.
     * @return The newly created instance.
     */
    public static SqlParameters fromParameters(Collection<SqlParameter> parameters) {
        return new SqlParameters(parameters);
    }

    @Override
    public Optional<SqlParameter> findParameter(String name) {
        return stream()
                .filter(parameter -> parameter.name().isPresent() && Objects.equals(name, parameter.name().get()))
                .findFirst();
    }

    @Override
    public Optional<SqlParameter> findParameter(Integer index) {
        return stream()
                .filter(parameter -> parameter.hasIndex(index))
                .findFirst();
    }

    @Override
    public Iterator<SqlParameter> iterator() {
        return parameterList.iterator();
    }

    private Stream<SqlParameter> stream() {
        return parameterList.stream();
    }
}
