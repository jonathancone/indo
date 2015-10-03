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

package indo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for working with {@link Map} instances.
 *
 * @author Jonathan Cone
 */
public class Maps {

    public static <S, T> Map<S, T> newHashMap() {
        return new HashMap<>();
    }

    public static <S, T> Map<S, T> newHashMap(S s1, T t1) {
        HashMap<S, T> map = new HashMap<>();
        map.put(s1, t1);
        return map;
    }

    public static <S, T> Map<S, T> newHashMap(S s1, T t1, S s2, T t2) {
        Map<S, T> map = newHashMap(s1, t1);
        map.put(s2, t2);
        return map;
    }

    public static <S, T> Map<S, T> newHashMap(S s1, T t1, S s2, T t2, S s3, T t3) {
        Map<S, T> map = newHashMap(s1, t1, s2, t2);
        map.put(s3, t3);
        return map;
    }

    public static <S, T> Map<S, T> newHashMap(S s1, T t1, S s2, T t2, S s3, T t3, S s4, T t4) {
        Map<S, T> map = newHashMap(s1, t1, s2, t2, s3, t3);
        map.put(s4, t4);
        return map;
    }

    public static <S, T> Map<S, T> newHashMap(S s1, T t1, S s2, T t2, S s3, T t3, S s4, T t4, S s5, T t5) {
        Map<S, T> map = newHashMap(s1, t1, s2, t2, s3, t3, s4, t4);
        map.put(s5, t5);
        return map;
    }

}
