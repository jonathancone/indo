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
    public QueryMetaData parse(String sourceSql, Object... parameters) {
        return parse(sourceSql, Lists.fromArray(parameters));
    }

    @Override
    public QueryMetaData parse(String sourceSql, List<?> parameters) {
        return parse(sourceSql, Parameters.fromList(parameters));
    }

    @Override
    public QueryMetaData parse(String sourceSql, Map<String, ?> nameValues) {
        return parse(sourceSql, Parameters.fromMap(nameValues));
    }

    @Override
    public QueryMetaData parse(String sourceSql, Parameters parameters) {

        QueryMetaData metaData = new QueryMetaData();

        StringBuilder targetSql = new StringBuilder(sourceSql.length());

        for (int nextIndex = 1, c = 0; c < sourceSql.length(); c++) {

            boolean match = false;

            // We found what could appear to be a bind variable.
            if (isPrefixToken(sourceSql.charAt(c))) {

                for (Parameter parameter : parameters) {

                    String tokenParamName = tokenizeParamName(parameter);

                    if (sourceSql.substring(c).startsWith(tokenParamName)) {
                        // We found match, now we need to determine how to bind it.
                        for (BindingResolver bindingResolver : getBindingResolvers()) {
                            Optional<String> resolved = bindingResolver.resolve(nextIndex, parameter);

                            if (resolved.isPresent()) {
                                nextIndex = parameter.getMaxIndex() + 1;
                                targetSql.append(resolved.get());

                                // Fast-forward the index past the parameter name.
                                c += tokenParamName.length();
                                match = true;
                                break;
                            }
                        }
                    }
                }
            }

            if (!match) {
                targetSql.append(c);
            }

        }

        return metaData;
    }


}

