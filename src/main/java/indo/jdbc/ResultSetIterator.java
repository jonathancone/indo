/*
 * Copyright 2015  Jonathan Cone
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

package indo.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

/**
 * Created by jcone on 9/11/2015.
 */
public class ResultSetIterator implements Iterator<ResultSet>, AutoCloseable {

    private PreparedStatement ps;
    private ResultSet rs;

    public ResultSetIterator(PreparedStatement ps) {
        this.ps = ps;
    }

    private void open() {
        if (rs == null) {
            this.rs = Statements.executeQuery(ps);
        }
    }

    @Override
    public boolean hasNext() {

        open();

        boolean closed = ResultSets.isClosed(rs);

        boolean next = !closed && ResultSets.next(rs);

        if (!next && !closed) {
            close();
        }

        return next;
    }

    @Override
    public ResultSet next() {
        try {
            return rs;
        } catch (RuntimeException e) {
            close();
            throw e;
        }
    }


    @Override
    public void close() {
        ResultSets.close(rs);
        Statements.close(ps);
    }
}
