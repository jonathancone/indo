/*
 * Copyright 2015  Jonathan Cone
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

package indo.util;

/**
 * Utilities to make working with checked {@link Exception}s less painful by converting them to
 * unchecked {@link RuntimeException}s.
 *
 * @author Jonathan Cone
 */
@SuppressWarnings({"unchecked", "ThrowableResultOfMethodCallIgnored"})
public class Unchecked {
    /**
     * This method wraps an unchecked {@link Exception} in a {@link RuntimeException} based on a supplied matching
     * candidate.  If the supplied {@link Exception} is an instance of any of the supplied matching
     * {@link RuntimeException} classes, then a new wrapper instance of the supplied type will be created to
     * wrap the {@link Exception}.  If there are no matching candidates, then a generic {@link RuntimeException}
     * will be created.
     * <p>
     * Note: This method does not accomplish anything that cannot be accomplished do with multiple catch blocks.
     * However, using this method may be less verbose in some circumstances.
     *
     * @param t        The exception instance to wrap.
     * @param wrapper  The type of exception the instance should be wrapped with if its one of the types supplied
     *                 as matching.
     * @param matching The candidate list of matching exceptions that will cause t to be wrapped with an instance
     *                 of wrapper.
     * @param <T>      A subclass of Exception
     * @param <E>      A subclass of Exception
     * @param <W>      A subclass of RuntimeException
     * @return The potentially wrapped RuntimeException.
     */
    public static <T extends Exception, E extends Exception, W extends RuntimeException> RuntimeException exception(T t, Class<W> wrapper, Class<E>... matching) {
        if (t instanceof RuntimeException) {
            return (W) t;
        } else {
            if (Multi.isNotEmpty(matching)) {
                for (Class<E> match : matching) {
                    if (match.isAssignableFrom(t.getClass())) {
                        return (W) (Reflect.on(wrapper).newInstanceNow()).initCause(t);
                    }
                }
            }
            return new RuntimeException(t);
        }
    }

    /**
     * Given an {@link Exception}, wrap it as a {@link RuntimeException} if needed, otherwise return the
     * original {@link Exception}.
     * <p>
     * Note: This method does not accomplish anything that cannot be accomplished do with multiple catch blocks.
     * However, using this method may be less verbose in some circumstances.
     *
     * @param t   The Exception instance to wrap.
     * @param <T> A subclass of Exception
     * @return A RuntimeException which wraps t, if required.
     */
    public static <T extends Exception> RuntimeException exception(T t) {
        return exception(t, RuntimeException.class, Exception.class);
    }

}
