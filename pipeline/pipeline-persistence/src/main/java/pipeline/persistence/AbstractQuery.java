package pipeline.persistence;

import java.util.List;
import java.util.Map;

public class AbstractQuery implements Query {

	protected String sql;
	protected BindParameterHolder parameters;
	protected SqlParser parser;

	public Query bind(String parameter, Object value) {
		getParameters().addParameter(parameter, value);
		return this;
	}

	public Query bind(Integer index, Object value) {
		getParameters().addParameter(index, value);
		return this;
	}

	public Query bind(String parameter, Object value, Integer sqlType) {
		getParameters().addParameter(parameter, value, sqlType);
		return this;
	}

	public Query bind(Integer index, Object value, Integer sqlType) {
		getParameters().addParameter(index, value, sqlType);
		return this;
	}

	public Query bindAll(Object object) {
		getParameters().addParameters(object);
		return this;
	}

	public Query bindAll(Map<String, Object> parameters) {
		getParameters().addParameters(parameters);
		return this;
	}

	public <T> T[] executeForArray(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> List<T> executeForList(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T executeForObject(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	public SqlParser getParser() {
		if (parser == null) {
			parser = new SqlParserImpl();
		}
		return parser;
	}

	protected BindParameterHolder getParameters() {
		if (parameters == null) {
			parameters = new BindParameterHolderImpl(parser);
		}
		return parameters;
	}

	protected void addParameter(BindParameter parameter) {
		getParameters().addParameter(parameter);
	}

}
