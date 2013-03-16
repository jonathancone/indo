package pipeline.persistence;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SqlParserImpl implements SqlParser {

	private static final char DEFAULT_ESCAPE_TOKEN = '\'';
	private static final char DEFAULT_PREFIX_TOKEN = ':';

	private char prefixToken;
	private char escapeToken;

	public SqlParserImpl() {
		this(DEFAULT_PREFIX_TOKEN, DEFAULT_ESCAPE_TOKEN);
	}

	public SqlParserImpl(char prefixToken, char escapeToken) {
		this.prefixToken = prefixToken;
		this.escapeToken = escapeToken;
	}

	public ParsedStatement parse(String sql) {

		List<ParameterPart> parameters = new LinkedList<ParameterPart>();

		if (StringUtils.isNotBlank(sql)) {

			boolean escaped = false;

			char escape = getEscapeToken();
			char prefix = getPrefixToken();
			int length = sql.length();

			for (int index = 0; index < length; index++) {

				char current = sql.charAt(index);

				if (current == escape) {

					// Flip the escape mode
					escaped = !escaped;

				} else if (current == prefix && !escaped) {

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
						char char0 = sql.charAt(identifierStart);

						if (validStart(char0)) {
							identifier.append(char0);

							while (identifierEnd < length
									&& validPart(sql.charAt(identifierEnd))) {
								identifier.append(sql.charAt(identifierEnd));
								identifierEnd++;
							}

							parameters.add(
									new ParameterPart(
											bindStart,
											identifierStart,
											identifierEnd,
											identifier.toString()));

							// Fast forward the index
							index = identifierEnd;
						}
					}
				}
			}
		}
		return new ParsedStatementImpl(sql, parameters);
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
