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

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by jcone on 8/14/15.
 */
public interface QueryOperations {

    QueryOperations sql(String sql);

    QueryOperations insert(Object object);

    QueryOperations insert(Collection<?> objects);

    QueryOperations update(Object object);

    QueryOperations update(Collection<?> objects);

    QueryOperations insertOrUpdate(Object object);

    QueryOperations insertOrUpdate(Collection<?> objects);

    QueryOperations delete(Object object);

    QueryOperations delete(Collection<?> objects);

    QueryOperations in(String table);

    QueryOperations includingOnly(String... fields);

    QueryOperations excludingOnly(String... fields);

    QueryOperations mapColumn(String field, String column);

    <T> QueryOperations mapColumn(String field, String column, TypeConverter<T> converter);

    QueryOperations mapColumn(Map<String, String> fieldColumn);

    QueryOperations bind(String field, Object value);

    <T> QueryOperations bind(String field, Object value, Class<T> type);

    QueryOperations bind(Map<String, Object> fieldValues);

    QueryOperations bind(Object mappingObject);

    QueryOperations usingKey(String... fields);

    Integer count();

    void execute();

    <T> List<T> list(Class<T> type);

    <T> T single(Class<T> type);

    <T> T unique(Class<T> type);
}
