package pipeline.persistence.jdbc;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SqlFormatterTest {
	@Test
	public void testNaiveSqlStatement() throws Exception {
		String sql = "SELECT * FROM table WHERE table.table_id = 1";
		new SqlFormatter(sql).format();

	}

	protected static class SqlFormatter {

		private Map<String, String> formats;

		private String sql;
		private StringBuilder output;

		public SqlFormatter(String sql) {
			this.sql = sql;
			this.output = new StringBuilder();

		}

		public String format() {

			StringBuilder buff = new StringBuilder();

			for (int i = 0; i < sql.length(); i++) {
				if (sql.charAt(i) == ' ') {
					output.append(getFormats().get(buff));
					buff.clear();
				} else {

				}
			}
		}

		public Map<String, String> getFormats() {
			if (formats == null) {
				formats = new HashMap<String, String>();
				formats.put("SELECT", "\nSELECT\n");
				formats.put("FROM", "\nFROM\n");
				formats.put("WHERE", "\nWHERE\n");
				formats.put("ORDER BY", "\nORDER BY\n");
				formats.put("GROUP BY", "\nGROUP BY\n");
				formats.put("ASC", "\nASC\n");
				formats.put("DESC", "\nDESC\n");
			}
			return formats;
		}
	}

}
