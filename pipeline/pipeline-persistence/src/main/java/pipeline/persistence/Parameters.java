package pipeline.persistence;

import java.util.Map;

public interface Parameters {

	void use(String parameter, Object value);

	void use(Map<String, Object> parameters);
}
