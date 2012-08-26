package pipeline.persistence;

/**
 * Interface for a factory which dispenses {@link Query} instances.
 * 
 * @author Jonathan Cone
 */
public interface QueryFactory {

	<T> Query<T> on(Class<T> root);

	<T> Query<T> on(T root);

}
