package pipeline.persistence;

import java.util.List;
import java.util.Map;

public interface Query<T> {

	Query<T> query(String query);

	Query<T> using(String parameter, Object value);

	Query<T> using(Map<String, Object> parameters);

	List<T> asList();

	T[] asArray();

	T asObject();
}
