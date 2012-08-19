package pipeline.common.table;

public class TextTableProperties {
	private static final String DEFAULT_VERT_DIVIDER = "|";
	private static final String DEFAULT_HORIZ_DIVIDER = "-";
	private static final String DEFAULT_CORNER_DIVIDER = "+";
	private static final int DEFAULT_MAX_CELL_WIDTH = 50;

	public static final TextTableProperties DEFAULT = new TextTableProperties(
			true, false, DEFAULT_CORNER_DIVIDER, DEFAULT_HORIZ_DIVIDER,
			DEFAULT_VERT_DIVIDER, DEFAULT_MAX_CELL_WIDTH);

	public static final TextTableProperties CAPITAL_HEADER_GRID = new TextTableProperties(
			true, true, DEFAULT_CORNER_DIVIDER, DEFAULT_HORIZ_DIVIDER,
			DEFAULT_VERT_DIVIDER, DEFAULT_MAX_CELL_WIDTH);

	public static final TextTableProperties CAPITAL_HEADER_NO_GRID = new TextTableProperties(
			false, true, DEFAULT_CORNER_DIVIDER, DEFAULT_HORIZ_DIVIDER,
			DEFAULT_VERT_DIVIDER, DEFAULT_MAX_CELL_WIDTH);

	public static final TextTableProperties DOT_STYLE = new TextTableProperties(
			true, false, ".", ".", " ", DEFAULT_MAX_CELL_WIDTH);

	public static final TextTableProperties WAVY_STYLE = new TextTableProperties(
			true, false, "~", "~", "|", DEFAULT_MAX_CELL_WIDTH);

	public static final TextTableProperties STAR_STYLE = new TextTableProperties(
			true, false, "*", "*", "|", DEFAULT_MAX_CELL_WIDTH);

	public static final TextTableProperties EQUALS_STYLE = new TextTableProperties(
			true, false, "=", "=", " ", DEFAULT_MAX_CELL_WIDTH);

	private boolean showGrid;
	private boolean uppercaseHeader;

	private String cornerCharacter;
	private String horizontalCharacter;
	private String verticalCharacter;

	private int maxCellWidth;

	public TextTableProperties(boolean showGrid, boolean uppercaseHeader,
			String cornerCharacter, String horizontalCharacter,
			String verticalCharacter, int maxCellWidth) {
		super();
		this.showGrid = showGrid;
		this.uppercaseHeader = uppercaseHeader;
		this.cornerCharacter = cornerCharacter;
		this.horizontalCharacter = horizontalCharacter;
		this.verticalCharacter = verticalCharacter;
		this.maxCellWidth = maxCellWidth;
	}

	public TextTableProperties() {

	}

	public int getMaxCellWidth() {
		return maxCellWidth;
	}

	public void setMaxCellWidth(int maxCellWidth) {
		this.maxCellWidth = maxCellWidth;
	}

	public boolean isShowGrid() {
		return showGrid;
	}

	public void setShowGrid(boolean showGrid) {
		this.showGrid = showGrid;
	}

	public boolean isUppercaseHeader() {
		return uppercaseHeader;
	}

	public void setUppercaseHeader(boolean uppercaseHeader) {
		this.uppercaseHeader = uppercaseHeader;
	}

	public String getCornerCharacter() {
		return cornerCharacter;
	}

	public void setCornerCharacter(String cornerCharacter) {
		this.cornerCharacter = cornerCharacter;
	}

	public String getHorizontalCharacter() {
		return horizontalCharacter;
	}

	public void setHorizontalCharacter(String horizontalCharacter) {
		this.horizontalCharacter = horizontalCharacter;
	}

	public String getVerticalCharacter() {
		return verticalCharacter;
	}

	public void setVerticalCharacter(String verticalCharacter) {
		this.verticalCharacter = verticalCharacter;
	}

}
