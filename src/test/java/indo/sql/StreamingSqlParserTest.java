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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class StreamingSqlParserTest {

    @Parameter(0)
    public String original;

    @Parameter(1)
    public String expected;

    @Parameter(2)
    public Map<String, ?> mapParameters;

    @Parameterized.Parameters(name = "{index}: {1}")
    public static Collection<Object[]> statements() {
        return Arrays
                .asList(new Object[][]{
                        {
                                "SELECT * FROM table WHERE column = :column",
                                "SELECT * FROM table WHERE column = ?",
                                Maps.newHashMap("column", 1)
                        },
                        {
                                "SELECT * FROM table WHERE column=:column",
                                "SELECT * FROM table WHERE column=?",
                                Maps.newHashMap("column", 1)
                        },
                        {
                                "SELECT * FROM table WHERE column = :column ",
                                "SELECT * FROM table WHERE column = ? ",
                                Maps.newHashMap("column", 1)
                        },
                        {
                                "SELECT * FROM table WHERE column=:column ",
                                "SELECT * FROM table WHERE column=? ",
                                Maps.newHashMap("column", 1)
                        },
                        {
                                "SELECT * FROM table WHERE column = :column AND column = :column",
                                "SELECT * FROM table WHERE column = ? AND column = ?",
                                Maps.newHashMap("column", 1)
                        },
                        {
                                "SELECT * FROM table WHERE column=:column AND column=:column",
                                "SELECT * FROM table WHERE column=? AND column=?",
                                Maps.newHashMap("column", 1)
                        },
                        {
                                "SELECT * FROM table WHERE column = :column AND column = :column ",
                                "SELECT * FROM table WHERE column = ? AND column = ? ",
                                Maps.newHashMap("column", 1)
                        },
                        {
                                "SELECT * FROM table WHERE column=:column AND column=:column ",
                                "SELECT * FROM table WHERE column=? AND column=? ",
                                Maps.newHashMap("column", 1)
                        },
                        {
                                "SELECT * FROM table WHERE column=:column1 AND column=:column2 ",
                                "SELECT * FROM table WHERE column=? AND column=? ",
                                Maps.newHashMap("column1", 1, "column2", 2)
                        },
                        {
                                "SELECT :column1 FROM table WHERE column=:column2 AND column=:column3 ",
                                "SELECT ? FROM table WHERE column=? AND column=? ",
                                Maps.newHashMap("column1", 1, "column2", 2, "column3", 3)
                        },
                        {
                                "SELECT * FROM table WHERE column IN(:column1) AND column IN(:column2)",
                                "SELECT * FROM table WHERE column IN(?) AND column IN(?)",
                                Maps.newHashMap("column1", 1, "column2", 2)
                        },
                        {
                                "SELECT * FROM table WHERE column IN (:column1) AND column IN (:column2)",
                                "SELECT * FROM table WHERE column IN (?) AND column IN (?)",
                                Maps.newHashMap("column1", 1, "column2", 2)
                        },
                        {
                                "SELECT :column1 FROM table",
                                "SELECT ? FROM table",
                                Maps.newHashMap("column1", 1)
                        },
                        {
                                "SELECT ':column1' FROM table",
                                "SELECT ':column1' FROM table",
                                Maps.newHashMap()
                        },
                        {
                                "SELECT \":column1\" FROM table",
                                "SELECT \":column1\" FROM table",
                                Maps.newHashMap()
                        },
                        {
                                "SELECT \":column1\" FROM table",
                                "SELECT \":column1\" FROM table",
                                Maps.newHashMap()
                        },
                        {
                                "SELECT '':column1'' FROM table",
                                "SELECT ''?'' FROM table",
                                Maps.newHashMap("column1", 1)
                        },
                        {
                                "SELECT '':column1'' FROM table",
                                "SELECT ''?'' FROM table",
                                Maps.newHashMap("column1", 1)
                        },
                        {
                                "SELECT \"\":column1\"\" FROM table",
                                "SELECT \"\"?\"\" FROM table",
                                Maps.newHashMap("column1", 1)
                        },
                        {
                                "SELECT ''':column1''' FROM table",
                                "SELECT ''':column1''' FROM table",
                                Maps.newHashMap()
                        },
                        {
                                "SELECT \"\"\":column1\"\"\" FROM table",
                                "SELECT \"\"\":column1\"\"\" FROM table",
                                Maps.newHashMap()
                        },
                        {
                                "SELECT * FROM table WHERE column IN (:column1) AND column IN (:column2)",
                                "SELECT * FROM table WHERE column IN (?) AND column IN (?,?,?,?)",
                                Maps.newHashMap("column1", 1, "column2", new int[]{2, 3, 4, 5})
                        },
                        {
                                "SELECT * FROM table WHERE column IN (:column1) AND column IN (:column2)",
                                "SELECT * FROM table WHERE column IN (?,?,?,?,?,?,?) AND column IN (?,?,?,?)",
                                Maps.newHashMap("column1", new int[]{1, 2, 3, 4, 5, 6, 7}, "column2", new int[]{8, 9, 10, 11})
                        },
                        {
                                "SELECT * FROM table WHERE column IN (:column1) AND column IN (:column2)",
                                "SELECT * FROM table WHERE column IN (?,?,?,?,?,?,?) AND column IN (?)",
                                Maps.newHashMap("column1", new int[]{1, 2, 3, 4, 5, 6, 7}, "column2", new int[]{8})
                        },

                });
    }

    @Test
    public void testParseWithParameterProvider() {
        StreamingSqlParser parser = new StreamingSqlParser();

        SqlQueryMetaData meta = parser.parse(original, mapParameters);

        assertEquals("The parsed SQL statement did not match the expected value.", expected, meta.getParsedSql());

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
                        assertTrue("The parameter indexes should have contained the index.", sqlParameter.hasIndex(array[i]));
                    }
                    assertEquals("The parameter should have contained the number of indexes in the array.", array.length, sqlParameter.getIndexes().size());
                } else {
                    assertTrue("The parameter indexes should have contained the index.", sqlParameter.hasIndex((Integer) o));
                }
            }
        }
    }
}
