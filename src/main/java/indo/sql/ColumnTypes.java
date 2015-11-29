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

import indo.util.Maps;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * This class can be used when the JDBC driver supports returning an object as a
 * different data type that the default type the driver supports.  By using an
 * instance of {@link Type}, you can specify a specific column and the type you
 * would like the driver to return it as.
 * <p>
 * <pre>
 *   // Create an instance with multiple columns and their types.
 * {@code
 * ColumnTypes.withType("employee_id", Type.INTEGER)
 *            .andType("start_date", Type.DATE)
 *            .andType("salary", Type.BIG_DECIMAL);
 * }
 * </pre>
 *
 * @author Jonathan Cone
 * @see Type
 */
public class ColumnTypes {

    // The immutable empty instance.
    private static final ColumnTypes EMPTY = new ColumnTypes() {
        @Override
        public ColumnTypes andType(String columnName, Type type) {
            throw new IllegalStateException("You cannot override column types on the EMPTY instance.");
        }
    };

    private Map<String, Type> types;

    private ColumnTypes() {
    }

    /**
     * @return An empty instance which cannot contain any type mappings.
     */
    public static ColumnTypes empty() {
        return EMPTY;
    }

    /**
     * Create a new instance with the first specified type mapping.
     *
     * @param columnName The name of the column to map.
     * @param type       The type that the column should be mapped to.
     * @return A newly constructed mutable instance.
     */
    public static ColumnTypes withType(String columnName, Type type) {
        Objects.requireNonNull(columnName);
        Objects.requireNonNull(type);

        ColumnTypes columnTypes = new ColumnTypes();
        columnTypes.andType(columnName, type);
        return columnTypes;
    }

    /**
     * Add another type mapping to this instance.
     *
     * @param columnName The name of the column to map.
     * @param type       The type that the column should be mapped to.
     * @return The {@code this} instance for chaining.
     */
    public ColumnTypes andType(String columnName, Type type) {
        Objects.requireNonNull(columnName);
        Objects.requireNonNull(type);

        if (types == null) {
            types = Maps.newHashMap();
        }
        types.put(columnName, type);

        return this;
    }

    /**
     * Retrieve an {@link Optional} type mapping for a specific column.
     *
     * @param columnName The name of the column.
     * @return The {@link Optional} instance.
     */
    public Optional<Type> get(String columnName) {
        // Short circuit to prevent heap allocation of a new map when no
        // types have been supplied.
        return types == null || columnName == null ? Optional.empty() : Optional.ofNullable(types.get(columnName));
    }
}
