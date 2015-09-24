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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractSqlParser implements SqlParser {

    private static final char DEFAULT_ESCAPE_TOKEN = '\'';
    private static final char DEFAULT_PREFIX_TOKEN = ':';
    private static final char DEFAULT_BIND_TOKEN = '?';

    private char prefixToken;
    private char escapeToken;
    private char bindToken;

    private List<BindingResolver> bindingResolvers;

    public AbstractSqlParser() {
        this(DEFAULT_PREFIX_TOKEN, DEFAULT_ESCAPE_TOKEN, DEFAULT_BIND_TOKEN);
    }

    public AbstractSqlParser(char prefixToken, char escapeToken, char bindToken) {
        this.prefixToken = prefixToken;
        this.escapeToken = escapeToken;
        this.bindToken = bindToken;
        this.bindingResolvers = Arrays.asList(new StandardBindingResolver());
    }

    public List<BindingResolver> getBindingResolvers() {
        return Collections.unmodifiableList(bindingResolvers);
    }

    public char getBindToken() {
        return bindToken;
    }

    public char getEscapeToken() {
        return escapeToken;
    }

    public char getPrefixToken() {
        return prefixToken;
    }

    public boolean isPrefixToken(char token) {
        return prefixToken == token;
    }

    public boolean isBindToken(char token) {
        return bindToken == token;
    }

    public boolean isSingleQuote(char token) {
        return '\'' == token;
    }

    public boolean isDoubleQuote(char token) {
        return '"' == token;
    }


}
