package pipeline.persistence;

import java.util.List;
import java.util.Map;

public class AbstractQuery<T> implements Query<T> {
	private String query;
	private T root;

	public AbstractQuery(String query, T root) {
		this.query = query;
		this.root = root;
	}

	public String getQuery() {
		return query;
	}

	public T getQueryRoot() {
		return root;
	}

	public List<T> list() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setParameter(String name, byte value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, boolean value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, char value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, short value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, int value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, long value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, float value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, double value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, Byte value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, Boolean value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, Character value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, Short value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, Integer value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, Long value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, Float value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, Double value) {
		// TODO Auto-generated method stub

	}

	public void setParameter(String name, Object o) {
		// TODO Auto-generated method stub

	}

	public void setParameterSource(Object source) {
		// TODO Auto-generated method stub

	}

	public void setParameterSource(Map<?, ?> source) {
		// TODO Auto-generated method stub

	}

}
