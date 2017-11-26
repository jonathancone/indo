/*
 * Copyright 2017 Indo Contributors
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


import indo.jdbc.JdbcException;
import indo.sql.mapping.ColumnMappingStrategy;
import indo.sql.mapping.InclusiveColumnMappingStrategy;
import indo.util.Reflect;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import static indo.jdbc.ResultSetMetaDatas.getColumnCount;
import static indo.jdbc.ResultSetMetaDatas.getColumnName;
import static indo.jdbc.ResultSets.getMetaData;
import static indo.log.Logger.debug;

/**
 * A {@link RowProcessor} implementation that uses reflection to map a row's
 * columns to properties on a POJO that adheres to JavaBeans conventions.
 *
 * @author Jonathan Cone
 * @see ColumnMappingStrategy
 */
public class ReflectionRowProcessor<T> implements RowProcessor<T> {

    private final Class<T> targetType;
    private final ResultTypes resultTypes;
    private final ColumnMappingStrategy columnMappingStrategy;

    public ReflectionRowProcessor(Class<T> targetType, ResultTypes resultTypes, ColumnMappingStrategy columnMappingStrategy) {
        this.targetType = targetType;
        this.resultTypes = resultTypes;
        this.columnMappingStrategy = columnMappingStrategy;
    }

    public ReflectionRowProcessor(Class<T> targetType, ResultTypes resultTypes) {
        this(targetType, resultTypes, new InclusiveColumnMappingStrategy());
    }

    public ReflectionRowProcessor(Class<T> targetType) {
        this(targetType, ResultTypes.empty());
    }

    /**
     * Subclasses may override this method to provide a custom {@link
     * ColumnMappingStrategy} instance.
     *
     * @return The {@link ColumnMappingStrategy} instance to use during column
     * mapping.
     */
    protected ColumnMappingStrategy getColumnMappingStrategy() {
        return columnMappingStrategy;
    }

    /**
     * Subclasses may override this method to suppress exceptions from being
     * thrown when no column to property match is found in any of the configured
     * {@link ColumnMappingStrategy} instances.
     *
     * @return this method returns true by default.
     */
    protected boolean isExceptionThrownWhenColumnHasNoMatch() {
        return true;
    }

    @Override
    public T map(ResultSet rs) {

        // Create a new instance of the target type.
        T targetObject = Reflect.on(targetType).newInstanceIfAbsent().getInstance();

        ResultSetMetaData rsm = getMetaData(rs);

        // Stream through each column to retrieve its name.
        IntStream.range(1, getColumnCount(rsm) + 1)
                .mapToObj(index -> getColumnName(rsm, index))
                .forEach(originalColumn -> {

                    // Resolve the column as a specific Java type, if one was
                    // specified, otherwise just map it as an object.
                    ResultType resultType = resultTypes.get(originalColumn).orElse(ResultType.OBJECT);

                    Object object = resultType.asType(rs, originalColumn);

                    // If a match is found, map the value and return the property name
                    // it was mapped to immediately.
                    Optional<String> matchedField =
                            columnMappingStrategy.findMatch(originalColumn, object, targetObject);

                    // Optionally throw an exception if no mapping could be found.
                    if (!matchedField.isPresent()) {
                        String message = String.format("Could not map [column: %s, type: %s, value: %s] to a property on %s using strategy: %s. Likely there is no setter method that takes the expected resultType.",
                                originalColumn,
                                Objects.isNull(object) ? "null" : object.getClass().getName(),
                                Objects.toString(object),
                                targetObject.getClass(),
                                columnMappingStrategy);

                        if (isExceptionThrownWhenColumnHasNoMatch()) {
                            throw new JdbcException(message);
                        }

                        debug(this, message);
                    }
                });

        return targetObject;
    }
}
