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

import indo.util.Collects;
import indo.util.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jcone on 8/1/15.
 */
public class Parameters implements Iterable<Parameter> {

    private static final Parameters EMPTY = new Parameters();

    private List<Parameter> parameterList;

    private Parameters() {
        this(0);
    }

    private Parameters(int capacity) {
        this.parameterList = new ArrayList<>(capacity);
    }

    public static Parameters empty() {
        return EMPTY;
    }

    public static Parameters fromList(List<?> list) {
        if (Collects.isNotEmpty(list)) {
            Parameters parameters = new Parameters(list.size());


            for (int i = 0; i < list.size(); i++) {
                parameters.parameterList.add(new Parameter(list.get(i), i + 1));
            }

            return parameters;
        } else {
            return empty();
        }
    }

    public static <T> Parameters fromArray(T[] t) {
        return fromList(Lists.fromArray(t));
    }

    @Override
    public Iterator<Parameter> iterator() {
        return parameterList.iterator();
    }

    public Stream<Parameter> stream() {
        return parameterList.stream();
    }

    public boolean hasParameters() {
        return Collects.isNotEmpty(parameterList);
    }
}
