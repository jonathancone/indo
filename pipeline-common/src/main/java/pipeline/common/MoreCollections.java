package pipeline.common;

import java.util.Collection;
import java.util.List;

/**
 * Utilities for working with collections. None of the methods in this class
 * should throw exceptions.
 * 
 * @author Jonathan Cone
 * 
 */
public class MoreCollections {
	public static <T> boolean isEmpty(Collection<T> collection) {
		return collection == null || collection.size() == 0;
	}

	public static <T> boolean isNotEmpty(Collection<T> collection) {
		return !isEmpty(collection);
	}

	public static <T> int size(Collection<T> collection) {
		return isNotEmpty(collection) ? collection.size() : 0;
	}

	public static <T> T get(List<T> list, int index) {
		if (isNotEmpty(list)) {
			if (index >= 0 && index < list.size()) {
				return list.get(index);
			}
		}
		return null;
	}
}
