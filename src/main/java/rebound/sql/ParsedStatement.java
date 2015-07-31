package rebound.sql;

import java.util.List;

import org.apache.commons.collections.ListUtils;

public class ParsedStatement {
    private String sourceSql;
    private String parsedSql;
    private List<ParameterPart> parameters;
    private JdbcCallType jdbcCallType;

    public ParsedStatement(String sourceSql, String parsedSql,
                           List<ParameterPart> parameters, JdbcCallType jdbcCallType) {
        this.sourceSql = sourceSql;
        this.parsedSql = parsedSql;
        this.parameters = parameters;
        this.jdbcCallType = jdbcCallType;
    }

    public JdbcCallType getJdbcCallType() {
        return jdbcCallType;
    }

    public String getSourceSql() {
        return sourceSql;
    }

    public String getParsedSql() {
        return parsedSql;
    }

    public List<ParameterPart> getParameters() {
        return ListUtils.unmodifiableList(parameters);
    }

}