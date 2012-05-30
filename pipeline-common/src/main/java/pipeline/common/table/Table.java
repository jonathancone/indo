package pipeline.common.table;

import java.util.List;


public interface Table {
	Area getArea();

	List<Row> getRows();

	int getWidth(Cell cell);
}
