/*
 * Copyright (c) 2015 Rebound Contributors
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

package rebound.sql;

import java.sql.SQLException;

/**
 * A helper utility to handle checked exceptions.
 *
 * @author Jonathan Cone
 */
public class Unchecked {
    public static <T extends Exception> RuntimeException exception(T t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else {
            if (t instanceof SQLException) {
                return new JdbcException(t);
            }
            return new RuntimeException(t);
        }
    }
}
