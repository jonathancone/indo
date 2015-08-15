/*
 * Copyright (c) 2015 Rebound Contributors
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

package rebound.sql;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSQLParser implements SQLParser {

    private static final char DEFAULT_ESCAPE_TOKEN = '\'';
    private static final char DEFAULT_PREFIX_TOKEN = ':';
    private static final char DEFAULT_BIND_TOKEN = '?';

    private char prefixToken;
    private char escapeToken;
    private char bindToken;

    private List<BindingResolver> bindingResolvers;

    public AbstractSQLParser() {
        this(DEFAULT_PREFIX_TOKEN, DEFAULT_ESCAPE_TOKEN, DEFAULT_BIND_TOKEN);
    }

    public AbstractSQLParser(char prefixToken, char escapeToken, char bindToken) {
        this.prefixToken = prefixToken;
        this.escapeToken = escapeToken;
        this.bindToken = bindToken;
        this.bindingResolvers = new ArrayList<>();
    }

    public List<BindingResolver> getBindingResolvers() {
        return new ArrayList<>(bindingResolvers);
    }

    public char getBindToken() {
        return bindToken;
    }

    public char getEscapeToken() {
        return escapeToken;
    }

    public boolean isPrefixToken(char token) {
        return prefixToken == token;
    }

    public char getPrefixToken() {
        return prefixToken;
    }

    protected String normalizeParamName(Parameter sp) {
        return getPrefixToken() + sp.getName();
    }

}
