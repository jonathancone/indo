package pipeline.persistence;

import java.util.List;
import java.util.Map;

public interface Query<T> {
	T getQueryRoot();

	List<T> list();

	void setParameter(String name, byte value);

	void setParameter(String name, boolean value);

	void setParameter(String name, char value);

	void setParameter(String name, short value);

	void setParameter(String name, int value);

	void setParameter(String name, long value);

	void setParameter(String name, float value);

	void setParameter(String name, double value);

	void setParameter(String name, Byte value);

	void setParameter(String name, Boolean value);

	void setParameter(String name, Character value);

	void setParameter(String name, Short value);

	void setParameter(String name, Integer value);

	void setParameter(String name, Long value);

	void setParameter(String name, Float value);

	void setParameter(String name, Double value);

	void setParameter(String name, Object o);

	void setParameterSource(Object source);

	void setParameterSource(Map<?, ?> source);

}
