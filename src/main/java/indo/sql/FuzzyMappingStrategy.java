package indo.sql;

import indo.util.Reflect;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created by JCone on 9/16/2015.
 */
public class FuzzyMappingStrategy<T> implements MappingStrategy<T> {
    @Override
    public boolean mapOnMatch(String column, Object value, T target) {
        Reflect<T> typeInstance = Reflect.on(target);

        String javaSafeColumn = column.chars().filter(Character::isJavaIdentifierPart).toString();

        // Find the first field that matches.
        Optional<String> firstField = typeInstance.fieldNames()
                .stream()
                .filter(fieldName -> fieldName.toLowerCase().equals(javaSafeColumn.toLowerCase()))
                .findFirst();

        boolean match = firstField.isPresent();

        if (match) {
            typeInstance.property(firstField.get(), value);
        }

        return match;
    }
}
