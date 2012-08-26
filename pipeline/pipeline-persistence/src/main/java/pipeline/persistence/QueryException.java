package pipeline.persistence;

@SuppressWarnings("serial")
public class QueryException extends DataAccessException {

	public QueryException() {
		super();
	}

	public QueryException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryException(String message) {
		super(message);
	}

	public QueryException(Throwable cause) {
		super(cause);
	}

}
