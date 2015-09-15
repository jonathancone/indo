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

package indo.util;


import indo.jdbc.ResultSetMetaDatas;
import indo.jdbc.ResultSets;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.EnumSet;
import java.util.stream.IntStream;

public class RowMappers {
    static <T> T map(ResultSet rs, Class<T> type) {
        return map(rs, type, EnumSet.of(MapMode.CONVERT_CASE, MapMode.CONVERT_UNDERSCORE));
    }

    static <T> T map(ResultSet rs, Class<T> type, EnumSet<MapMode> modes) {

        ResultSetMetaData rsm = ResultSets.getMetaData(rs);

        Reflect<T> result = Reflect.on(type).newInstance();

        IntStream.range(0, ResultSetMetaDatas.getColumnCount(rsm))
                .mapToObj(index -> ResultSetMetaDatas.getColumnName(rsm, index))
                .forEach(column -> result.property(column, ResultSets.getObject(rs, column)));

        return result.getInstance();
    }

}
