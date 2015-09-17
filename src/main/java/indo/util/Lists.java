package indo.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by JCone on 9/17/2015.
 */
public class Lists {
    public static <T> List<T> fromArray(Class<T> type, T[] t) {
        return Arrays.asList(Optional.ofNullable(t).orElse((T[]) Array.newInstance(type, 0)));
    }

    public static List<?> fromArray(Object[] t) {
        return fromArray(Object.class, t);
    }
}
