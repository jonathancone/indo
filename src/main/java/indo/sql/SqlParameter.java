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

import java.util.*;
import java.util.stream.IntStream;


/**
 * Represents a high-level SQL bind parameter which may be bound to a value in a
 * SQL statement using JDBC syntax or a familiar map key/value syntax.
 *
 * @author Jonathan Cone
 * @see SqlParameters
 * @see SqlParameterProvider
 */
public class SqlParameter {

    private String name;

    /**
     * The actual value that is being bound, might be a String, Date, List,
     * etc.
     */
    private Object value;

    /**
     * The type that this value should be bound as, can be inferred but is also
     * able to be overridden.
     */
    private Integer type;

    private SortedSet<Integer> indexes;

    public SqlParameter(SqlParameter sqlParameter) {
        this.name = sqlParameter.name;
        this.value = sqlParameter.value;
        this.type = sqlParameter.type;
        this.indexes = new TreeSet<>(indexes);
    }

    public SqlParameter(String name, Object value) {
        this.name = name;
        this.value = value;
        this.indexes = new TreeSet<>();
    }

    public SqlParameter(String name, Object value, Integer type) {
        this(name, value);
        this.type = type;
    }

    public SqlParameter(Integer index, Object value) {
        this.value = value;
        this.indexes = new TreeSet<>();
        this.indexes.add(index);
    }

    public SqlParameter(Integer index, Object value, Integer type) {
        this(index, value);
        this.type = type;
    }

    public Optional<String> name() {
        return Optional.ofNullable(name);
    }

    public Optional<Object> value() {
        return Optional.ofNullable(value);
    }

    public Optional<Integer> type() {
        return Optional.ofNullable(type);
    }

    public boolean hasIndex(Integer index) {
        return Collects.isNotEmpty(indexes) && indexes.contains(index);
    }

    public List<Integer> getIndexes() {
        return Collections.unmodifiableList(new ArrayList<>(indexes));
    }

    public void addIndex(Integer index) {
        indexes.add(index);
    }

    public void addIndexes(int start, int length) {
        if (start < 1) {
            throw new IllegalArgumentException("The starting index cannot be less than 1.");
        }

        IntStream.range(start, start + length).forEachOrdered(this::addIndex);
    }

    public Integer getMaxIndex() {
        return indexes.size() > 0 ? indexes.last() : 0;
    }
}
