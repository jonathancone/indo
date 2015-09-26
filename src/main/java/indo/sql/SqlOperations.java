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

import indo.jdbc.DataSources;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Jonathan Cone
 */
public interface SqlOperations {
    <T> List<T> query(Connection connection,
                      String sql,
                      Class<T> type,
                      Object... parameters);

    <T> List<T> query(Connection connection,
                      String sql,
                      RowProcessor<T> rowProcessor,
                      Object... parameters);

    <T> List<T> query(Connection connection,
                      String sql,
                      RowProcessor<T> rowProcessor,
                      SqlParameterProvider parameters);

    <T> List<T> query(Connection connection,
                      String sql,
                      RowProcessor<T> rowProcessor,
                      Supplier<List<T>> resultContainer,
                      SqlParameterProvider parameters);

    default <T> List<T> query(DataSource dataSource,
                              String sql,
                              Class<T> type,
                              Object... parameters) {
        return query(DataSources.getConnection(dataSource), sql, type, parameters);
    }

    default <T> List<T> query(DataSource dataSource,
                              String sql,
                              RowProcessor<T> rowProcessor,
                              Object... parameters) {
        return query(DataSources.getConnection(dataSource), sql, rowProcessor, parameters);
    }

    default <T> List<T> query(DataSource dataSource,
                              String sql,
                              RowProcessor<T> rowProcessor,
                              SqlParameterProvider parameters) {
        return query(DataSources.getConnection(dataSource), sql, rowProcessor, parameters);
    }

    default <T> List<T> query(DataSource dataSource,
                              String sql,
                              RowProcessor<T> rowProcessor,
                              Supplier<List<T>> resultContainer,
                              SqlParameterProvider parameters) {
        return query(DataSources.getConnection(dataSource), sql, rowProcessor, resultContainer, parameters);
    }
}
