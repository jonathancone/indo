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

import indo.util.Lists;

import java.util.List;
import java.util.Map;

/**
 * Interface that defines parsing operations that may take place on a SQL
 * statement before its executed.
 *
 * @author Jonathan Cone
 */
public interface SqlParser {

    /**
     * Construct an instance of {@link SqlQueryMetaData} from an input SQL statement and parameters.
     *
     * @param sql        The input SQL statement.
     * @param parameters An Object array of input parameters.
     * @return The newly created SqlQueryMetaData instance.
     */
    default SqlQueryMetaData parse(String sql, Object[] parameters) {
        return parse(sql, Lists.fromArray(parameters));
    }

    /**
     * Construct an instance of {@link SqlQueryMetaData} from an input SQL statement and parameters.
     *
     * @param sql        The input SQL statement.
     * @param parameters An ordered List of input parameters.
     * @return The newly created SqlQueryMetaData instance.
     */
    default SqlQueryMetaData parse(String sql, List<?> parameters) {
        return parse(sql, SqlParameters.fromList(parameters));
    }

    /**
     * Construct an instance of {@link SqlQueryMetaData} from an input SQL statement and parameters.
     *
     * @param sql        The input SQL statement.
     * @param nameValues A Map of name-value pair parameters.
     * @return The newly created SqlQueryMetaData instance.
     */
    default SqlQueryMetaData parse(String sql, Map<String, ?> nameValues) {
        return parse(sql, SqlParameters.fromMap(nameValues));
    }

    /**
     * Construct an instance of {@link SqlQueryMetaData} from an input SQL statement and parameters.
     *
     * @param sql  The input SQL statement.
     * @param pojo A POJO whose properties will be used to construct the parameters.
     * @return The newly created SqlQueryMetaData instance.
     */
    default SqlQueryMetaData parse(String sql, Object pojo) {
        return parse(sql, PojoSqlParameters.fromPojo(pojo));
    }

    /**
     * Construct an instance of {@link SqlQueryMetaData} from an input SQL statement and parameters.
     *
     * @param sql        The input SQL statement.
     * @param parameters A {@link SqlParameterProvider} instance that will be used to provide the parameters.
     * @return The newly created SqlQueryMetaData instance.
     */
    SqlQueryMetaData parse(String sql, SqlParameterProvider parameters);
}
