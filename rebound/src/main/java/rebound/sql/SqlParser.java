package rebound.sql;

public interface SqlParser {
    ParsedStatement parse(String originalSql);
}
