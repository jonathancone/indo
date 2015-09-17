package indo.sql;

import java.sql.ResultSet;

/**
 * Created by JCone on 9/17/2015.
 */
@FunctionalInterface
public interface RowProcessor<T> {
    T map(ResultSet rs);
}
