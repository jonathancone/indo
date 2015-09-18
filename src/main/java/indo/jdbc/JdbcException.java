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

package indo.jdbc;

import java.sql.SQLException;

/**
 * A {@link RuntimeException} suitable for wrapping {@link
 * SQLException} instances. This class provides {@link
 * #hasSQLExceptionCause()} and {@link #getSQLExceptionCause()}
 * methods for interacting with chained causes that are
 * instances of {@link SQLException}.
 *
 * @author Jonathan Cone
 * @see SQLException
 */
public class JdbcException extends RuntimeException {
    public JdbcException() {
    }

    public JdbcException(String message, Object... parameters) {
        super(String.format(message, parameters));
    }

    public JdbcException(String message) {
        super(message);
    }

    public JdbcException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdbcException(Throwable cause) {
        super(cause);
    }

    public JdbcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @return true if this instance is wrapping a {@link
     * SQLException}.
     */
    public boolean hasSQLExceptionCause() {
        return getCause() instanceof SQLException;
    }

    /**
     * Get the {@link SQLException} instance that is being
     * wrapped, call {@link #hasSQLExceptionCause()} to
     * ensure that the cause can be cast before calling this
     * method.
     *
     * @return the {@link SQLException} cause this instance
     * is wrapping.
     */
    public SQLException getSQLExceptionCause() {
        return (SQLException) getCause();
    }

}
