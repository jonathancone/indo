package pipeline.common.table;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class Row {
	private Table table;
	private List<Cell> cells;

	public Row(Table table) {
		this.cells = new ArrayList<Cell>();
		this.table = table;
	}

	public Row addCell(Object o) {
		getCells().add(new Cell(o, this, getCellCount()));
		return this;
	}

	public int getWidth(Cell cell) {
		return table.getWidth(cell);
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	public int getCellCount() {
		return CollectionUtils.size(cells);
	}

	public Area getArea() {

		int height = 0;
		int width = 0;
		if (CollectionUtils.isNotEmpty(cells)) {

			for (Cell c : cells) {
				height = Math.max(height, c.getArea().getHeight());
				width += c.getArea().getWidth();
			}
		}
		return new Area(width, height);

	}
}
