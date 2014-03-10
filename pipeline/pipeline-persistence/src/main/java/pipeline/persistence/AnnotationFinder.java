package pipeline.persistence;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Utilities for finding annotations.
 * 
 * @author Jonathan Cone
 */
public class AnnotationFinder {
	/**
	 * Given a class and method, determine the path to the supplied annotation
	 * (if any) and return some details about it including the annotation itself
	 * and the path it was found at.
	 * 
	 * @param target
	 *            The target class to search, not null.
	 * @param method
	 *            The method to use in the search, not null.
	 * @param annotation
	 *            The annotation to check for, not null.
	 * @return Some {@link AnnotationDetails} about the annotation including the
	 *         path it was located at.
	 */
	public static <T extends Annotation> AnnotationDetails<T> findAnnotation(
			Class<?> target, Method method, Class<T> annotation) {

		AnnotationDetails<T> details = new AnnotationDetails<T>();

		if (!method.isAccessible()) {
			method.setAccessible(true);
		}

		T methodAnnotation = method.getAnnotation(annotation);

		if (methodAnnotation != null) {
			details.setPath(target.getName() + "." + method.getName());
			details.setAnnotation((T) methodAnnotation);
			return details;

		}
		else {
			T classAnnotation = target.getAnnotation(annotation);

			if (classAnnotation != null) {
				details.setPath(target.getName());
				details.setAnnotation((T) classAnnotation);
			}
		}

		return details;

	}
}
