package indo.util;

import java.util.function.IntConsumer;

/**
 * Created by jcone on 9/11/2015.
 */
public interface CheckedIntConsumer extends IntConsumer {
    @Override
    default void accept(int i) {
        try {
            acceptThrows(i);
        } catch (Exception e) {
            throw Unchecked.exception(e);
        }
    }

    void acceptThrows(int i) throws Exception;
}
