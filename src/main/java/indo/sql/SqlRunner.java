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
import indo.util.Unchecked;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static indo.log.Logger.debug;

/**
 * @author Jonathan Cone
 */
public class SqlRunner implements SqlOperations {
    private DataSource dataSource;
    private SqlParser sqlParser;

    public SqlRunner(DataSource dataSource) {
        this(dataSource, StreamingSqlParser.instance());
    }

    public SqlRunner(DataSource dataSource, SqlParser sqlParser) {
        this.dataSource = dataSource;
        this.sqlParser = sqlParser;
    }

    public <T> List<T> list(String sql,
                            Class<T> type,
                            Object... parameters) {
        return list(DataSources.getConnection(dataSource), sql, type, parameters);
    }

    public <T> List<T> list(String sql,
                            Class<T> type,
                            ColumnTypes columnTypes,
                            Object... parameters) {
        return list(DataSources.getConnection(dataSource), sql, type, columnTypes, parameters);
    }

    public <T> List<T> list(String sql,
                            Class<T> type,
                            Map<String, ?> parameters) {
        return list(DataSources.getConnection(dataSource), sql, type, parameters);
    }

    public <T> List<T> list(String sql,
                            Class<T> type,
                            ColumnTypes columnTypes,
                            Map<String, ?> parameters) {
        return list(DataSources.getConnection(dataSource), sql, type, columnTypes, parameters);
    }

    public <T> List<T> list(String sql,
                            RowProcessor<T> rowProcessor,
                            Object... parameters) {
        return list(DataSources.getConnection(dataSource), sql, rowProcessor, parameters);
    }

    public <T> List<T> list(String sql,
                            RowProcessor<T> rowProcessor,
                            Map<String, ?> parameters) {
        return list(DataSources.getConnection(dataSource), sql, rowProcessor, parameters);
    }

    public <T> List<T> list(String sql,
                            RowProcessor<T> rowProcessor,
                            SqlParameterProvider parameters) {
        return list(DataSources.getConnection(dataSource), sql, rowProcessor, parameters);
    }

    public <T> List<T> list(String sql,
                            RowProcessor<T> rowProcessor,
                            Supplier<List<T>> resultContainer,
                            SqlParameterProvider parameters) {
        return list(DataSources.getConnection(dataSource), sql, rowProcessor, resultContainer, parameters);
    }

    @Override
    public <T> List<T> list(Connection connection,
                            String sql,
                            Class<T> type,
                            Object... parameters) {
        return list(connection, sql, (rs) -> RowProcessor.using(type).map(rs), SqlParameters.fromArray(parameters));
    }

    @Override
    public <T> List<T> list(Connection connection,
                            String sql,
                            Class<T> type,
                            ColumnTypes columnTypes,
                            Object... parameters) {
        return list(connection, sql, (rs) -> RowProcessor.using(type, columnTypes).map(rs), SqlParameters.fromArray(parameters));
    }

    @Override
    public <T> List<T> list(Connection connection,
                            String sql,
                            Class<T> type,
                            Map<String, ?> parameters) {
        return list(connection, sql, (rs) -> RowProcessor.using(type).map(rs), SqlParameters.fromMap(parameters));
    }

    @Override
    public <T> List<T> list(Connection connection,
                            String sql,
                            Class<T> type,
                            ColumnTypes columnTypes,
                            Map<String, ?> parameters) {
        return list(connection, sql, (rs) -> RowProcessor.using(type, columnTypes).map(rs), SqlParameters.fromMap(parameters));
    }


    @Override
    public <T> List<T> list(Connection connection,
                            String sql,
                            RowProcessor<T> rowProcessor,
                            Object... parameters) {
        return list(connection, sql, rowProcessor, SqlParameters.fromArray(parameters));
    }

    @Override
    public <T> List<T> list(Connection connection,
                            String sql,
                            RowProcessor<T> rowProcessor,
                            Map<String, ?> parameters) {
        return list(connection, sql, rowProcessor, SqlParameters.fromMap(parameters));
    }

    @Override
    public <T> List<T> list(Connection connection,
                            String sql,
                            RowProcessor<T> rowProcessor,
                            SqlParameterProvider parameters) {
        return list(connection, sql, rowProcessor, ArrayList<T>::new, parameters);
    }

    @Override
    public <T> List<T> list(Connection connection,
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
