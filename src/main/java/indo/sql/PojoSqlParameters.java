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

import indo.util.Reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jcone on 9/24/15.
 */
public class PojoSqlParameters<T> implements SqlParameterProvider {
    private Reflect<T> reflect;
    private Map<String, SqlParameter> parameters;


    public PojoSqlParameters(T instance) {
        this.reflect = Reflect.on(instance);
        this.parameters = new HashMap<>();
    }

    public static <T> PojoSqlParameters<T> fromPojo(T t) {
        return new PojoSqlParameters<>(t);
    }

    @Override
    public Optional<SqlParameter> findParameter(String name) {
        SqlParameter parameter = null;

        if (parameters.containsKey(name)) {
            // We already handled this one, so we'e cached it.
            parameter = parameters.get(name);
        } else {
            Optional<Method> getter = reflect.findGetter(name);

            if (getter.isPresent()) {

                Optional<Object> property = reflect.property(name);

                if (property.isPresent()) {
                    parameter = new SqlParameter(name, property.get());
                }
            }
        }

        return Optional.ofNullable(parameter);
    }

    @Override
    public Optional<SqlParameter> findParameter(Integer index) {
        throw new UnsupportedOperationException("Method not supported for POJO parameters.");
    }

    @Override
    public Iterator<SqlParameter> iterator() {
        return parameters.values().iterator();
    }
}
