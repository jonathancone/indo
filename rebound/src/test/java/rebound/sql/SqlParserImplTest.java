package rebound.sql;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class SqlParserImplTest {

    @Parameter(0)
    public String original;

    @Parameter(1)
    public String expected;

    @Parameters
    public static Collection<Object[]> statements() {
	return Arrays
		.asList(new Object[][] {
			{ "SELECT * FROM table WHERE column = :column",
				"SELECT * FROM table WHERE column = ?" },
			{ "SELECT * FROM table WHERE column=:column",
				"SELECT * FROM table WHERE column=?" },
			{ "SELECT * FROM table WHERE column = :column ",
				"SELECT * FROM table WHERE column = ? " },
			{ "SELECT * FROM table WHERE column=:column ",
				"SELECT * FROM table WHERE column=? " },
			{
				"SELECT * FROM table WHERE column = :column AND column = :column",
				"SELECT * FROM table WHERE column = ? AND column = ?" },
			{
				"SELECT * FROM table WHERE column=:column AND column=:column",
				"SELECT * FROM table WHERE column=? AND column=?" },
			{
				"SELECT * FROM table WHERE column = :column AND column = :column ",
				"SELECT * FROM table WHERE column = ? AND column = ? " },
			{
				"SELECT * FROM table WHERE column=:column AND column=:column ",
				"SELECT * FROM table WHERE column=? AND column=? " },
			{
				"SELECT * FROM table WHERE column=:column1 AND column=:column2 ",
				"SELECT * FROM table WHERE column=? AND column=? " },
		});
    }

    @Test
    public void test() {
	SqlParserImpl parser = new SqlParserImpl();

	ParsedStatement parsed = parser.parse(original);

	assertEquals(expected, parsed.getParsedSql());

	parsed.getParameters().get(0).getIdentifier();
    }

}
