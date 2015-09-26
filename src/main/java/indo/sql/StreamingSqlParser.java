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
 * @author Jonathan Cone
 */
public class StreamingSqlParser extends AbstractSqlParser implements SqlParser {
    public static final StreamingSqlParser INSTANCE = new StreamingSqlParser();

    private StreamingSqlParser() {
    }

    @Override
    public SqlQueryMetaData parse(String sql, SqlParameterProvider sqlParameterProvider) {

        StringBuilder targetSql = new StringBuilder(sql.length());

        boolean singleQuoteClosed = true;
        boolean doubleQuoteClosed = true;

        // Start by looping through the SQL statement.
        for (int nextIndex = 1, seekIndex = 0; seekIndex < sql.length(); seekIndex++) {

            boolean match = false;
            char currentChar = sql.charAt(seekIndex);

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

                    while (sql.length() > identifierEnd
                            && Character.isJavaIdentifierPart(sql.charAt(identifierEnd))) {
                        identifierEnd++;
                    }

                    // We found an identifier, now we need to determine if its actually valid.
                    String identifier = sql.substring(identifierStart, identifierEnd);

                    Optional<SqlParameter> optionalParameter = sqlParameterProvider.findParameter(identifier);

                    if (optionalParameter.isPresent()) {

                        // We found a good match, now determine how to bind it.
                        SqlParameter sqlParameter = optionalParameter.get();

                        for (BindingResolver bindingResolver : getBindingResolvers()) {
                            Optional<String> resolved = bindingResolver.resolve(nextIndex, sqlParameter);

                            // This resolver can handle this parameter.
                            if (resolved.isPresent()) {

                                // The resolver handled creating new indexes, so we need to bump the next index up.
                                nextIndex = sqlParameter.getMaxIndex() + 1;

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

        return new SqlQueryMetaData(targetSql.toString(), sqlParameterProvider);
    }


}

