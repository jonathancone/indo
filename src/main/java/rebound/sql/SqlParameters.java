package rebound.sql;

import org.apache.commons.collections.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * Created by jcone on 8/1/15.
 */
public class SqlParameters implements Iterable<SqlParameter> {

    private List<SqlParameter> parameters;

    @Override
    public Iterator<SqlParameter> iterator() {
        return parameters.iterator();
    }

    public boolean hasParameters() {
        return CollectionUtils.isNotEmpty(parameters);
    }

}
