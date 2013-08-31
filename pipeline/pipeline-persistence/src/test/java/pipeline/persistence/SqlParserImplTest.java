package pipeline.persistence;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.inject.annotation.TestedObject;

public class SqlParserImplTest extends UnitilsJUnit4 {
	@TestedObject
	private SqlParserImpl sqlParser;

	@Rule
	private ExpectedFileRule fileRule = new ExpectedFileRule(this);

	private File actualFile;

	@Test
	@ExpectedFile
	public void testParse() throws Exception {
		ParsedStatement parsed = getSqlParser().parse("");

		FileUtils.write(actualFile, "");

	}

	public SqlParserImpl getSqlParser() {
		return sqlParser;
	}

	public void setSqlParser(SqlParserImpl sqlParser) {
		this.sqlParser = sqlParser;
	}

}
