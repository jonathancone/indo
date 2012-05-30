package pipeline.common;

import java.lang.reflect.Field;

public class MoreReflection {

	public static Object get(Field field, Object object) {
		try {
			return field.get(object);
		} catch (Exception e) {
			return null;
		}
	}
}
