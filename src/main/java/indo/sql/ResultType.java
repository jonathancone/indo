/*
 * Copyright 2017 Indo Contributors
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
 * @see ResultTypes
 * @see ResultSet
 */
@FunctionalInterface
public interface ResultType<T> {

    ResultType<BigDecimal> BIG_DECIMAL = ResultSets::getBigDecimal;
    ResultType<Boolean> BOOLEAN = ResultSets::getBoolean;
    ResultType<Byte> BYTE = ResultSets::getByte;
    ResultType<byte[]> BYTES = ResultSets::getBytes;
    ResultType<Date> DATE = ResultSets::getDate;
    ResultType<Double> DOUBLE = ResultSets::getDouble;
    ResultType<Float> FLOAT = ResultSets::getFloat;
    ResultType<Integer> INTEGER = ResultSets::getInt;
    ResultType<Long> LONG = ResultSets::getLong;
    ResultType<String> NSTRING = ResultSets::getNString;
    ResultType<Object> OBJECT = ResultSets::getObject;
    ResultType<Short> SHORT = ResultSets::getShort;
    ResultType<String> STRING = ResultSets::getString;
    ResultType<Time> TIME = ResultSets::getTime;
    ResultType<Timestamp> TIMESTAMP = ResultSets::getTimestamp;
    ResultType<java.net.URL> URL = ResultSets::getURL;
    ResultType<LocalDate> LOCAL_DATE = DateType::getLocalDate;
    ResultType<LocalDateTime> LOCAL_DATE_TIME = DateType::getLocalDateTime;
    ResultType<LocalTime> LOCAL_TIME = DateType::getLocalTime;
    ResultType<Instant> INSTANT = DateType::getInstant;


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
