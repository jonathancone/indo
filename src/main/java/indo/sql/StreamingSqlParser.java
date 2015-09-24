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

import indo.util.Lists;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by jcone on 8/1/15.
 */
public class StreamingSqlParser extends AbstractSqlParser implements SqlParser {

    @Override
    public SqlQueryMetaData parse(String sourceSql, Object... parameters) {
        return parse(sourceSql, Lists.fromArray(parameters));
    }

    @Override
    public SqlQueryMetaData parse(String sourceSql, List<?> parameters) {
        return parse(sourceSql, Parameters.fromList(parameters));
    }

    @Override
    public SqlQueryMetaData parse(String sourceSql, Map<String, ?> nameValues) {
        return parse(sourceSql, Parameters.fromMap(nameValues));
    }

    @Override
    public SqlQueryMetaData parse(String sourceSql, ParameterProvider parameterProvider) {

        StringBuilder targetSql = new StringBuilder(sourceSql.length());

        boolean singleQuoteClosed = true;
        boolean doubleQuoteClosed = true;

        // Start by looping through the SQL statement.
        for (int nextIndex = 1, seekIndex = 0; seekIndex < sourceSql.length(); seekIndex++) {

            boolean match = false;
            char currentChar = sourceSql.charAt(seekIndex);

            if (isSingleQuote(currentChar)) {
                singleQuoteClosed = !singleQuoteClosed;
            }

            if (isDoubleQuote(currentChar) && singleQuoteClosed) {
                doubleQuoteClosed = !doubleQuoteClosed;
            }

            if (singleQuoteClosed && doubleQuoteClosed) {

                // Perhaps the start of a named bind variable.
                if (isPrefixToken(currentChar)) {

                    // Scan until we don't find a valid Java identifier.
                    int identifierStart = seekIndex + 1;
                    int identifierEnd = identifierStart;

                    while (sourceSql.length() > identifierEnd
                            && Character.isJavaIdentifierPart(sourceSql.charAt(identifierEnd))) {
                        identifierEnd++;
                    }

                    // We found an identifier, now we need to determine if its actually valid.
                    String identifier = sourceSql.substring(identifierStart, identifierEnd);

                    Optional<Parameter> optionalParameter = parameterProvider.get(identifier);

                    if (optionalParameter.isPresent()) {

                        // We found a good match, now determine how to bind it.
                        Parameter parameter = optionalParameter.get();

                        for (BindingResolver bindingResolver : getBindingResolvers()) {
                            Optional<String> resolved = bindingResolver.resolve(nextIndex, parameter);

                            if (resolved.isPresent()) {

                                // This resolver can handle this parameter.
                                nextIndex = parameter.getMaxIndex() + 1;
                                targetSql.append(resolved.get());

                                // Fast-forward the index past the parameter name.
                                seekIndex += identifier.length();
                                match = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!match) {
                targetSql.append(currentChar);
            }

        }

        return new SqlQueryMetaData(sourceSql, targetSql.toString(), parameterProvider);
    }


}

