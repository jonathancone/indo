/*
 * Copyright (c) 2015 Rebound Contributors
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

package rebound.util;

/**
 * A helper utility to handle checked exceptions.
 *
 * @author Jonathan Cone
 */
@SuppressWarnings({"unchecked", "ThrowableResultOfMethodCallIgnored"})
public class Unchecked {
    public static <T extends Exception, E extends Exception, W extends RuntimeException> W exception(T t, Class<E> matching, Class<W> wrapper) {
        if (t instanceof RuntimeException) {
            return (W) t;
        } else {
            if (matching.isAssignableFrom(t.getClass())) {
                return (W) Reflect.on(wrapper).newInstance().initCause(t);
            }
            return (W) new RuntimeException(t);
        }
    }

    public static <T extends Exception> RuntimeException exception(T t) {
        return exception(t, Exception.class, RuntimeException.class);
    }

}
