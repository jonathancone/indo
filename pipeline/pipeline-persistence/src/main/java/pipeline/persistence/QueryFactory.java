package pipeline.persistence;

/**
 * Interface for a factory which dispenses {@link Query} instances.
 * 
 * @author Jonathan Cone
 */
public interface QueryFactory {
	Query withSQL(String sql);
}
