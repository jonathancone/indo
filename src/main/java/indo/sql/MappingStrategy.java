package indo.sql;

/**
 * This interface is used to implement various query result mapping behaviors.
 *
 * @author Jonathan Cone
 */
public interface MappingStrategy<T> {
    /**
     * Attempt to map a column to a target object, if a match is found, set the value on the target object.
     *
     * @param column The column to match to the target object.
     * @param value  The value to set on the target object if a column match is found.
     * @param target The target object to match the column against.
     * @return true if the target object had a match for the supplied column.
     */
    boolean mapOnMatch(String column, Object value, T target);
}
