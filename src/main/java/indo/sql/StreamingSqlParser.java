/*
 * Copyright 2015  Jonathan Cone
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

import indo.util.Strings;

/**
 * Created by jcone on 8/1/15.
 */
public class StreamingSqlParser extends AbstractSqlParser implements SqlParser {


    @Override
    public String parse(String sourceSql, Parameters parameters) {

        StringBuilder targetSql = new StringBuilder(sourceSql.length());

        for (int nextIndex = 1, c = 0; c < sourceSql.length(); c++) {

            boolean match = false;

            // We found what could appear to be a bind variable.
            if (isPrefixToken(sourceSql.charAt(c))) {

                for (Parameter sp : parameters) {

                    String name = normalizeParamName(sp);

                    if (sourceSql.substring(c).startsWith(name)) {
                    /*
                        We found match, now we need to determine how to bind it.
                     */
                        for (BindingResolver bindingResolver : getBindingResolvers()) {
                            String sqlFragment = bindingResolver.resolve(nextIndex, sp);

                            if (Strings.isNotBlank(sqlFragment)) {
                                nextIndex = sp.getMaxIndex() + 1;
                                targetSql.append(sqlFragment);

                                // Fast-forward the index
                                c += name.length();
                                break;
                            }
                        }
                        match = true;
                        break;
                    }
                }
            }

            if (!match) {
                targetSql.append(c);
            }

        }

        return targetSql.toString();
    }


}

