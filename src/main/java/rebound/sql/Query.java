/*
 * Copyright (c) 2015 Rebound Contributors
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

package rebound.sql;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by jcone on 8/7/15.
 */
public class Query<T> extends SQL {
    private boolean dynamic;

    public Query(DataSource dataSource) {
        super(dataSource);
    }

    public Query<T> insert(String sql) {
        return this;
    }

    public Query<T> insert(T object) {
        return this;
    }

    public Query<T> insert(Collection<T> objects) {
        return this;
    }

    public Query<T> update(String sql) {
        return this;
    }

    public Query<T> update(T object) {
        return this;
    }

    public Query<T> update(Collection<T> objects) {
        return this;
    }

    public Query<T> delete(String sql) {
        return this;
    }

    public Query<T> delete(T object) {
        return this;
    }

    public Query<T> delete(Collection<T> objects) {
        return this;
    }

    public Query<T> select(String sql) {
        return this;
    }

    public Query<T> select(Class<T> clazz) {
        return this;
    }

    public Query<T> in(String table) {
        return this;
    }

    public Query<T> includingOnly(String... fields) {
        return this;
    }

    public Query<T> excludingOnly(String... fields) {
        return this;
    }

    public Query<T> mapping(String... fieldColumn) {
        return this;
    }

    public Query<T> mapping(Map<String, String> fieldColumn) {
        return this;
    }

    public Query<T> generateKey(String... fields) {
        return this;
    }

    public Query<T> withParameter(String field, Object value) {
        return this;
    }

    public Query<T> withParameters(Map<String, Object> fieldValues) {
        return this;
    }

    public Query<T> withParameters(Object mappingObject) {
        return this;
    }

    public Query<T> usingKey(String... fields) {
        return this;
    }

    public Integer count() {
        return 0;
    }

    public List<T> list() {
        return null;
    }

    public T single() {
        return null;
    }
}
