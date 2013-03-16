package pipeline.persistence;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;

public class BindParameterHolderImpl implements BindParameterHolder {
	private List<BindParameter> parameters;
	private SqlParser sqlParser;

	public BindParameterHolderImpl(SqlParser sqlParser) {
		this(new LinkedList<BindParameter>(), sqlParser);
	}

	public BindParameterHolderImpl(List<BindParameter> parameters,
			SqlParser sqlParser) {
		this.parameters = parameters;
		this.sqlParser = sqlParser;
	}

	public List<BindParameter> getParameters() {
		return ListUtils.unmodifiableList(parameters);
	}

	public void addParameter(BindParameter parameter) {
	}

	public void addParameter(Integer index, Object value) {
		// TODO Auto-generated method stub
	}

	public void addParameter(Integer index, Object value, Integer sqlType) {
		// TODO Auto-generated method stub
	}

	public void addParameter(String parameter, Object value) {
		// TODO Auto-generated method stub
	}

	public void addParameter(String parameter, Object value, Integer sqlType) {
		// TODO Auto-generated method stub
	}

	public void addParameters(Map<String, Object> parameters) {
		// TODO Auto-generated method stub
	}

	public void addParameters(Object object) {
		// TODO Auto-generated method stub

	}

	protected SqlParser getSqlParser() {
		return sqlParser;
	}

	@Override
	public String toString() {
		return getParameters().toString();
	}
}
