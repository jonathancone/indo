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

import indo.util.Unchecked;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static indo.log.Logs.debug;

/**
 * @author Jonathan Cone
 */
public class SqlRunner implements SqlOperations {
    private SqlParser sqlParser;

    public SqlRunner(SqlParser sqlParser) {
        this.sqlParser = sqlParser;
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             Class<T> type,
                             Object... parameters) {
        return query(connection, sql, (rs) -> new ReflectionRowProcessor<>(type).map(rs), SqlParameters.fromArray(parameters));
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             RowProcessor<T> rowProcessor,
                             Object... parameters) {
        return query(connection, sql, rowProcessor, SqlParameters.fromArray(parameters));
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             RowProcessor<T> rowProcessor,
                             SqlParameterProvider parameters) {
        return query(connection, sql, rowProcessor, ArrayList<T>::new, parameters);
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             RowProcessor<T> rowProcessor,
                             Supplier<List<T>> resultContainer,
                             SqlParameterProvider parameters) {


        SqlQueryMetaData metaData = sqlParser.parse(sql, parameters);

        String parsedSql = metaData.getParsedSql();

        debug(this, "Preparing statement - %s", parsedSql);

        try (PreparedStatement ps = connection.prepareStatement(parsedSql)) {

            for (SqlParameter sqlParameter : metaData.getSqlParameterProvider()) {
                for (Integer index : sqlParameter.getIndexes()) {

                    Optional<Integer> type = sqlParameter.type();
                    Optional<Object> value = sqlParameter.value();

                    if (type.isPresent()) {
                        if (value.isPresent()) {
                            ps.setObject(index, value.get(), type.get());
                        } else {
                            ps.setObject(index, null, type.get());
                        }
                    } else {
                        if (value.isPresent()) {
                            ps.setObject(index, value.get());
                        } else {
                            ps.setObject(index, null);
                        }
                    }
                }
            }

            List<T> results = resultContainer.get();

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(rowProcessor.map(rs));
                }
            }

            return results;
        } catch (SQLException e) {
            throw Unchecked.sqlException(e);
        }
    }
}
