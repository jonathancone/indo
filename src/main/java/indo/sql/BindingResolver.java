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
 * A {@link BindingResolver} is responsible for generating bind variables in a SQL statement for a specific parameter.
 * How these are generated might vary based on the Java object being bound, so this interface can be used to
 * implement a specific strategy depending on the object.
 *
 * @author Jonathan Cone
 * @see StandardBindingResolver
 */
@FunctionalInterface
public interface BindingResolver {
    /**
     * Method called to resolve binding a parameter to a parameter.
     *
     * @param nextIndex    The next index available for binding.
     * @param sqlParameter The parameter that we're attempting to bind a value to.
     * @return The {@link Optional} SQL String that resulted from the binding resolution.
     */
    Optional<String> resolve(int nextIndex, SqlParameter sqlParameter);
}
