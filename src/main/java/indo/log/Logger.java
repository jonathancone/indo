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
 * Simple utility to wrap an underlying logging implementation.
 *
 * @author Jonathan Cone
 */
public class Logger {
    private static Log log;

    static {
        try {
            log = new Slf4jLog();
        } catch (ClassNotFoundException e) {
            log = Log.DEFAULT;
        }
    }

    public static void debug(Object instance, String message, Object... parameters) {
        log.debug(instance, message, parameters);
    }

    public static void info(Object instance, String message, Object... parameters) {
        log.info(instance, message, parameters);
    }

    public static void warn(Object instance, String message, Object... parameters) {
        log.warn(instance, message, parameters);
    }

    public static void error(Object instance, String message, Object... parameters) {
        log.error(instance, message, parameters);
    }

    public static void setLog(Log log) {
        Logger.log = log;
    }
}
