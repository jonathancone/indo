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


import java.util.*;

/**
 * Created by jcone on 8/1/15.
 */
public class Parameter {

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

    public Parameter(Object value, Integer index) {
        this(null, value, null, index);
    }

    public Parameter(String name, Object value, Integer type, Integer index) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.indexes = new TreeSet<>();
        this.indexes.add(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
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

    public void addIndex(Integer index) {
        indexes.add(index);
    }

    public void addIndexes(int start, int length) {
        for (int i = start; i < length; i++) {
            addIndex(i);
        }
    }

    public Integer getMaxIndex() {
        return indexes.last();
    }
}
