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
import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jcone on 8/7/15.
 */
public class Quietly {

    public static Connection getConnection(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new UncheckedSQLException(e);
        }
    }

    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            throw new UncheckedSQLException(e);
        }
    }
}
