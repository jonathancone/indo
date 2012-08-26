package pipeline.persistence.jdbc;

import java.sql.ResultSet;

public interface Row<T> {
	T mapRow(ResultSet rs, int rowNum);
}
