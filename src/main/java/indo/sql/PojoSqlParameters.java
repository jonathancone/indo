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
 * This class provides the capability to create a {@link SqlParameterProvider} from a regular POJO.  This will match
 * parameter names with the names of JavaBean style "getter" methods on the target instance.
 *
 * @author Jonathan Cone
 */
public class PojoSqlParameters<T> implements SqlParameterProvider {
    private Reflect<T> reflect;
    private Map<String, SqlParameter> parameters;


    /**
     * Package-private constructor.
     *
     * @param instance The object instance to retrieve the parameters from using reflection/
     */
    PojoSqlParameters(T instance) {
        this.reflect = Reflect.on(instance);
        this.parameters = new HashMap<>();
    }

    /**
     * Create a new instance of this class backed by the supplied object.
     *
     * @param t   The target object used to back the {@link SqlParameterProvider}.
     * @param <T> The target object type.
     * @return The newly created instance.
     */
    public static <T> PojoSqlParameters<T> fromPojo(T t) {
        return new PojoSqlParameters<>(t);
    }

    /**
     * Uses reflection to look up a {@link SqlParameter} on the JavaBean that this {@link SqlParameterProvider}
     * is based off of.  Example:
     * <pre>
     * class Employee {
     *     private BigDecimal salary;
     *
     *     // Other fields and methods
     *     // ...
     *
     *     public BigDecimal getSalary() {
     *      return salary;
     *     }
     * }
     *
     * Employee employee = new Employee();
     * employee.setSalary(...);
     *
     * // The following will return a SqlParameter with the value retrieved from employee.getSalary():
     *
     * Optional<SqlParameter> parameter = PojoSqlParameters.fromPojo(employee).findParameter("salary");
     * </pre>
     *
     * @param name The name of the {@link SqlParameter} to retrieve.
     * @return The Optional SqlParameter associated with the supplied name.
     */
    @Override
    public Optional<SqlParameter> findParameter(String name) {
        SqlParameter parameter = null;

        if (parameters.containsKey(name)) {
            // We already handled this one, so we've cached it.
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

    /**
     * @throws UnsupportedOperationException This method is not supported.
     */
    @Override
    public Optional<SqlParameter> findParameter(Integer index) {
        throw new UnsupportedOperationException("Method not supported for POJO parameters.");
    }

    @Override
    public Iterator<SqlParameter> iterator() {
        return parameters.values().iterator();
    }
}
