package pipeline.persistence;

import java.util.List;

public interface SqlParser {
	List<ParameterPart> findParameterParts(String sql);

}
