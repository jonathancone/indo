package pipeline.persistence;

import org.junit.Before;
import org.unitils.UnitilsJUnit4;

public abstract class AbstractPersistenceTest extends UnitilsJUnit4 {

	private QueryBuilderFactory queryBuilderFactory;

	@Before
	public void setUp() {
		queryBuilderFactory = createFactory();
	}

	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}

	protected abstract QueryBuilderFactory createFactory();

	protected QueryBuilder getQueryBuilder() {
		return getQueryBuilderFactory().getQueryBuilder();
	}

}
