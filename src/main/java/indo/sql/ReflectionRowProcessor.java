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


import indo.jdbc.JdbcException;
import indo.util.Reflect;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static indo.jdbc.ResultSetMetaDatas.getColumnCount;
import static indo.jdbc.ResultSetMetaDatas.getColumnName;
import static indo.jdbc.ResultSets.getMetaData;
import static indo.jdbc.ResultSets.getObject;
import static indo.log.Logger.debug;

/**
 * A {@link RowProcessor} implementation that uses reflection to map a row's columns to properties on a POJO
 * that adheres to JavaBeans conventions.
 *
 * @author Jonathan Cone
 * @see MappingStrategy
 */
public class ReflectionRowProcessor<T> implements RowProcessor<T> {

    private Class<T> targetType;

    public ReflectionRowProcessor(Class<T> targetType) {
        this.targetType = targetType;
    }

    /**
     * Subclasses may override this method to provide custom {@link MappingStrategy} instances.  The
     * {@link MappingStrategy}s will be matched against in the order they are present in the list.
     *
     * @return A {@link List} of {@link MappingStrategy} instances to attempt during column mapping.
     */
    protected List<MappingStrategy> getMappingStrategies() {
        return MappingStrategy.DEFAULTS;
    }

    /**
     * Subclasses may override this method to suppress exceptions from being thrown when no column to property match
     * is found in any of the configured {@link MappingStrategy} instances.
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
        IntStream.range(0, getColumnCount(rsm))
                .mapToObj(index -> getColumnName(rsm, index))
                .forEach(originalColumn -> {

                    // Stream through each strategy to attempt to find a matching column based on the
                    // strategy. If a match is found, map the value and return the property name
                    // it was mapped to immediately.
                    Optional<String> matchedField =
                            getMappingStrategies().stream()
                                    .map(s -> s.findMatch(originalColumn, getObject(rs, originalColumn), targetObject))
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .findFirst();

                    // Optionally throw an exception if no mapping could be found.
                    if (!matchedField.isPresent()) {
                        String message = String.format("Could not map column \"%s\" to a property on %s",
                                originalColumn,
                                targetObject.getClass());

                        if (isExceptionThrownWhenColumnHasNoMatch()) {
                            throw new JdbcException(message);
                        } else {
                            debug(this, message);
                        }
                    }
                });

        return targetObject;
    }
}
