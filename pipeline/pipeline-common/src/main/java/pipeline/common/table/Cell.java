package pipeline.common.table;


public class Cell {
	private static final int DEFAULT_WIDTH = 25;
	private static final int DEFAULT_HEIGHT = 1;

	private Object object;

	private int height;
	private int column;
	private Row row;

	public Cell(Object object, Row row, int column) {
		this();
		this.object = object;
		this.row = row;
		this.column = column;
	}

	public Cell() {
		this.height = DEFAULT_HEIGHT;
	}

	public int getColumn() {
		return column;
	}

	public Area getArea() {
		return new Area(getWidth(), height);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return row.getWidth(this);
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
