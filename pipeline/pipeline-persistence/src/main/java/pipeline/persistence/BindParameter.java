package pipeline.persistence;

public interface BindParameter {
	String getProperty();

	Integer getOrdinal();

	Object getValue();

	Integer getSqlType();
}
