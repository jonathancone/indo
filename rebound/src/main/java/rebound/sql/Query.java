package rebound.sql;

import java.util.Map;

public interface Query {
    Query run(String sql);

    Query run(StringBuilder sql);

    <T> Query binding(T parameterObject);

    Query binding(Map<String, Object> parameters);

    <T> Query listOf(T t);

    <T> Query single(T t);

}
