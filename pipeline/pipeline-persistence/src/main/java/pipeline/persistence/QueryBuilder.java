package pipeline.persistence;

public interface QueryBuilder {

	<T> Query<T> createQuery(String query, Class<T> root);

	<T> Query<T> createQuery(String query, Class<T> root, Object... params);

	<T> Query<T> createQuery(String query, Class<T> root, ParameterSource params);

}
