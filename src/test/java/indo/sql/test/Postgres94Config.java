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

package indo.sql.test;

import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;
import java.util.Random;

/**
 * Configuration for Postgresql 9.4.
 *
 * @author Jonathan Cone
 */
public class Postgres94Config extends DatabaseConfig {

    @Override
    protected DataSource doCreateDataSource() throws Exception {

        Random random = new Random(System.currentTimeMillis());

        String source = Postgres94Config.class.getSimpleName() + random.nextLong();

        PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setDataSourceName(source);
        ds.setDatabaseName(source);
        ds.setUser(getUser());
        ds.setPassword(getPassword());
        return ds;
    }

    @Override
    protected DefaultDataTypeFactory getDataTypeFactory() {
        return new PostgresqlDataTypeFactory();
    }
}
