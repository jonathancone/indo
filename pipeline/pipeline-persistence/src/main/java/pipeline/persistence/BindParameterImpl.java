package pipeline.persistence;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BindParameterImpl implements BindParameter {
	private String property;
	private Integer ordinal;
	private Object value;
	private Integer sqlType;

	public BindParameterImpl() {
	}

	public BindParameterImpl(Integer ordinal, Object value) {
		this.ordinal = ordinal;
		this.value = value;
	}

	public BindParameterImpl(Integer ordinal, Object value, Integer sqlType) {
		this.ordinal = ordinal;
		this.value = value;
		this.sqlType = sqlType;
	}

	public BindParameterImpl(String property, Object value) {
		super();
		this.property = property;
		this.value = value;
	}

	public BindParameterImpl(String property, Object value, Integer sqlType) {
		this.property = property;
		this.value = value;
		this.sqlType = sqlType;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Integer getSqlType() {
		return sqlType;
	}

	public void setSqlType(Integer sqlType) {
		this.sqlType = sqlType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE).toString();
	}
}
