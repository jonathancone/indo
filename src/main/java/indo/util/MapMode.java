package indo.util;

/**
 * An enumeration of the modes that columns can be matched upon.  This allows
 * for customization of things such as mapping columns with underscores or
 * mixed case identifiers to POJO properties on result objects.
 *
 * @author Jonathan Cone
 */
public enum MapMode {
    /**
     * Attempt to convert column names that contain underscores to the closest matching property.
     */
    CONVERT_UNDERSCORE,
    /**
     * Attempt to convert columns to the closest matching property regardless of case.
     */
    CONVERT_CASE
}
