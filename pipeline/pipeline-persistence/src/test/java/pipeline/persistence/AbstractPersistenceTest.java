package pipeline.persistence;

import org.junit.Before;
import org.unitils.UnitilsJUnit4;

public abstract class AbstractPersistenceTest extends UnitilsJUnit4 {
	private QueryFactory queryFactory;

	@Before
	public void setUp() {
		this.queryFactory = createFactory();
	}

	protected abstract QueryFactory createFactory();

	protected QueryFactory getQueryFactory() {
		return queryFactory;
	}

}
