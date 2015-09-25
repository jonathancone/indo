package indo.log;

/**
 * Interface to provide logging capabilities without binding to a specific implementation.
 *
 * @author Jonathan Cone
 */
public interface Log {
    default void debug(Object instance, String message, Object... parameters) {
        System.out.printf("DEBUG: " + message + System.lineSeparator(), parameters);
    }

    default void info(Object instance, String message, Object... parameters) {
        System.out.printf("INFO: " + message + System.lineSeparator(), parameters);
    }

    default void warn(Object instance, String message, Object... parameters) {
        System.out.printf("WARN: " + message + System.lineSeparator(), parameters);
    }

    default void error(Object instance, String message, Object... parameters) {
        System.out.printf("ERROR: " + message + System.lineSeparator(), parameters);
    }

}
