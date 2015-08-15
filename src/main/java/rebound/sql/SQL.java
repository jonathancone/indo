/*
 * Copyright (c) 2015 Rebound Contributors
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

package rebound.sql;

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
public class SQL {
    private DataSource dataSource;
    private boolean sqlOnlyMode;

    public SQL(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected Connection getConnection() {
        return Quietly.getConnection(dataSource);
    }

    protected boolean isSqlOnlyMode() {
        return sqlOnlyMode;
    }

    protected void setSqlOnlyMode(boolean sqlOnlyMode) {
        this.sqlOnlyMode = sqlOnlyMode;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> query(String sql, ResultMapper<T> mapper, Object... parameters) {

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {

            if (Tuples.isNotEmpty(parameters)) {
                for (int i = 0; i < parameters.length; i++) {
                    stmt.setObject(i + 1, parameters[i]);
                }
            }

            try (ResultSet rs = stmt.executeQuery()) {

                List list = new ArrayList();

                while (rs.next()) {
                    list.add(mapper.map(rs, rs.getRow()));
                }

                return list;
            }

        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }

    }
}
