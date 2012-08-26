package pipeline.persistence.jdbc;

@SuppressWarnings("serial")
public class JdbcWorkException extends RuntimeException {

	public JdbcWorkException() {
		super();
	}

	public JdbcWorkException(String message, Throwable cause) {
		super(message, cause);
	}

	public JdbcWorkException(String message) {
		super(message);
	}

	public JdbcWorkException(Throwable cause) {
		super(cause);
	}

}
