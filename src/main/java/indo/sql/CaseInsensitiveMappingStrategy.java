package indo.sql;

import indo.util.Reflect;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

/**
 * A MappingStrategy that only requires that field names contain the same letters, regardless of letter
 * case.  If multiple fields on the target object would result in a match, the first field found will be
 * used.
 *
 * @author Jonathan Cone
 */
public class CaseInsensitiveMappingStrategy<T> implements MappingStrategy<T> {
    @Override
    public boolean mapOnMatch(String column, Object value, T target) {
        Reflect<T> typeInstance = Reflect.on(target);

        // Find the first field that matches.
        Optional<String> firstField = typeInstance.fieldNames()
                .stream()
                .filter(fieldName -> fieldName.toLowerCase().equals(column.toLowerCase()))
                .findFirst();

        boolean match = firstField.isPresent();

        if (match) {
            typeInstance.property(firstField.get(), value);
        }

        return match;
    }
}
