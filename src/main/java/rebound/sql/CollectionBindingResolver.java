package rebound.sql;


import java.util.Collection;

/**
 * Created by jcone on 8/4/15.
 */
public class CollectionBindingResolver extends AbstractBindingResolver {
    @Override
    public String resolve(int nextIndex, SqlParameter sqlParameter) {

        int length = 0;

        if (sqlParameter.getValue() instanceof Collection) {
            Collection collection = (Collection) sqlParameter.getValue();
            length = collection.size();
            sqlParameter.addIndexes(nextIndex, length);
        }

        return generateBindingPlaceholders(length);

    }
}
