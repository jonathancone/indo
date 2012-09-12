package pipeline.persistence.jdbc;

public class SqlParseEvent {
	private char currentToken;

	public SqlParseEvent(char currentToken) {
		this.currentToken = currentToken;
	}

	public char getCurrentToken() {
		return currentToken;
	}

}
