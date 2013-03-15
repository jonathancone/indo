package pipeline.persistence;

import java.util.List;
import java.util.Map;

public interface Query {

	Query bind(String parameter, Object value);

	Query bind(Integer parameter, Object value);

	Query bind(String parameter, Object value, Integer sqlType);

	Query bind(Integer parameter, Object value, Integer sqlType);

	Query bindAll(Object object);

	Query bindAll(Map<String, Object> parameters);

	<T> List<T> executeForList(Class<T> type);

	<T> T executeForObject(Class<T> type);

	<T> T[] executeForArray(Class<T> type);

}
