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

import indo.jdbc.ResultSets;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Interface type used to specify which data type will be used to retrieve a
 * named column from the JDBC driver returned {@link ResultSet}.  Overriding the
 * default type assumes that the JDBC driver supports the requested type
 * mapping.  A few of the more common overrides are provided, but since this
 * interface is a {@link FunctionalInterface}, other types can be specified as
 * needed on-the-fly via method reference or lambda expression.
 *
 * @author Jonathan Cone
 * @see ColumnTypes
 * @see ResultSet
 */
@FunctionalInterface
public interface Type<T> {

    Type<BigDecimal> BIG_DECIMAL = ResultSets::getBigDecimal;
    Type<Boolean> BOOLEAN = ResultSets::getBoolean;
    Type<Byte> BYTE = ResultSets::getByte;
    Type<byte[]> BYTES = ResultSets::getBytes;
    Type<Date> DATE = ResultSets::getDate;
    Type<Double> DOUBLE = ResultSets::getDouble;
    Type<Float> FLOAT = ResultSets::getFloat;
    Type<Integer> INTEGER = ResultSets::getInt;
    Type<Long> LONG = ResultSets::getLong;
    Type<String> NSTRING = ResultSets::getNString;
    Type<Object> OBJECT = ResultSets::getObject;
    Type<Short> SHORT = ResultSets::getShort;
    Type<String> STRING = ResultSets::getString;
    Type<Time> TIME = ResultSets::getTime;
    Type<Timestamp> TIMESTAMP = ResultSets::getTimestamp;
    Type<java.net.URL> URL = ResultSets::getURL;


    /**
     * Resolve a column name as the type specified by this instance.
     *
     * @param rs         The {@link ResultSet} instance to resolve the column
     *                   name against.
     * @param columnName The name of the column to resolve.
     * @return The value that wa resolved.
     */
    T asType(ResultSet rs, String columnName);
}
