package pipeline.core.query;

import pipeline.core.ResultSetMapper;

public interface Query<T> {
	T run(String query);

	T run(String query, Object... params);

	T run(String query, ResultSetMapper<T> mapper, Object... params);

}
