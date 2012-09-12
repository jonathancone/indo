package pipeline.persistence.jdbc;

public interface SqlParseProcessor {
	void onSqlParseEvent(SqlParseEvent e);
}
