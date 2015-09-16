package indo.sql;

import java.sql.ResultSet;

/**
 * Created by JCone on 9/16/2015.
 */
public interface MappingStrategy<T> {
    boolean mapOnMatch(String column, Object value, T target);
}
