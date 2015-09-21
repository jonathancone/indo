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


import indo.util.Lists;
import indo.util.Multi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by jcone on 8/1/15.
 */
public class Parameter {

    private String name;

    /**
     * The actual value that is being bound, might be a String, Date, List, etc.
     */
    private Object value;

    /**
     * The type that this value should be bound as, can be inferred but is also able to be overridden.
     */
    private Integer type;

    private SortedSet<Integer> indexes;

    public Parameter(String name, Object value, Integer type) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.indexes = new TreeSet<>();
    }

    public Parameter(String name, Object value, Integer type, Integer index) {
        this(name, value, type);
        addIndex(index);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Object> getValue() {
        return Optional.ofNullable(value);
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Optional<Integer> getType() {
        return Optional.ofNullable(type);
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Integer> getIndexes() {
        return new ArrayList<>(indexes);
    }

    public Parameter addIndex(Integer index) {
        indexes.add(index);
        return this;
    }

    public Parameter addIndexes(Integer start, Integer length) {
        for (int i = start; i < length; i++) {
            addIndex(i);
        }
        return this;
    }

    public Integer getMaxIndex() {
        return indexes.last();
    }

}
