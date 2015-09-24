package indo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for working with {@link Map} instances.
 *
 * @author Jonathan Cone
 */
public class Maps {

    public static <S, T> Map<S, T> newHashMap() {
        return new HashMap<>();
    }

    public static <S, T> Map<S, T> newHashMap(S s1, T t1) {
        HashMap<S, T> map = new HashMap<>();
        map.put(s1, t1);
        return map;
    }

    public static <S, T> Map<S, T> newHashMap(S s1, T t1, S s2, T t2) {
        Map<S, T> map = newHashMap(s1, t1);
        map.put(s2, t2);
        return map;
    }

    public static <S, T> Map<S, T> newHashMap(S s1, T t1, S s2, T t2, S s3, T t3) {
        Map<S, T> map = newHashMap(s1, t1, s2, t2);
        map.put(s3, t3);
        return map;
    }

    public static <S, T> Map<S, T> newHashMap(S s1, T t1, S s2, T t2, S s3, T t3, S s4, T t4) {
        Map<S, T> map = newHashMap(s1, t1, s2, t2, s3, t3);
        map.put(s4, t4);
        return map;
    }

    public static <S, T> Map<S, T> newHashMap(S s1, T t1, S s2, T t2, S s3, T t3, S s4, T t4, S s5, T t5) {
        Map<S, T> map = newHashMap(s1, t1, s2, t2, s3, t3, s4, t4);
        map.put(s5, t5);
        return map;
    }

}
