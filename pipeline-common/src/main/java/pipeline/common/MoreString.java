package pipeline.common;

import java.util.Arrays;
import java.util.List;

public class MoreString {

	public static String repeat(String s, int times) {
		return times > 0 ? new String(new char[times]).replace("\0", s) : "";
	}

	public static boolean isEmpty(String s) {
		return s == null || s.equals("");
	}

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	public static String[] asStrings(Object... objects) {
		if (MoreArrays.isEmpty(objects)) {
			return new String[] {};
		}

		String[] strings = new String[objects.length];

		for (int i = 0; i < objects.length; i++) {
			strings[i] = toString(objects[i]);
		}

		return strings;
	}

	public static List<String> asStringList(Object... objects) {
		return Arrays.asList(asStrings(objects));
	}

	public static String toString(Object o) {
		return o == null ? "" : o.toString();
	}

}
