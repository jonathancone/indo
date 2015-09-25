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

package indo.sql;

import java.util.Optional;

/**
 * An interface which exposes behavior for providing {@link SqlParameter} instances based on some criteria.
 *
 * @author Jonathan Cone
 * @see SqlParameter
 * @see SqlParameters
 */
public interface SqlParameterProvider extends Iterable<SqlParameter> {
    /**
     * Retreive the {@link SqlParameter} associated with the supplied name.
     *
     * @param name The name of the parameter to retrieve.
     * @return The {@link Optional} instance to retrieve.
     */
    Optional<SqlParameter> findParameter(String name);

    /**
     * Retreive the {@link SqlParameter} associated with the supplied index.
     *
     * @param index The index of the parameter to retrieve.
     * @return The {@link Optional} instance to retrieve.
     */
    Optional<SqlParameter> findParameter(Integer index);
}
