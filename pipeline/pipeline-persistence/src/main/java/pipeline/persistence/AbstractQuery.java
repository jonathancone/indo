package pipeline.persistence;

import java.util.List;
import java.util.Map;

import org.joor.Reflect;

public class AbstractQuery<T> implements Query<T> {
	protected T root;

	protected String query;

	protected Parameters parameters;

	public AbstractQuery(T root) {
		this.root = root;
	}

	public AbstractQuery(Class<T> type) {
		this.root =
				Reflect
						.on(type)
						.create()
						.get();
	}

	public Query<T> query(String query) {
		this.query = query;
		return this;
	}

	public Query<T> using(Object object) {
		return this;
	}

	public Query<T> using(Integer parameter, Object value) {
		return this;
	}

	public Query<T> using(String parameter, Object value) {
		getParameters().use(parameter, value);
		return this;
	}

	public Query<T> using(Map<String, Object> parameters) {
		getParameters().use(parameters);
		return this;
	}

	public List<T> asList() {
		return null;
	}

	public T[] asArray() {
		return null;
	}

	public T asObject() {
		return null;
	}

	public T getRoot() {
		return root;
	}

	public String getQuery() {
		return query;
	}

	protected Parameters getParameters() {
		return parameters;
	}
}
