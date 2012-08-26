package pipeline.persistence.jdbc;

import java.sql.CallableStatement;

public class CallableWork extends PreparedWork {

	// ==============================================================
	// Constructors
	// ==============================================================
	private CallableWork(CallableStatement stmt) {
		super(stmt);
	}

	// ==============================================================
	// Static methods
	// ==============================================================

	public static CallableWork on(CallableStatement stmt) {
		return new CallableWork(stmt);
	}
}
