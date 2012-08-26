package pipeline.persistence.jdbc;

@SuppressWarnings("serial")
public class JdbcException extends RuntimeException {

	public JdbcException() {
		super();
	}

	public JdbcException(String message, Throwable cause) {
		super(message, cause);
	}

	public JdbcException(String message) {
		super(message);
	}

	public JdbcException(Throwable cause) {
		super(cause);
	}

}
