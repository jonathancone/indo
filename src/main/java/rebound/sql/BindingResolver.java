package rebound.sql;

/**
 * Created by jcone on 8/2/15.
 */
public interface BindingResolver {

    String resolve(int nextIndex, SqlParameter sqlParameter);

}
