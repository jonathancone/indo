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

package indo.util;

/**
 * String utility methods.
 */
public class Strings {
    public static boolean isBlank(String s) {
        return s == null || s.trim().equals("");
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    public static String before(String input, char before) {
        int i = input.indexOf(before);
        return input.substring(0, (i > -1 ? i : input.length()));
    }

    public static String wordCase(String input) {
        return isBlank(input) ? input : input.substring(0, 1).toUpperCase() + input.substring(1, input.length());
    }
}
