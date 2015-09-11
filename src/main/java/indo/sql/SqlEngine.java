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

import indo.util.JdbcException;
import indo.util.Multi;
import indo.util.Unchecked;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcone on 8/12/15.
 */
public class SqlEngine implements SqlOperations {
    private DataSource dataSource;
    private SqlParser sqlParser;

    public SqlEngine(DataSource dataSource) {
        this.dataSource = dataSource;
        this.sqlParser = new StreamingSqlParser();
    }

    public static Connection getConnection(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    protected Connection getConnection() {
        return getConnection(dataSource);
    }

    protected DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public <T> T query(String sql, PreparedStatementCommand<T> psc) {
        T t = null;
        try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
            t = psc.perform(pstmt);
        } catch (SQLException e) {
            Unchecked.exception(e);
        }
        return t;
    }

    @Override
    public <T> List<T> query(String sql, final ResultSetCommand<T> rsc, final Object... parameters) {
        return query(sql, new PreparedStatementCommand<List<T>>() {
            @Override
            public List<T> perform(PreparedStatement pstmt) throws SQLException {
                if (Multi.isNotEmpty(parameters)) {
                    for (int i = 0; i < parameters.length; i++) {
                        pstmt.setObject(i + 1, parameters[i]);
                    }
                }

                try (ResultSet rs = pstmt.executeQuery()) {
                    List<T> list = new ArrayList<>();

                    while (rs.next()) {
                        list.add(rsc.perform(rs));
                    }

                    return list;
                }
            }
        });
    }
}
