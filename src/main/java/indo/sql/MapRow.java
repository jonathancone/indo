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
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author Jonathan Cone
 */
public class MapRow {

    public static <T> T to(ResultSet rs, T container, MapMode... modes) {
        return to(ResultSets.getMetaData(rs), rs, Reflect.on(container), modes);
    }

    public static <T> T to(ResultSet rs, Class<T> container, MapMode... modes) {
        return to(ResultSets.getMetaData(rs), rs, Reflect.on(container), modes);
    }

    public static <T> T to(ResultSetMetaData rsm, ResultSet rs, Reflect<T> container, MapMode... modes) {

        IntStream.range(0, ResultSetMetaDatas.getColumnCount(rsm))
                .mapToObj(index -> ResultSetMetaDatas.getColumnName(rsm, index))
                .forEach(originalColumn -> {

                    Map<String, Field> fields = container.fields();

                    if (fields.containsKey(originalColumn)) {
                        // We found a match, nothing else to be done except for setting the property.
                        Object object = ResultSets.getObject(rs, originalColumn);

                        container.property(originalColumn, object);
                    } else {

                        String matchingColumn = originalColumn;

                        List<MapMode> mapModes = modes == null ? Collections.emptyList() : Arrays.asList(modes);

                        if (mapModes.contains(MapMode.IGNORE_CASE)) {
                            matchingColumn = matchingColumn.toLowerCase();
                        }

                        if (mapModes.contains(MapMode.IGNORE_UNDERSCORE)) {
                            matchingColumn = matchingColumn.replace("_", "");
                        }

                        String toTest = matchingColumn;

                        Optional<String> match = fields.keySet()
                                .stream()
                                .filter(fieldName -> fieldName.toLowerCase().equals(toTest))
                                .findFirst();

                        match.ifPresent(key -> container.property(key, ResultSets.getObject(rs, originalColumn)));
                        match.orElseThrow(() -> new JdbcException("Could not map column \"%s\" to a property on %s!", originalColumn, container.getInstance().getClass()));

                    }
                });
        return container.getInstance();
    }

}
