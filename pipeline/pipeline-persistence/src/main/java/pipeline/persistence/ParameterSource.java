package pipeline.persistence;

public interface ParameterSource<T> {
	T getParameters(String query);
}
