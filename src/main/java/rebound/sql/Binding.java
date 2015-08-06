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

/**
 * Created by jcone on 8/5/15.
 */
public class Binding {
    public static final String DEFAULT_BINDING_PLACEHOLDER = "?";
    public static final String DEFAULT_BINDING_DELIMITER = ",";

    public static String repeatPlaceholders(String placeholder, String delimiter, int length) {
        String sql = "";

        for (int i = 0; i < length; i++) {
            sql += placeholder;

            if (i != (length - 1)) {
                sql += delimiter;
            }
        }

        return sql;
    }

    public static String repeatPlaceholders(int length) {
        return repeatPlaceholders(DEFAULT_BINDING_PLACEHOLDER, DEFAULT_BINDING_DELIMITER, length);
    }

}
