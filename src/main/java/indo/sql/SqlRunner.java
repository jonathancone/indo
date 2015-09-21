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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        return query(connection, sql, new ReflectionRowProcessor<>(type), Parameters.fromArray(parameters));
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             Class<T> type,
                             Map<String, Object> parameters) {
        return query(connection, sql, new ReflectionRowProcessor<>(type), Parameters.fromMap(parameters));
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             Class<T> type,
                             List<Parameter> parameters) {
        return query(connection, sql, new ReflectionRowProcessor<>(type), parameters);
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             RowProcessor<T> rowProcessor,
                             Object... parameters) {
        return query(connection, sql, rowProcessor, ArrayList<T>::new, Parameters.fromArray(parameters));
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             RowProcessor<T> rowProcessor,
                             Map<String, Object> parameters) {
        return query(connection, sql, rowProcessor, ArrayList<T>::new, Parameters.fromMap(parameters));
    }

    public <T> List<T> query(Connection connection,
                             String sql,
                             RowProcessor<T> rowProcessor,
                             List<Parameter> parameters) {
        return query(connection, sql, rowProcessor, ArrayList<T>::new, parameters);
    }


    public <T> List<T> query(Connection connection,
                             String sql,
                             RowProcessor<T> rowProcessor,
                             Supplier<List<T>> resultContainer,
                             List<Parameter> parameters) {

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Parameter parameter : parameters) {
                for (Integer index : parameter.getIndexes()) {
                    if (parameter.getType().isPresent()) {
                        ps.setObject(index, parameter.getValue().get(), parameter.getType().get());
                    } else {
                        ps.setObject(index, parameter.getValue().get());
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
