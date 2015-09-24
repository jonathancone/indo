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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StreamingSqlParserTest {

    @Parameter(0)
    public String original;

    @Parameter(1)
    public String expected;

    @Parameter(2)
    public Map<String, ?> mapParameters;


    @Parameterized.Parameters
    public static Collection<Object[]> statements() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("column", "VALUE");
        map.put("column0", "VALUE");
        map.put("column1", "VALUE");
        map.put("column2", "VALUE");


        return Arrays
                .asList(new Object[][] {
                        {
                                "SELECT * FROM table WHERE column = :column",
                                "SELECT * FROM table WHERE column = ?",
                                map
                        },
                        {
                                "SELECT * FROM table WHERE column=:column",
                                "SELECT * FROM table WHERE column=?",
                                map
                        },
                        {
                                "SELECT * FROM table WHERE column = :column ",
                                "SELECT * FROM table WHERE column = ? ",
                                map
                        },
                        {
                                "SELECT * FROM table WHERE column=:column ",
                                "SELECT * FROM table WHERE column=? ",
                                map
                        },
                        {
                                "SELECT * FROM table WHERE column = :column AND column = :column",
                                "SELECT * FROM table WHERE column = ? AND column = ?",
                                map
                        },
                        {
                                "SELECT * FROM table WHERE column=:column AND column=:column",
                                "SELECT * FROM table WHERE column=? AND column=?",
                                map
                        },
                        {
                                "SELECT * FROM table WHERE column = :column AND column = :column ",
                                "SELECT * FROM table WHERE column = ? AND column = ? ",
                                map
                        },
                        {
                                "SELECT * FROM table WHERE column=:column AND column=:column ",
                                "SELECT * FROM table WHERE column=? AND column=? ",
                                map
                        },
                        {
                                "SELECT * FROM table WHERE column=:column1 AND column=:column2 ",
                                "SELECT * FROM table WHERE column=? AND column=? ",
                                map
                        },
                        {
                                "SELECT :column0 FROM table WHERE column=:column1 AND column=:column2 ",
                                "SELECT ? FROM table WHERE column=? AND column=? ",
                                map
                        },
                        {
                                "SELECT * FROM table WHERE column IN(:column1) AND column IN(:column2)",
                                "SELECT * FROM table WHERE column IN(?) AND column IN(?)",
                                map
                        },
                        {
                                "SELECT * FROM table WHERE column IN (:column1) AND column IN (:column2)",
                                "SELECT * FROM table WHERE column IN (?) AND column IN (?)",
                                map
                        },
                        {
                                "SELECT :column1 FROM table",
                                "SELECT ? FROM table",
                                map
                        },
                        {
                                "SELECT ':column1' FROM table",
                                "SELECT ':column1' FROM table",
                                map
                        },
                        {
                                "SELECT \":column1\" FROM table",
                                "SELECT \":column1\" FROM table",
                                map
                        },
                        {
                                "SELECT '':column1'' FROM table",
                                "SELECT '':column1'' FROM table",
                                map
                        },
                        {
                                "SELECT \"\":column1\"\" FROM table",
                                "SELECT \"\":column1\"\" FROM table",
                                map
                        },
                });
    }

    @Test
    public void test() {
        StreamingSqlParser parser = new StreamingSqlParser();

        SqlQueryMetaData meta = parser.parse(original, mapParameters);

        System.out.println(meta.getSourceSql());
        System.out.println(meta.getParsedSql());
        System.out.println("");
        assertEquals(expected, meta.getParsedSql());


    }

}
