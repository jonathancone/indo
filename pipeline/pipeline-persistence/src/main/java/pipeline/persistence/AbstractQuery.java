package pipeline.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joor.Reflect;

public class AbstractQuery<T> implements Query<T> {
	protected T root;

	protected String query;

	protected Map<String, Object> parameters;

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

	public Query<T> using(String parameter, Object value) {
		getParameters().put(parameter, value);
		return this;
	}

	public Query<T> using(Map<String, Object> parameters) {
		getParameters().putAll(parameters);
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

	public Map<String, Object> getParameters() {
		if (parameters == null) {
			parameters = new HashMap<String, Object>();
		}
		return parameters;
	}

}
