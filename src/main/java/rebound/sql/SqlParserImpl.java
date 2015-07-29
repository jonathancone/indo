package rebound.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SqlParserImpl implements SqlParser {

	private static final char DEFAULT_ESCAPE_TOKEN = '\'';
	private static final char DEFAULT_PREFIX_TOKEN = ':';
	private static final char DEFAULT_BIND_TOKEN = '?';

	private char prefixToken;
	private char escapeToken;

	public SqlParserImpl() {
		this(DEFAULT_PREFIX_TOKEN, DEFAULT_ESCAPE_TOKEN);
	}

	public SqlParserImpl(char prefixToken, char escapeToken) {
		this.prefixToken = prefixToken;
		this.escapeToken = escapeToken;
	}

	public ParsedStatement parse(String originalSql) {

		StringBuilder parsedSql = new StringBuilder();

		List<ParameterPart> parameters = new ArrayList<ParameterPart>();

		if (StringUtils.isNotBlank(originalSql)) {

			boolean escaped = false;

			char escape = getEscapeToken();
			char prefix = getPrefixToken();
			int length = originalSql.length();

			for (int index = 0; index < length; index++) {

				char current = originalSql.charAt(index);

				if (current == escape) {

					// Flip the escape mode
					escaped = !escaped;

				}

				if (current == prefix && !escaped) {

					/*
					 * Found a match for a bind variable, check to make sure the
					 * next character is a valid Java identifier starting
					 * character.
					 */

					int bindStart = index;
					int identifierStart = bindStart + 1;
					int identifierEnd = identifierStart + 1;
					StringBuilder identifier = new StringBuilder();

					if (identifierStart < length) {
						char char0 = originalSql.charAt(identifierStart);

						if (validStart(char0)) {
							identifier.append(char0);

							while (identifierEnd < length
									&& validPart(originalSql
											.charAt(identifierEnd))) {
								identifier.append(originalSql
										.charAt(identifierEnd));
								identifierEnd++;
							}

							// Fast forward the index
							index = identifierEnd - 1;

							parameters.add(new ParameterPart(bindStart,
									identifierStart, identifierEnd, identifier
											.toString()));

						}
					}
					current = DEFAULT_BIND_TOKEN;
				}

				parsedSql.append(current);
			}
		}
		return new ParsedStatement(originalSql, parsedSql.toString(),
				parameters);
	}

	protected boolean validStart(char character) {
		return Character.isJavaIdentifierStart(character);
	}

	protected boolean validPart(char character) {
		return Character.isJavaIdentifierPart(character);
	}

	protected char getPrefixToken() {
		return prefixToken;
	}

	protected char getEscapeToken() {
		return escapeToken;
	}
}