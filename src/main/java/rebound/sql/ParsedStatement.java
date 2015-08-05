package rebound.sql;

public class ParsedStatement {
    private String sourceSql;
    private String targetSql;
    private SqlParameters sqlParameters;
    private JdbcCallType jdbcCallType;

    public ParsedStatement(String sourceSql, JdbcCallType jdbcCallType, String targetSql, SqlParameters sqlParameters) {
        this.sourceSql = sourceSql;
        this.jdbcCallType = jdbcCallType;
        this.targetSql = targetSql;
        this.sqlParameters = sqlParameters;
    }

    public String getSourceSql() {
        return sourceSql;
    }

    public String getTargetSql() {
        return targetSql;
    }

    public SqlParameters getSqlParameters() {
        return sqlParameters;
    }

    public JdbcCallType getJdbcCallType() {
        return jdbcCallType;
    }
}