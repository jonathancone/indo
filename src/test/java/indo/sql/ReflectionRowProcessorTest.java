package indo.sql;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link ReflectionRowProcessor}.
 *
 * @author Jonathan Cone
 */
public class ReflectionRowProcessorTest {
    public void testGetMappingStrategies() throws Exception {
        assertEquals(MappingStrategy.DEFAULTS, new ReflectionRowProcessor(Object.class).getMappingStrategies());
    }
}
