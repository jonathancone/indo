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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jcone on 8/14/15.
 */
public class QueryMetaData {

    private String sourceSql;
    private String parsedSql;

    private Parameters parameters;

    private String tableNameOverride;
    private Map<String, String> fieldColumnNameOverride;

    public QueryMetaData() {
        this.parameters = new Parameters();
        this.fieldColumnNameOverride = new HashMap<>();
    }

    public String getSourceSql() {
        return sourceSql;
    }

    public void setSourceSql(String sourceSql) {
        this.sourceSql = sourceSql;
    }

    public String getParsedSql() {
        return parsedSql;
    }

    public void setParsedSql(String parsedSql) {
        this.parsedSql = parsedSql;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public String getTableNameOverride() {
        return tableNameOverride;
    }

    public void setTableNameOverride(String tableNameOverride) {
        this.tableNameOverride = tableNameOverride;
    }

    public Map<String, String> getFieldColumnNameOverride() {
        return fieldColumnNameOverride;
    }

    public void setFieldColumnNameOverride(Map<String, String> fieldColumnNameOverride) {
        this.fieldColumnNameOverride = fieldColumnNameOverride;
    }
}
