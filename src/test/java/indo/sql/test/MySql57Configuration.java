/*
 * Copyright 2017 Indo Contributors
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

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;

import javax.sql.DataSource;

/**
 * Configuration for MySQL 5.7.
 *
 * @author Jonathan Cone
 */
public class MySql57Configuration extends DatabaseConfiguration {

    @Override
    protected DataSource doCreateDataSource() throws Exception {

        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(getUrl());
        ds.setUser(getUser());
        ds.setPassword(getPassword());

        return ds;
    }

    @Override
    protected DefaultDataTypeFactory getDataTypeFactory() {
        return new MySqlDataTypeFactory();
    }
}
