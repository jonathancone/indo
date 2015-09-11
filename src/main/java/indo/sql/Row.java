package indo.sql;

import indo.jdbc.ResultSetMetaDatas;
import indo.util.CheckedIntConsumer;
import indo.jdbc.ResultSets;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by jcone on 9/11/2015.
 */
public class Row {
    private int row;
    private List<Object> values;

    public Row() {
        this.values = new ArrayList<>();
    }

    public Row(int row) {
        this();
        this.row = row;
    }

    public void add(Object value) {
        values.add(value);
    }

    public Object get(int index) {
        return values.get(index);
    }

    public int getColumnCount() {
        return values.size();
    }

    public static Row from(ResultSet rs) {
        Row row = new Row(ResultSets.getRow(rs));

        ResultSetMetaData rsm = ResultSets.getMetaData(rs);

        int count = ResultSetMetaDatas.getColumnCount(rsm);

        IntStream.range(0, count)
                .forEachOrdered((CheckedIntConsumer)
                        index -> row.add(rs.getObject(index)));

        return row;
    }
}
