package pipeline.common;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;

public class MoreString {

	public static String[] asStrings(Object... objects) {
		if (ArrayUtils.isEmpty(objects)) {
			return new String[] {};
		}

		String[] strings = new String[objects.length];

		for (int i = 0; i < objects.length; i++) {
			strings[i] = ObjectUtils.toString(objects[i]);
		}

		return strings;
	}

	public static List<String> asStringList(Object... objects) {
		return Arrays.asList(asStrings(objects));
	}

}
