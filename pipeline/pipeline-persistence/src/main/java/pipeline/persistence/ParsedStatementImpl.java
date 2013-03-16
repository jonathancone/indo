package pipeline.persistence;

import java.util.List;

import org.apache.commons.collections.ListUtils;

public class ParsedStatementImpl implements ParsedStatement {
	private String originalSql;
	private String parsedSql;
	private List<ParameterPart> parameters;

	public ParsedStatementImpl(String originalSql,
			List<ParameterPart> parameters) {
		this.originalSql = originalSql;
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
