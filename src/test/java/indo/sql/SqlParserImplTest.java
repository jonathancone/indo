/*
 * Copyright 2015  Jonathan Cone
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

import indo.sql.test.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SqlParserImplTest {

    @Parameter(0)
    public String original;

    @Parameter(1)
    public String expected;

    @Parameter(2)
    public Parameters parameters;

    @Parameterized.Parameters
    public static Collection<Object[]> statements() {

        Employee employee = new Employee(1, "First", "Last");

        Parameters pojoParams = new Parameters();
        Parameters mapParams = new Parameters();
        Parameters iterableParams = new Parameters();


        return Arrays
                .asList(new Object[][]{
                        {
                                "SELECT * FROM table WHERE column = :column",
                                "SELECT * FROM table WHERE column = ?",
                                pojoParams},
                        {
                                "SELECT * FROM table WHERE column=:column",
                                "SELECT * FROM table WHERE column=?",
                                pojoParams},
                        {
                                "SELECT * FROM table WHERE column = :column ",
                                "SELECT * FROM table WHERE column = ? ",
                                pojoParams},
                        {
                                "SELECT * FROM table WHERE column=:column ",
                                "SELECT * FROM table WHERE column=? ",
                                pojoParams},
                        {
                                "SELECT * FROM table WHERE column = :column AND column = :column",
                                "SELECT * FROM table WHERE column = ? AND column = ?",
                                pojoParams},
                        {
                                "SELECT * FROM table WHERE column=:column AND column=:column",
                                "SELECT * FROM table WHERE column=? AND column=?",
                                pojoParams},
                        {
                                "SELECT * FROM table WHERE column = :column AND column = :column ",
                                "SELECT * FROM table WHERE column = ? AND column = ? ",
                                pojoParams},
                        {
                                "SELECT * FROM table WHERE column=:column AND column=:column ",
                                "SELECT * FROM table WHERE column=? AND column=? ",
                                pojoParams},
                        {
                                "SELECT * FROM table WHERE column=:column1 AND column=:column2 ",
                                "SELECT * FROM table WHERE column=? AND column=? ",
                                pojoParams},
                        {
                                "SELECT :column0 FROM table WHERE column=:column1 AND column=:column2 ",
                                "SELECT ? FROM table WHERE column=? AND column=? ",
                                pojoParams},
                        {
                                "SELECT * FROM table WHERE column IN(:column1) AND column IN(:column2)",
                                "SELECT * FROM table WHERE column IN(?) AND column IN(?)",
                                pojoParams},
                        {
                                "SELECT * FROM table WHERE column IN (:column1) AND column IN (:column2)",
                                "SELECT * FROM table WHERE column IN (?) AND column IN (?)",
                                pojoParams},
                });
    }

    @Test
    public void test() {
        StreamingSqlParser parser = new StreamingSqlParser();

        String parsed = parser.parse(original, parameters);

        Assert.assertEquals(expected, parsed);

    }

}
