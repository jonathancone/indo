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


import indo.util.Collects;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Optional;

/**
 * A {@link BindingResolver} implementation that handles a single objects,
 * collections and arrays.
 */
public class CollectionBindingResolver implements BindingResolver {
    @Override
    public Optional<String> resolve(int nextIndex, SqlParameter sqlParameter) {

        // Bind a single parameter by default.
        int length = 1;

        Optional<Object> value = sqlParameter.value();

        if (value.isPresent()) {

            Object object = value.get();

            if (object.getClass().isArray()) {
                length = Array.getLength(object);
            }

            if (object instanceof Collection) {
                length = Collects.size((Collection) object);
            }

        }
        sqlParameter.addIndexes(nextIndex, length);

        return Optional.ofNullable(Binder.repeatPlaceholders(length));
    }
}
