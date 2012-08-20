package pipeline.core.query;

import javax.sql.DataSource;

import org.unitils.UnitilsJUnit4;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

public class AbstractQueryTest<T> extends UnitilsJUnit4 {

	@TestedObject
	private SelectQuery<T> selectQuery;
	@TestedObject
	private InsertQuery<T> insertQuery;
	@TestedObject
	private UpdateQuery<T> updateQuery;
	@TestedObject
	private DeleteQuery<T> deleteQuery;

	@InjectIntoByType
	@TestDataSource
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected SelectQuery<T> getSelectQuery() {
		return selectQuery;
	}

	protected InsertQuery<T> getInsertQuery() {
		return insertQuery;
	}

	protected UpdateQuery<T> getUpdateQuery() {
		return updateQuery;
	}

	protected DeleteQuery<T> getDeleteQuery() {
		return deleteQuery;
	}

}
