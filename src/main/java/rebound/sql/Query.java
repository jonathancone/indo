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

import org.apache.poi.ss.formula.functions.T;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by jcone on 8/7/15.
 */
public class Query {

    private SQL sql;

    public Query(DataSource dataSource) {
        this.sql = new SQL(dataSource);
    }

    public Query insert(String sql) {
        return this;
    }

    public Query insert(Object object) {
        return this;
    }

    public Query insert(Collection<T> objects) {
        return this;
    }

    public Query update(String sql) {
        return this;
    }

    public Query update(Object object) {
        return this;
    }

    public Query update(Collection<?> objects) {
        return this;
    }

    public Query insertOrUpdate(Object object) {
        return this;
    }

    public Query insertOrUpdate(Collection<?> objects) {
        return this;
    }

    public Query delete(String sql) {
        return this;
    }

    public Query delete(Object object) {
        return this;
    }

    public Query delete(Collection<?> objects) {
        return this;
    }

    public Query select(String sql) {
        return this;
    }

    public Query select() {
        return this;
    }

    public Query in(String table) {
        return this;
    }

    public Query includingOnly(String... fields) {
        return this;
    }

    public Query excludingOnly(String... fields) {
        return this;
    }

    public Query mapping(String... fieldColumn) {
        return this;
    }

    public Query mapping(Map<String, String> fieldColumn) {
        return this;
    }

    public Query bind(String field, Object value) {
        return this;
    }

    public Query bind(Map<String, Object> fieldValues) {
        return this;
    }

    public Query bind(Object mappingObject) {
        return this;
    }

    public Query usingKey(String... fields) {
        return this;
    }

    public Integer count() {
        return 0;
    }

    public <T> List<T> list(Class<T> type) {
        return null;
    }

    public <T> T single(Class<T> type) {
        return null;
    }

    public <T> T unique(Class<T> type) {
        return null;
    }
}
