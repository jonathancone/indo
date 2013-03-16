package pipeline.persistence;

import java.util.List;
import java.util.Map;

public interface BindParameterHolder {
	void addParameter(BindParameter parameter);

	void addParameter(String parameter, Object value);

	void addParameter(Integer index, Object value);

	void addParameter(String parameter, Object value, Integer sqlType);

	void addParameter(Integer index, Object value, Integer sqlType);

	void addParameters(Object object);

	void addParameters(Map<String, Object> parameters);

	List<BindParameter> getParameters();
}
