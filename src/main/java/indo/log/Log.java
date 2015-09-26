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

/**
 * Interface to provide logging capabilities without binding to a specific
 * implementation. The default methods included here can be used to log messages
 * to System.out.
 *
 * @author Jonathan Cone
 * @see Logger
 */
public interface Log {
    /**
     * A default Log implementation that logs everything to System.out.
     */
    Log DEFAULT = new Log() {
    };

    /**
     * Logs a debug message to System.out.
     *
     * @param instance An instance used to look up the appropriate logger,
     *                 usually you'll want to supply the 'this' keyword.
     * @param message  The message which should be logged, allows for %s, %d,
     *                 etc. style substitutions.
     * @param args     The arguments to be supplied to the formatting parameters
     *                 mentioned above.
     * @see String#format(String, Object...)
     */
    default void debug(Object instance, String message, Object... args) {
        System.out.printf("DEBUG: " + message + System.lineSeparator(), args);
    }

    /**
     * Logs an info message to System.out.
     *
     * @param instance An instance used to look up the appropriate logger,
     *                 usually you'll want to supply the 'this' keyword.
     * @param message  The message which should be logged, allows for %s, %d,
     *                 etc. style substitutions.
     * @param args     The arguments to be supplied to the formatting parameters
     *                 mentioned above.
     * @see String#format(String, Object...)
     */
    default void info(Object instance, String message, Object... args) {
        System.out.printf("INFO: " + message + System.lineSeparator(), args);
    }

    /**
     * Logs a warning message to System.out.
     *
     * @param instance An instance used to look up the appropriate logger,
     *                 usually you'll want to supply the 'this' keyword.
     * @param message  The message which should be logged, allows for %s, %d,
     *                 etc. style substitutions.
     * @param args     The arguments to be supplied to the formatting parameters
     *                 mentioned above.
     * @see String#format(String, Object...)
     */
    default void warn(Object instance, String message, Object... args) {
        System.out.printf("WARN: " + message + System.lineSeparator(), args);
    }

    /**
     * Logs an error message to System.out.
     *
     * @param instance An instance used to look up the appropriate logger,
     *                 usually you'll want to supply the 'this' keyword.
     * @param message  The message which should be logged, allows for %s, %d,
     *                 etc. style substitutions.
     * @param args     The arguments to be supplied to the formatting parameters
     *                 mentioned above.
     * @see String#format(String, Object...)
     */
    default void error(Object instance, String message, Object... args) {
        System.out.printf("ERROR: " + message + System.lineSeparator(), args);
    }

}
