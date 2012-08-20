package pipeline.persistence;

import java.sql.Connection;

import javax.sql.DataSource;

import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;

import pipeline.persistence.jdbc.DataSourceUtils;

public class BasePersistenceTest extends UnitilsJUnit4 {

	@TestDataSource
	private DataSource dataSource;

	protected DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected Connection getConnection() {
		return DataSourceUtils.getConnection(getDataSource());
	}

}
