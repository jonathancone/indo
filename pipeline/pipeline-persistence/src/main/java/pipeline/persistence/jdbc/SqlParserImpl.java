package pipeline.persistence.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SqlParserImpl implements SqlParser {

	private List<SqlParseProcessor> processors;

	public void parse(String sql) {
		if (StringUtils.isNotBlank(sql)) {

			for (int i = 0; i < sql.length(); i++) {

				char currentChar = sql.charAt(i);

				fireSqlParseEvent(new SqlParseEvent(sql.charAt(i)));
			}

		}
	}

	private void fireSqlParseEvent(SqlParseEvent event) {

		for (SqlParseProcessor processor : getProcessors()) {
			processor.onSqlParseEvent(event);
		}

	}

	public void addSqlParseProcessor(SqlParseProcessor processor) {
		getProcessors().add(processor);

	}

	public void removeSqlParseProcessor(SqlParseProcessor processor) {
		getProcessors().remove(processor);
	}

	public List<SqlParseProcessor> getProcessors() {
		if (processors == null) {
			processors = new ArrayList<SqlParseProcessor>();
		}
		return processors;
	}

}
