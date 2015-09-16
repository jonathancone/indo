package indo.sql;

import indo.util.Reflect;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by JCone on 9/16/2015.
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
