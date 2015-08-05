package rebound.sql;

public interface SqlParser {
    ParsedStatement parse(String sourceSql, SqlParameters sqlParameters);
}
