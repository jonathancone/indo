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


import indo.jdbc.JdbcException;
import indo.jdbc.ResultSetMetaDatas;
import indo.jdbc.ResultSets;
import indo.util.Reflect;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author Jonathan Cone
 */
public class RowMappers {
    public static <T> T map(ResultSet rs, Class<T> type) {
        return map(rs, type, EnumSet.of(MapMode.IGNORE_CASE, MapMode.IGNORE_UNDERSCORE));
    }

    public static <T> T map(ResultSet rs, Class<T> type, EnumSet<MapMode> modes) {

        ResultSetMetaData rsm = ResultSets.getMetaData(rs);

        Reflect<T> result = Reflect.on(type).newInstance();

        IntStream.range(0, ResultSetMetaDatas.getColumnCount(rsm))
                .mapToObj(index -> ResultSetMetaDatas.getColumnName(rsm, index))
                .forEach(originalColumn -> {

                    Map<String, Field> fields = result.fields();

                    if (fields.containsKey(originalColumn)) {
                        // We found a match, nothing else to be done except for setting the property.
                        result.property(originalColumn, ResultSets.getObject(rs, originalColumn));
                    } else {

                        String matchingColumn = originalColumn;

                        if (modes.contains(MapMode.IGNORE_CASE)) {
                            matchingColumn = matchingColumn.toLowerCase();
                        }

                        if (modes.contains(MapMode.IGNORE_UNDERSCORE)) {
                            matchingColumn = matchingColumn.replace("_", "");
                        }

                        String toTest = matchingColumn;

                        Optional<String> match = fields.keySet()
                                .stream()
                                .filter(fieldName -> fieldName.toLowerCase().equals(toTest))
                                .findFirst();

                        match.ifPresent(key -> result.property(key, ResultSets.getObject(rs, originalColumn)));
                        match.orElseThrow(() -> new JdbcException("Could not map column \"%s\" to a property on class %s!", originalColumn, type));

                    }
                });
        return result.getInstance();
    }

}
