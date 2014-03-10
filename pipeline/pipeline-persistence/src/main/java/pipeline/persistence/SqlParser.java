package pipeline.persistence;

public interface SqlParser {
	ParsedStatement parse(String sql);

}
