package indo.sql;

import indo.util.Reflect;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * A MappingStrategy that requires an exact case-sensitive match between column and property names.
 *
 * @author Jonathan Cone
 */
public class ExactMappingStrategy<T> implements MappingStrategy<T> {

    @Override
    public boolean mapOnMatch(String column, Object value, T target) {
        Reflect<T> typeInstance = Reflect.on(target);

        Map<String, Field> fields = typeInstance.fields();

        boolean match = fields.containsKey(column);

        if (match) {
            // We found a match, nothing else to be done except for setting the property.
            typeInstance.property(column, value);
        }

        return match;
    }
}
