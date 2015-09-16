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

package indo.jdbc.experimental;

import indo.jdbc.DataSources;
import indo.jdbc.ResultSets;
import indo.sql.Row;
import indo.sql.SqlOperations;
import indo.util.CheckedConsumer;
import indo.util.Unchecked;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

/**
 * Created by jcone on 8/12/15.
 */
public class LambdaSqlRunner implements SqlOperations {
    private DataSource dataSource;
    private Connection connection;

    public LambdaSqlRunner(Connection connection) {
        this.connection = connection;
    }

    public LambdaSqlRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public <S> S query(String sql, Function<PreparedStatement, S> psc) {

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            return psc.apply(ps);
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }

    @SuppressWarnings("RedundantCast")
    Stream<ResultSet> stream(String sql, Object... parameters) {
        return query(sql, ps -> {

            AtomicInteger index = new AtomicInteger();

            if (nonNull(parameters)) {
                Arrays.stream(parameters).forEachOrdered(
                        (CheckedConsumer<Object>) param -> ps.setObject(index.incrementAndGet(), param));
            }

            return ResultSets.stream(ps);

        });
    }

    public List<Row> list(String sql, Object... parameters) {
        return stream(sql, parameters)
                .map(Row::from)
                .collect(Collectors.toList());
    }

    protected Connection getConnection() {
        if (connection == null) {
            return DataSources.getConnection(dataSource);
        }
        return connection;
    }

    protected DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public <T> List<T> query(Connection connection, String sql, Function<ResultSet, T> rowMapper, Supplier<List<T>> resultContainer, Supplier<List<?>> parameters) {
        return null;
    }
}
