package rebound.sql;

public class ParameterPart {
	private int bindStart;
	private int identifierStart;
	private int identifierEnd;
	private String identifier;

	public ParameterPart(int bindStartingIndex, int javaIdentifierStart,
			int javaIdentifierEnd, String identifier) {
		this.bindStart = bindStartingIndex;
		this.identifierStart = javaIdentifierStart;
		this.identifierEnd = javaIdentifierEnd;
		this.identifier = identifier;
	}

	public int getBindStartingIndex() {
		return bindStart;
	}

	public int getJavaIdentifierStart() {
		return identifierStart;
	}

	public int getJavaIdentifierEnd() {
		return identifierEnd;
	}

	public String getIdentifier() {
		return identifier;
	}

}