/*
 * Copyright 2015 Indo Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
