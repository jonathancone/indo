package rebound.sql;

import java.util.Map;

public interface SqlQuery {
	SqlQuery run(String sql);

	SqlQuery run(StringBuilder sql);

	<T> SqlQuery binding(Object param);

	SqlQuery binding(Map<String, Object> parameters);

	<T> SqlQuery listOf(T t);

	<T> SqlQuery single(T t);

}
