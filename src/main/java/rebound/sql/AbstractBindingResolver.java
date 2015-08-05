package rebound.sql;

/**
 * Created by jcone on 8/3/15.
 */
public abstract class AbstractBindingResolver implements BindingResolver {
    public static final String DEFAULT_BINDING_PLACEHOLDER = "?";
    public static final String DEFAULT_BINDING_DELIMITER = ",";

    public String getBindingPlaceholder() {
        return DEFAULT_BINDING_PLACEHOLDER;
    }

    public String getBindingDelimiter() {
        return DEFAULT_BINDING_DELIMITER;
    }

    protected String generateBindingPlaceholders(int length) {
        String sql = "";

        for (int i = 0; i < length; i++) {
            sql += getBindingPlaceholder();

            if (i != (length - 1)) {
                sql += getBindingDelimiter();
            }
        }

        return sql;
    }
}
