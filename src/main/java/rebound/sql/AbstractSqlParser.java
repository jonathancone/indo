package rebound.sql;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jcone on 8/1/15.
 */
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
        this.bindingResolvers = new ArrayList<>();
    }

    @Override
    public ParsedStatement parse(String sourceSql, SqlParameters sqlParameters) {
        return new ParsedStatement(sourceSql, resolveCallType(sourceSql), parseInternal(sourceSql, sqlParameters), sqlParameters);
    }

    protected abstract String parseInternal(String sourceSql, SqlParameters sqlParameters);

    public List<BindingResolver> getBindingResolvers() {
        return ListUtils.unmodifiableList(bindingResolvers);
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

    protected String normalizeParamName(SqlParameter sp) {
        return getPrefixToken() + sp.getName();
    }

    protected boolean validStart(char character) {
        return Character.isJavaIdentifierStart(character);
    }

    protected boolean validPart(char character) {
        return Character.isJavaIdentifierPart(character);
    }

    protected JdbcCallType resolveCallType(String sourceSql) {

        String stripped = StringUtils.strip(sourceSql, " ");

        if (stripped.startsWith("{?=call")) {
            return JdbcCallType.FUNCTION;
        } else if (stripped.startsWith("{call")) {
            return JdbcCallType.PROCEDURE;
        } else {
            return JdbcCallType.STATEMENT;
        }
    }

}
