package pipeline.persistence;


public class DatabaseSmokeTest extends BasePersistenceTest {

	// @Test
	// public void testConnect() {
	// Connection connection = getConnection();
	//
	// assertNotNull("The test suite should have a connection.", connection);
	//
	// PreparedStatement ps = null;
	// ResultSet rs = null;
	// try {
	//
	// String queryA = "SELECT                                 "
	// + "        t.table_catalog,                     "
	// + "        t.table_schema,                      "
	// + "        t.table_name,                        "
	// + "        t.table_type,                        "
	// + "        c.column_name                        "
	// + "      FROM                                   "
	// + "        information_schema.tables t          "
	// + "      INNER JOIN                             "
	// + "        information_schema.columns c         "
	// + "      ON                                     "
	// + "        t.table_catalog = c.table_catalog    "
	// + "        AND t.table_schema = c.table_schema  "
	// + "        AND t.table_name = c.table_name      "
	// + "      WHERE                                  "
	// + "        t.table_type = 'VIEW'                "
	// + "        AND c.data_type = 'character varying'";
	//
	// String queryB =
	// "SELECT  t.table_catalog,  t.table_schema,  t.table_name,  t.table_type,  c.column_nameFROM  information_schema.tables tINNER JOIN  information_schema.columns cON  t.table_catalog = c.table_catalog  AND t.table_schema = c.table_schema  AND t.table_name = c.table_nameWHERE  t.table_type = 'VIEW'  AND c.data_type = 'character varying'";
	//
	// ps = connection.prepareStatement(queryB);
	// rs.getM
	//
	// ps.setString(1, "pg_catalog");
	//
	// rs = ps.executeQuery();
	//
	//
	//
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// } finally {
	// if (rs != null) {
	// try {
	// rs.close();
	// } catch (SQLException e) {
	// }
	// }
	// if (ps != null) {
	// try {
	// ps.close();
	// } catch (SQLException e) {
	// }
	// }
	// }
	//
	// }
}
