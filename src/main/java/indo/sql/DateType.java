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
import indo.jdbc.Statements;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by jcone on 1/30/17.
 */
public class DateType {
    public static LocalDate getLocalDate(ResultSet rs, String columnLabel) {
        Date date = ResultSets.getDate(rs, columnLabel);

        if (date != null) {
            return date.toLocalDate();
        }

        return null;
    }

    public static void setLocalDate(PreparedStatement ps, int parameterIndex, LocalDate date) {
        if (date != null) {
            Statements.setDate(ps, parameterIndex, Date.valueOf(date));
        } else {
            Statements.setDate(ps, parameterIndex, null);
        }
    }

    public static LocalDateTime getLocalDateTime(ResultSet rs, String columnLabel) {
        Timestamp timestamp = ResultSets.getTimestamp(rs, columnLabel);

        if (timestamp != null) {
            return timestamp.toLocalDateTime();
        }

        return null;
    }


    public static void setLocalDateTime(PreparedStatement ps, int parameterIndex, LocalDateTime dateTime) {
        if (dateTime != null) {
            Statements.setTimestamp(ps, parameterIndex, Timestamp.valueOf(dateTime));
        } else {
            Statements.setTimestamp(ps, parameterIndex, null);
        }
    }

    public static Instant getInstant(ResultSet rs, String columnLabel) {
        Timestamp timestamp = ResultSets.getTimestamp(rs, columnLabel);

        if (timestamp != null) {
            return timestamp.toInstant();
        }

        return null;
    }

    public static void setInstant(PreparedStatement ps, int parameterIndex, Instant instant) {
        if (instant != null) {
            Statements.setTimestamp(ps, parameterIndex, Timestamp.from(instant));
        } else {
            Statements.setTimestamp(ps, parameterIndex, null);
        }
    }

    public static LocalTime getLocalTime(ResultSet rs, String columnLabel) {
        Time time = ResultSets.getTime(rs, columnLabel);

        if (time != null) {
            return time.toLocalTime();
        }

        return null;
    }

    public static void setLocalTime(PreparedStatement ps, int parameterIndex, LocalTime localTime) {
        if (localTime != null) {
            Statements.setTime(ps, parameterIndex, Time.valueOf(localTime));
        } else {
            Statements.setTime(ps, parameterIndex, null);
        }
    }

}