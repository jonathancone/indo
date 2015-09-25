package indo.log;

import java.util.Optional;

/**
 * Simple utility to wrap an underlying logging implementation.
 *
 * @author Jonathan Cone
 */
public class Logs {
    private static Optional<Log> log;

    static {
        try {
            Class.forName("org.slf4j.LoggerFactory");
            log = Optional.of(new Slf4jLog());
        } catch (ClassNotFoundException e) {
            log = Optional.ofNullable(null);
        }
    }

    public static void debug(Object instance, String message, Object... parameters) {
        if (log.isPresent()) {
            log.get().debug(instance, message, parameters);
        }
    }

    public static void info(Object instance, String message, Object... parameters) {
        if (log.isPresent()) {
            log.get().info(instance, message, parameters);
        }
    }

    public static void warn(Object instance, String message, Object... parameters) {
        if (log.isPresent()) {
            log.get().warn(instance, message, parameters);
        }
    }

    public static void error(Object instance, String message, Object... parameters) {
        if (log.isPresent()) {
            log.get().error(instance, message, parameters);
        }
    }


    public static void setLog(Log log) {
        Logs.log = Optional.ofNullable(log);
    }
}
