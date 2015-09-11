package indo.util;

import java.util.function.Consumer;

/**
 * Created by jcone on 9/11/2015.
 */
public interface CheckedConsumer<T> extends Consumer<T> {

    @Override
    default void accept(T t) {
        try {
            acceptThrows(t);
        } catch (Exception e) {
            throw Unchecked.exception(e);
        }
    }

    void acceptThrows(T t) throws Exception;

}
