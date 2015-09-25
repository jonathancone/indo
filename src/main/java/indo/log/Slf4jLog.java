package indo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link Log} that makes use of SLF4J.
 *
 * @author Jonathan Cone
 */
public class Slf4jLog implements Log {
    @Override
    public void debug(Object instance, String message, Object... parameters) {
        Logger logger = LoggerFactory.getLogger(instance.getClass());
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(message, parameters));
        }
    }

    @Override
    public void info(Object instance, String message, Object... parameters) {
        Logger logger = LoggerFactory.getLogger(instance.getClass());
        if (logger.isInfoEnabled()) {
            logger.info(String.format(message, parameters));
        }
    }

    @Override
    public void warn(Object instance, String message, Object... parameters) {
        Logger logger = LoggerFactory.getLogger(instance.getClass());
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(message, parameters));
        }
    }

    @Override
    public void error(Object instance, String message, Object... parameters) {
        Logger logger = LoggerFactory.getLogger(instance.getClass());
        if (logger.isErrorEnabled()) {
            logger.error(String.format(message, parameters));
        }
    }
}
