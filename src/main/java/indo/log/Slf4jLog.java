/*
 * Copyright 2015 Indo Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package indo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link Log} that makes use of SLF4J.
 *
 * @author Jonathan Cone
 */
public class Slf4jLog implements Log {

    /**
     * Creates a new instance of the logger, if possible.  This will only
     * succeed if an implementation of SLF4J can be found, otherwise the
     * exception below will be thrown.
     *
     * @throws ClassNotFoundException if the SLF4J API cannot be found on the
     *                                classpath.
     */
    public Slf4jLog() throws ClassNotFoundException {
        Class.forName("org.slf4j.LoggerFactory");
    }

    @Override
    public void debug(Object instance, String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(instance.getClass());
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(message, args));
        }
    }

    @Override
    public void info(Object instance, String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(instance.getClass());
        if (logger.isInfoEnabled()) {
            logger.info(String.format(message, args));
        }
    }

    @Override
    public void warn(Object instance, String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(instance.getClass());
        if (logger.isWarnEnabled()) {
            logger.warn(String.format(message, args));
        }
    }

    @Override
    public void error(Object instance, String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(instance.getClass());
        if (logger.isErrorEnabled()) {
            logger.error(String.format(message, args));
        }
    }
}
