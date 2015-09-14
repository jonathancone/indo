/*
 * Copyright (c) 2015 Indo Contributors
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

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by jcone on 8/14/15.
 */
public abstract class AbstractQueryOperations implements QueryOperations {

    private DataSource dataSource;
    private QueryMetaData queryMetaData;
    private SqlOperations sqlOperations;
    private Operation operation;

    private Collection<?> targetCollection;

    public AbstractQueryOperations(DataSource dataSource) {
        this.dataSource = dataSource;
        this.queryMetaData = new QueryMetaData();
        this.sqlOperations = new SqlRunner(dataSource);
        this.operation = Operation.UNKNOWN;
    }

    @Override
    public AbstractQueryOperations insert(Object object) {
        return insert(Arrays.asList(object));
    }

    @Override
    public AbstractQueryOperations insert(Collection<?> objects) {
        this.operation = Operation.INSERT;
        this.targetCollection = objects;
        return this;
    }

    @Override
    public AbstractQueryOperations update(Object object) {
        return update(Arrays.asList(object));
    }

    @Override
    public AbstractQueryOperations update(Collection<?> objects) {
        this.operation = Operation.UPDATE;
        this.targetCollection = objects;
        return this;
    }

    @Override
    public AbstractQueryOperations insertOrUpdate(Object object) {
        return insertOrUpdate(Arrays.asList(object));
    }

    @Override
    public AbstractQueryOperations insertOrUpdate(Collection<?> objects) {
        this.operation = Operation.INSERT_OR_UPDATE;
        this.targetCollection = objects;
        return this;
    }

    @Override
    public AbstractQueryOperations delete(Object object) {
        return delete(Arrays.asList(object));
    }

    @Override
    public AbstractQueryOperations delete(Collection<?> objects) {
        this.operation = Operation.DELETE;
        this.targetCollection = objects;
        return this;
    }

    @Override
    public AbstractQueryOperations sql(String sql) {
        this.queryMetaData.setSourceSql(sql);
        return this;
    }

    @Override
    public AbstractQueryOperations in(String table) {
        queryMetaData.setTableNameOverride(table);
        return this;
    }

    @Override
    public AbstractQueryOperations includingOnly(String... fields) {
        return null;
    }

    @Override
    public AbstractQueryOperations excludingOnly(String... fields) {
        return null;
    }

    @Override
    public AbstractQueryOperations mapColumn(String field, String column) {
        return null;
    }

    @Override
    public <T> AbstractQueryOperations mapColumn(String field, String column, TypeConverter<T> converter) {
        return null;
    }

    @Override
    public AbstractQueryOperations mapColumn(Map<String, String> fieldColumn) {
        return null;
    }

    @Override
    public AbstractQueryOperations bind(String field, Object value) {
        return null;
    }

    @Override
    public <T> AbstractQueryOperations bind(String field, Object value, Class<T> type) {
        return null;
    }

    @Override
    public AbstractQueryOperations bind(Map<String, Object> fieldValues) {
        return null;
    }

    @Override
    public AbstractQueryOperations bind(Object mappingObject) {
        return null;
    }

    @Override
    public AbstractQueryOperations usingKey(String... fields) {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public void execute() {

    }

    @Override
    public <T> List<T> list(Class<T> type) {
        return null;
    }

    @Override
    public <T> T single(Class<T> type) {
        return null;
    }

    @Override
    public <T> T unique(Class<T> type) {
        return null;
    }
}
