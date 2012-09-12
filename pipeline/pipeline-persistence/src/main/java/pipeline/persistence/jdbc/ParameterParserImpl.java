package pipeline.persistence.jdbc;

import pipeline.persistence.Parameters;

public class ParameterParserImpl implements SqlParseProcessor {

	private Parameters parameters;

	private boolean insideLiteral;

	public ParameterParserImpl() {
	}

	public void onSqlParseEvent(SqlParseEvent e) {
		String token = e.getCurrentToken();

		if (token.startsWith("'")) {
			toggleLiteral();
		}

		if (token.endsWith("'")) {
			toggleLiteral();
		}

	}

	private void toggleLiteral() {
		setInsideLiteral(!isInsideLiteral());
	}

	private boolean isInsideLiteral() {
		return insideLiteral;
	}

	private void setInsideLiteral(boolean insideLiteral) {
		this.insideLiteral = insideLiteral;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

}
