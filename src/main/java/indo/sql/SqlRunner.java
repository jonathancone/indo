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

import indo.util.RowMappers;
import indo.util.Unchecked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by jcone on 9/14/15.
 */
public class SqlRunner implements SqlOperations {
    private static final Logger log = LoggerFactory.getLogger(SqlRunner.class);

    public <T> List<T> query(Connection connection,
                             String sql,
                             Class<T> type,
                             Object... parameters) {
        return query(connection, sql, (rs) -> RowMappers.map(rs, type), Arrays::asList);
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             Function<ResultSet, T> rowMapper,
                             Object... parameters) {
        return query(connection, sql, rowMapper, Arrays::asList);
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             Function<ResultSet, T> rowMapper,
                             Supplier<List<?>> parameters) {
        return query(connection, sql, rowMapper, ArrayList<T>::new, parameters);
    }

    @Override
    public <T> List<T> query(Connection connection,
                             String sql,
                             Function<ResultSet, T> rowMapper,
                             Supplier<List<T>> resultContainer,
                             Supplier<List<?>> parameters) {

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            List<?> params = parameters.get();

            int count = params.size();

            for (int i = 0; i < count; i++) {
                ps.setObject(i + 1, params.get(i));
            }

            List<T> results = resultContainer.get();

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(rowMapper.apply(rs));
                }
            }

            return results;
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }
}
