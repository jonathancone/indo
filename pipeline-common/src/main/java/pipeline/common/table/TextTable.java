package pipeline.common.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import pipeline.common.MoreCollections;
import pipeline.common.MoreReflection;
import pipeline.common.MoreString;


public class TextTable implements Table {

	private TextTableProperties properties;
	private List<Row> rows;

	private Area area;

	private StringBuilder content;
	private StringBuilder horizontal;

	private Collection<?> collection;
	private String[] fields;
	List<Integer> widths;

	public TextTable() {
		this(TextTableProperties.DEFAULT, null);
	}

	public TextTable(Object object, String... fields) {
		this(TextTableProperties.DEFAULT, Arrays.asList(object), fields);
	}

	public TextTable(Collection<?> collection, String... fields) {
		this(TextTableProperties.DEFAULT, collection, fields);
	}

	public TextTable(TextTableProperties properties, Collection<?> collection,
			String... fields) {
		this.collection = collection;
		this.fields = fields;
		this.content = new StringBuilder();
		this.horizontal = new StringBuilder();
		this.widths = new ArrayList<Integer>();
		this.properties = properties;
	}

	private void addCollectionRows() {

		boolean firstItem = true;
		Row first = null;
		Field[] fields = null;

		if (MoreCollections.isNotEmpty(collection)) {
			for (Object o : collection) {

				if (firstItem) {
					first = addRow();
					fields = o.getClass().getDeclaredFields();
				}
				Row row = addRow();

				for (Field field : fields) {

					if (firstItem) {
						field.setAccessible(true);

						String name = getProperties().isUppercaseHeader() ? field
								.getName().toUpperCase() : field.getName();

						first.addCell(name);
					}

					row.addCell(MoreReflection.get(field, o));

				}

				firstItem = false;
			}
		}
	}

	private StringBuilder construct() {

		addCollectionRows();

		int count = MoreCollections.size(rows);

		area = getArea();

		for (int i = 0; i < count; i++) {
			Row row = rows.get(i);
			render(row, i == 0, content);
			content.append(horizontal);
		}

		return horizontal.append(content);
	}

	private StringBuilder render(Row row, boolean buildHoriz, StringBuilder b) {

		boolean grid = getProperties().isShowGrid();

		for (Cell cell : row.getCells()) {

			if (grid) {
				if (buildHoriz) {
					horizontal.append(getProperties().getCornerCharacter());
					render(cell.getWidth(), MoreString.repeat(getProperties()
							.getHorizontalCharacter(), cell.getWidth()),
							horizontal);
				}
				b.append(getProperties().getVerticalCharacter());
			}
			render(cell, b);
		}

		if (grid) {
			if (buildHoriz) {
				horizontal.append(getProperties().getCornerCharacter());
				horizontal.append("\n");
			}
			b.append(getProperties().getVerticalCharacter());
		}
		b.append("\n");
		return b;
	}

	private StringBuilder render(Cell cell, StringBuilder b) {
		return render(cell.getWidth(), cell.getObject(), b);
	}

	private StringBuilder render(int width, Object content, StringBuilder b) {

		boolean rightJustify = content instanceof Number
				&& getProperties().isShowGrid();

		int realWidth = Math.min(width, getProperties().getMaxCellWidth());

		String realString = content == null ? "null" : MoreString
				.toString(content);

		realString = realString.substring(0,
				Math.min(realString.length(), realWidth));

		b.append(String.format("%" + (rightJustify ? "" : "-") + realWidth
				+ "s", realString));
		return b;
	}

	public Area getArea() {

		if (area == null) {

			int height = 0;
			int width = 0;

			if (MoreCollections.isNotEmpty(rows)) {

				for (Row row : rows) {
					width = Math.max(width, row.getArea().getWidth());
					height += row.getArea().getHeight();
				}

			}
			area = new Area(width, height);
		}
		return area;
	}

	public Row addRow() {
		Row row = new Row(this);
		getRows().add(row);
		return row;
	}

	public List<Row> getRows() {
		if (rows == null) {
			rows = new ArrayList<Row>();
		}
		return rows;
	}

	public TextTableProperties getProperties() {
		return properties;
	}

	public void setProperties(TextTableProperties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return construct().toString();
	}

	public int getWidth(Cell cell) {

		if (MoreCollections.isEmpty(widths)) {
			for (Row row : getRows()) {

				int index = 0;

				for (Cell c : row.getCells()) {
					int length = MoreString.toString(c.getObject()).length() + 2;

					Integer curWidth = MoreCollections.get(widths, index);

					if (curWidth == null) {
						widths.add(length);
					} else {
						widths.set(index, Math.max(curWidth, length));
					}
					index++;
				}

			}
		}

		return widths.get(cell.getColumn());

	}
}
