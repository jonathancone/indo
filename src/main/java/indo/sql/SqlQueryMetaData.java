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
 * This class holds meta-data about a parsed query.  This allows for an input query to be transformed /
 * parsed into a different form before it is consumed.  This is useful for things like parameter expansion or
 * parameter replacement.
 *
 * @author Jonathan Cone
 */
public class SqlQueryMetaData {

    /**
     * A SQL statement that has undergone some transformation.
     */
    private String parsedSql;

    private SqlParameterProvider sqlParameterProvider;


    public SqlQueryMetaData(String parsedSql, SqlParameterProvider sqlParameterProvider) {
        this.parsedSql = parsedSql;
        this.sqlParameterProvider = sqlParameterProvider;
    }

    /**
     * @return The parsed SQL string.
     */
    public String getParsedSql() {
        return parsedSql;
    }

    /**
     * @return An object that is providing the parameters that correspond to the parsed SQL.
     */
    public SqlParameterProvider getSqlParameterProvider() {
        return sqlParameterProvider;
    }
}
