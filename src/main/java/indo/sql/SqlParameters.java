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
 * Created by jcone on 8/1/15.
 */
public class SqlParameters implements SqlParameterProvider {

    private static final SqlParameters EMPTY = new SqlParameters();

    private List<SqlParameter> sqlParameterList;

    private SqlParameters() {
        this(0);
    }

    private SqlParameters(List<SqlParameter> sqlParameters) {
        this.sqlParameterList = sqlParameters;
    }

    private SqlParameters(int capacity) {
        this.sqlParameterList = new ArrayList<>(capacity);
    }

    public static SqlParameters empty() {
        return EMPTY;
    }

    public static SqlParameters fromMap(Map<String, ?> map) {
        if (Collects.isNotEmpty(map)) {
            return new SqlParameters(map.keySet().stream()
                    .map(key -> new SqlParameter(key, map.get(key)))
                    .collect(Collectors.toList()));
        } else {
            return empty();
        }
    }

    public static SqlParameters fromList(List<?> list) {
        if (Collects.isNotEmpty(list)) {

            SqlParameters sqlParameters = new SqlParameters(list.size());

            int size = list.size();

            for (int i = 0; i < size; i++) {
                sqlParameters.sqlParameterList.add(new SqlParameter(i + 1, list.get(i)));
            }

            return sqlParameters;
        } else {
            return empty();
        }
    }

    public static <T> SqlParameters fromArray(T[] t) {
        return fromList(Lists.fromArray(t));
    }

    @Override
    public Optional<SqlParameter> get(String name) {
        return stream()
                .filter(parameter -> parameter.name().isPresent() && Objects.equals(name, parameter.name().get()))
                .findFirst();
    }

    @Override
    public Optional<SqlParameter> get(Integer index) {
        return stream()
                .filter(parameter -> parameter.hasIndex(index))
                .findFirst();
    }


    public SqlParameters add(SqlParameter sqlParameter) {
        sqlParameterList.add(sqlParameter);
        return this;
    }

    @Override
    public Iterator<SqlParameter> iterator() {
        return sqlParameterList.iterator();
    }

    public Stream<SqlParameter> stream() {
        return sqlParameterList.stream();
    }

    public boolean hasParameters() {
        return Collects.isNotEmpty(sqlParameterList);
    }
}
