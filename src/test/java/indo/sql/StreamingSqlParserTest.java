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

import indo.util.Maps;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamingSqlParserTest {

    static Stream<Arguments> statements() {
        return Stream.of(
                Arguments.of(
                        "SELECT * FROM table WHERE column = :column",
                        "SELECT * FROM table WHERE column = ?",
                        Maps.newHashMap("column", 1)
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column=:column",
                        "SELECT * FROM table WHERE column=?",
                        Maps.newHashMap("column", 1)
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column = :column ",
                        "SELECT * FROM table WHERE column = ? ",
                        Maps.newHashMap("column", 1)
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column=:column ",
                        "SELECT * FROM table WHERE column=? ",
                        Maps.newHashMap("column", 1)
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column = :column AND column = :column",
                        "SELECT * FROM table WHERE column = ? AND column = ?",
                        Maps.newHashMap("column", 1)
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column=:column AND column=:column",
                        "SELECT * FROM table WHERE column=? AND column=?",
                        Maps.newHashMap("column", 1)
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column = :column AND column = :column ",
                        "SELECT * FROM table WHERE column = ? AND column = ? ",
                        Maps.newHashMap("column", 1)
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column=:column AND column=:column ",
                        "SELECT * FROM table WHERE column=? AND column=? ",
                        Maps.newHashMap("column", 1)
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column=:column1 AND column=:column2 ",
                        "SELECT * FROM table WHERE column=? AND column=? ",
                        Maps.newHashMap("column1", 1, "column2", 2)
                ),
                Arguments.of(
                        "SELECT :column1 FROM table WHERE column=:column2 AND column=:column3 ",
                        "SELECT ? FROM table WHERE column=? AND column=? ",
                        Maps.newHashMap("column1", 1, "column2", 2, "column3", 3)
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column IN(:column1) AND column IN(:column2)",
                        "SELECT * FROM table WHERE column IN(?) AND column IN(?)",
                        Maps.newHashMap("column1", 1, "column2", 2)
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column IN (:column1) AND column IN (:column2)",
                        "SELECT * FROM table WHERE column IN (?) AND column IN (?)",
                        Maps.newHashMap("column1", 1, "column2", 2)
                ),
                Arguments.of(
                        "SELECT :column1 FROM table",
                        "SELECT ? FROM table",
                        Maps.newHashMap("column1", 1)
                ),
                Arguments.of(
                        "SELECT ':column1' FROM table",
                        "SELECT ':column1' FROM table",
                        Maps.newHashMap()
                ),
                Arguments.of(
                        "SELECT \":column1\" FROM table",
                        "SELECT \":column1\" FROM table",
                        Maps.newHashMap()
                ),
                Arguments.of(
                        "SELECT \":column1\" FROM table",
                        "SELECT \":column1\" FROM table",
                        Maps.newHashMap()
                ),
                Arguments.of(
                        "SELECT '':column1'' FROM table",
                        "SELECT ''?'' FROM table",
                        Maps.newHashMap("column1", 1)
                ),
                Arguments.of(
                        "SELECT '':column1'' FROM table",
                        "SELECT ''?'' FROM table",
                        Maps.newHashMap("column1", 1)
                ),
                Arguments.of(
                        "SELECT \"\":column1\"\" FROM table",
                        "SELECT \"\"?\"\" FROM table",
                        Maps.newHashMap("column1", 1)
                ),
                Arguments.of(
                        "SELECT ''':column1''' FROM table",
                        "SELECT ''':column1''' FROM table",
                        Maps.newHashMap()
                ),
                Arguments.of(
                        "SELECT \"\"\":column1\"\"\" FROM table",
                        "SELECT \"\"\":column1\"\"\" FROM table",
                        Maps.newHashMap()
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column IN (:column1) AND column IN (:column2)",
                        "SELECT * FROM table WHERE column IN (?) AND column IN (?,?,?,?)",
                        Maps.newHashMap("column1", 1, "column2", new int[]{2, 3, 4, 5})
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column IN (:column1) AND column IN (:column2)",
                        "SELECT * FROM table WHERE column IN (?,?,?,?,?,?,?) AND column IN (?,?,?,?)",
                        Maps.newHashMap("column1", new int[]{1, 2, 3, 4, 5, 6, 7}, "column2", new int[]{8, 9, 10, 11})
                ),
                Arguments.of(
                        "SELECT * FROM table WHERE column IN (:column1) AND column IN (:column2)",
                        "SELECT * FROM table WHERE column IN (?,?,?,?,?,?,?) AND column IN (?)",
                        Maps.newHashMap("column1", new int[]{1, 2, 3, 4, 5, 6, 7}, "column2", new int[]{8})
                )
        );
    }

    @ParameterizedTest
    @MethodSource("statements")
    public void testParseWithParameterProvider(String original, String expected, Map<String, ?> mapParameters) {
        StreamingSqlParser parser = StreamingSqlParser.instance();

        SqlQueryMetaData meta = parser.parse(original, mapParameters);

        assertEquals(expected, meta.getParsedSql(), "The parsed SQL statement did not match the expected value.");

        SqlParameterProvider sqlParameterProvider = meta.getSqlParameterProvider();

        Set<String> parameterNames = mapParameters.keySet();

        for (String parameterName : parameterNames) {
            Optional<SqlParameter> parameter = sqlParameterProvider.findParameter(parameterName);

            if (parameter.isPresent()) {
                SqlParameter sqlParameter = parameter.get();

                Object o = mapParameters.get(parameterName);

                if (o instanceof int[]) {
                    int[] array = (int[]) o;

                    for (int i = 0; i < array.length; i++) {
                        assertTrue(sqlParameter.hasIndex(array[i]), "The parameter indexes should have contained the index.");
                    }
                    assertEquals(array.length, sqlParameter.getIndexes().size(), "The parameter should have contained the number of indexes in the array.");
                } else {
                    assertTrue(sqlParameter.hasIndex((Integer) o), "The parameter indexes should have contained the index.");
                }
            }
        }
    }
}
