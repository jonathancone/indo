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

    default SqlQueryMetaData parse(String sourceSql, Object... parameters) {
        return parse(sourceSql, Lists.fromArray(parameters));
    }

    default SqlQueryMetaData parse(String sourceSql, List<?> parameters) {
        return parse(sourceSql, SqlParameters.fromList(parameters));
    }

    default SqlQueryMetaData parse(String sourceSql, Map<String, ?> nameValues) {
        return parse(sourceSql, SqlParameters.fromMap(nameValues));
    }

    default SqlQueryMetaData parse(String sourceSql, Object pojo) {
        return parse(sourceSql, PojoSqlParameters.fromPojo(pojo));
    }

    SqlQueryMetaData parse(String sourceSql, SqlParameterProvider parameters);
}
