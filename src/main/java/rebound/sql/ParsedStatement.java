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

public class ParsedStatement {
    private String sourceSql;
    private String targetSql;
    private SqlParameters sqlParameters;
    private CallType callType;

    public ParsedStatement(String sourceSql, CallType callType, String targetSql, SqlParameters sqlParameters) {
        this.sourceSql = sourceSql;
        this.callType = callType;
        this.targetSql = targetSql;
        this.sqlParameters = sqlParameters;
    }

    public String getSourceSql() {
        return sourceSql;
    }

    public String getTargetSql() {
        return targetSql;
    }

    public SqlParameters getSqlParameters() {
        return sqlParameters;
    }

    public CallType getCallType() {
        return callType;
    }
}