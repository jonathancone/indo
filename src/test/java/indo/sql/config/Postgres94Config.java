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

package indo.sql.config;

import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;

/**
 * Created by jcone on 10/2/15.
 */
public class Postgres94Config extends TestDatabaseConfig {

    public Postgres94Config(String user, String password, String url, String schemaSetupSql, String driver, Boolean caseSensitiveTableNames) {
        super(user, password, url, schemaSetupSql, driver, caseSensitiveTableNames);
    }

    @Override
    protected DataSource doCreateDataSource() throws Exception {
        PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setDataSourceName("postgres" + System.currentTimeMillis());
        ds.setDatabaseName("config");
        ds.setUser(getUser());
        ds.setPassword(getPassword());
        return ds;
    }

    @Override
    protected DefaultDataTypeFactory getDataTypeFactory() {
        return new PostgresqlDataTypeFactory();
    }
}
