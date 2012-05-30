package more.common.table;

import java.util.Arrays;
import java.util.List;


import org.junit.Test;

import pipeline.common.table.TextTable;
import pipeline.common.table.TextTableProperties;

public class TextTableTest {
	@Test
	public void testTable() {
		List<Employee> emps = Arrays.asList(
				new Employee("Jim", "09/09/2000", 300, 130),
				new Employee("Sam", "01/14/1986", 400, 109), 
				new Employee("St. Peter Von Martkzstein III", "01/14/1986", 400, 109), 
				new Employee("Bob", "12/23/1943", 500, 1123420));

		TextTable table1 = new TextTable(TextTableProperties.DEFAULT, emps);
		TextTable table2 = new TextTable(TextTableProperties.CAPITAL_HEADER_GRID, emps);
		TextTable table3 = new TextTable(TextTableProperties.CAPITAL_HEADER_NO_GRID, emps);
		TextTable table4 = new TextTable(TextTableProperties.DOT_STYLE, emps);
		TextTable table5 = new TextTable(TextTableProperties.WAVY_STYLE, emps);
		TextTable table6 = new TextTable(TextTableProperties.STAR_STYLE, emps);
		TextTable table7 = new TextTable(TextTableProperties.EQUALS_STYLE, emps);

		System.out.println(table1.toString());
		System.out.println(table2.toString());
		System.out.println(table3.toString());
		System.out.println(table4.toString());
		System.out.println(table5.toString());
		System.out.println(table6.toString());
		System.out.println(table7.toString());
	}

	public static class Employee {
		private String name;
		private String birthday;
		private int rate;
		private double iq;

		public Employee(String name, String birthday, int rate, double iq) {
			super();
			this.name = name;
			this.birthday = birthday;
			this.rate = rate;
			this.iq = iq;
		}

	}

}
