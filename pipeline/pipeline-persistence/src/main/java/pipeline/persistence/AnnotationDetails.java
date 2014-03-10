package pipeline.persistence;

import java.lang.annotation.Annotation;

public class AnnotationDetails<T extends Annotation> {
	private T annotation;
	private String path;

	public T getAnnotation() {
		return annotation;
	}

	public void setAnnotation(T annotation) {
		this.annotation = annotation;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
