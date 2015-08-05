package rebound.sql;

/**
 * Created by jcone on 8/4/15.
 */
public class Strings {
    public static boolean isBlank(String s) {
        return s == null || s.trim().equals("");
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }
}
