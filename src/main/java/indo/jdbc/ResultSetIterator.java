package indo.jdbc;

import java.sql.ResultSet;
import java.util.Iterator;

/**
 * Created by jcone on 9/11/2015.
 */
public class ResultSetIterator implements Iterator<ResultSet> {

    private ResultSet rs;

    public ResultSetIterator(ResultSet resultSet) {
        this.rs = resultSet;
    }

    @Override
    public boolean hasNext() {
        boolean next = ResultSets.next(rs);

        if (!next) {
            ResultSets.close(rs);
        }

        return next;
    }

    @Override
    public ResultSet next() {
        try {
            return rs;
        } catch (RuntimeException e) {
            ResultSets.close(rs);
            throw e;
        }
    }


}
