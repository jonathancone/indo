package pipeline.persistence;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SqlParserImpl implements SqlParser {

	private static final char ESCAPE = '\'';
	private static final char ORDINAL_PARAM_TOKEN = '?';
	private static final char NAMED_PARAM_TOKEN = ':';

	public List<ParameterPart> findParameterParts(String sql) {

		List<ParameterPart> parts = new LinkedList<ParameterPart>();

		if (StringUtils.isNotBlank(sql)) {

			int length = sql.length();

			boolean escaped = false;

			for (int i = 0; i < length; i++) {

				char current = sql.charAt(i);

				switch (current) {
				case ESCAPE:
					escaped = !escaped;
					break;
				case ORDINAL_PARAM_TOKEN:

					if (!escaped) {

					}

					break;

				case NAMED_PARAM_TOKEN:
					break;
				}

			}

		}

		return parts;

	}
}
