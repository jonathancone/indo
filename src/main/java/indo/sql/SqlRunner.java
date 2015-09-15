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

import indo.jdbc.ResultSets;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

/**
 * Created by jcone on 8/12/15.
 */
public class SqlRunner implements SqlOperations {
    private DataSource dataSource;
    private Connection connection;

    public SqlRunner(Connection connection) {
        this.connection = connection;
    }

    public SqlRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static Connection getConnection(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }
    }

    @Override
    public <T> T query(String sql, OnPreparedStatement<T> psc) {
        T t;
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(sql);
            t = psc.apply(ps);
        } catch (SQLException e) {
            throw Unchecked.exception(e);
        }

        return t;
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

    @Override
    public List<Row> list(String sql, Object... parameters) {
        return stream(sql, parameters)
                .map(Row::from)
                .collect(Collectors.toList());
    }

    protected Connection getConnection() {
        if (connection == null) {
            return getConnection(dataSource);
        }
        return connection;
    }

    protected DataSource getDataSource() {
        return dataSource;
    }
}
