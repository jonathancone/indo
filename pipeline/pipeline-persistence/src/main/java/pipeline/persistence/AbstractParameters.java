package pipeline.persistence;

import java.util.HashMap;
import java.util.Map;

public class AbstractParameters implements Parameters {

	private Map<String, Object> pairs;

	public void use(String parameter, Object value) {
		getPairs().put(parameter, value);
	}

	public void use(Map<String, Object> parameters) {
		getPairs().putAll(parameters);
	}

	public Map<String, Object> getPairs() {

		if (pairs == null) {
			this.pairs = new HashMap<String, Object>();
		}
		return pairs;
	}

	public void setPairs(Map<String, Object> pairs) {
		this.pairs = pairs;
	}

}
