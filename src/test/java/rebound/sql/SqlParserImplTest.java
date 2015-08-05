package rebound.sql;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import rebound.sql.test.Employee;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SqlParserImplTest {

    @Parameter(0)
    public String original;

    @Parameter(1)
    public String expected;

    @Parameter(2)
    public SqlParameters sqlParameters;

    @Parameters
    public static Collection<Object[]> statements() {

        Employee employee = new Employee("First", "Last");

        SqlParameters pojoParams = new SqlParameters();
        SqlParameters mapParams = new SqlParameters();
        SqlParameters iterableParams = new SqlParameters();


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
        ReplacingSqlParser parser = new ReplacingSqlParser();

        ParsedStatement parsed = parser.parse(original, sqlParameters);

        Assert.assertEquals(expected, parsed.getTargetSql());

    }

}
