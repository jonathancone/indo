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

/**
 * Created by jcone on 8/14/15.
 */
public class SqlQueryMetaData {

    private String sourceSql;
    private String parsedSql;

    private SqlParameterProvider sqlParameterProvider;

    public SqlQueryMetaData(String sourceSql, String parsedSql, SqlParameterProvider sqlParameterProvider) {
        this.sourceSql = sourceSql;
        this.parsedSql = parsedSql;
        this.sqlParameterProvider = sqlParameterProvider;
    }

    public String getSourceSql() {
        return sourceSql;
    }

    public String getParsedSql() {
        return parsedSql;
    }

    public SqlParameterProvider getSqlParameterProvider() {
        return sqlParameterProvider;
    }
}
