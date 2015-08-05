package rebound.sql;

import java.lang.reflect.Array;

/**
 * Created by jcone on 8/3/15.
 */
public class ArrayBindingResolver extends AbstractBindingResolver {

    @Override
    public String resolve(int nextIndex, SqlParameter sqlParameter) {

        int length = 0;

        if (sqlParameter.getValue() instanceof Array) {
            length = Array.getLength(sqlParameter.getValue());
            sqlParameter.addIndexes(nextIndex, length);
        }

        return generateBindingPlaceholders(length);
    }
}
