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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author Jonathan Cone
 */
public class MapRow {

    public static <T> T to(ResultSet rs, T container, MappingStrategy<T>... strategy) {
        return to(rs, Reflect.on(container), strategy);
    }

    public static <T> T to(ResultSet rs, Class<T> container, MappingStrategy<T>... strategy) {
        return to(rs, Reflect.on(container), strategy);
    }

    public static <T> T to(ResultSet rs, Reflect<T> container, MappingStrategy<T>... strategy) {

        ResultSetMetaData rsm = ResultSets.getMetaData(rs);

        IntStream.range(0, ResultSetMetaDatas.getColumnCount(rsm))
                .mapToObj(index -> ResultSetMetaDatas.getColumnName(rsm, index))
                .forEach(originalColumn -> {

                    Optional<String> matchedField = Arrays.stream(strategy)
                            .map(s -> s.findMatch(originalColumn, container.fieldNames()))
                            .filter(strategyMatch -> strategyMatch.isPresent())
                            .map(strategyMatch -> strategyMatch.get())
                            .findFirst();

                    if (matchedField.isPresent()) {
                        Object object = ResultSets.getObject(rs, originalColumn);

                        container.property(matchedField.get(), object);
                    } else {
                        throw new JdbcException("Could not map column \"%s\" to a property on %s!", originalColumn, container.getInstance().getClass());
                    }

                });

        return container.getInstance();
    }


}
