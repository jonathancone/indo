package pipeline.persistence;

import org.junit.Rule;
import org.junit.Test;

public class SqlParserImplTest {
	private SqlParserImpl sqlParser;

	@Rule
	public ExpectedFileRule fileRule = new ExpectedFileRule(this);

	public SqlParserImplTest() {
		this.sqlParser = new SqlParserImpl();
	}

	@Test
	@ExpectedFile
	public void testParse() throws Exception {
		ParsedStatement parsed = getSqlParser().parse("");

		// FileUtils.write(actualFile, "");

	}

	public SqlParserImpl getSqlParser() {
		return sqlParser;
	}

	public void setSqlParser(SqlParserImpl sqlParser) {
		this.sqlParser = sqlParser;
	}

}
