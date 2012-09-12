package pipeline.persistence.jdbc;

public interface SqlParser {
	void parse(String sql);

	void addSqlParseProcessor(SqlParseProcessor processor);

	void removeSqlParseProcessor(SqlParseProcessor processor);
}
