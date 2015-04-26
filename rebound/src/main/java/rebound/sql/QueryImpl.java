package rebound.sql;

import java.sql.Connection;
import java.util.Map;

public class QueryImpl implements Query {
    private Connection conn;
    private String originalSql;
    private SqlParser sqlParser;

    public Query run(String sql) {
	// TODO Auto-generated method stub
	return null;
    }

    public Query run(StringBuilder sql) {
	// TODO Auto-generated method stub
	return null;
    }

    public Query binding(Map<String, Object> parameters) {
	// TODO Auto-generated method stub
	return null;
    }

    public <T> Query binding(T t) {
	// TODO Auto-generated method stub
	return null;
    }

    public <T> Query listOf(T t) {
	// TODO Auto-generated method stub
	return null;
    }

    public <T> Query andFetchSetOf(T t) {
	// TODO Auto-generated method stub
	return null;
    }

    public <T> Query single(T t) {
	// TODO Auto-generated method stub
	return null;
    }

}
