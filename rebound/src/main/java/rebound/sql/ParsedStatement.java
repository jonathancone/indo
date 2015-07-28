package rebound.sql;

import java.util.List;

import org.apache.commons.collections.ListUtils;

public class ParsedStatement {
	private String originalSql;
	private String parsedSql;
	private List<ParameterPart> parameters;

	public ParsedStatement(String originalSql, String parsedSql,
			List<ParameterPart> parameters) {
		this.originalSql = originalSql;
		this.parsedSql = parsedSql;
		this.parameters = parameters;
	}

	public String getOriginalSql() {
		return originalSql;
	}

	public String getParsedSql() {
		return parsedSql;
	}

	public List<ParameterPart> getParameters() {
		return ListUtils.unmodifiableList(parameters);
	}

}