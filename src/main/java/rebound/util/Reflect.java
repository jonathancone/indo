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

package rebound.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jcone on 8/25/15.
 */
public class Reflect<T> {
    private static final String GET_PREFIX = "get";
    private static final String IS_PREFIX = "is";
    private static final String SET_PREFIX = "set";

    private Class<T> aClass;
    private String property;


    private Reflect(Class<T> aClass) {
        this.aClass = aClass;
    }

    public static <T> Reflect<T> on(Class<T> aClass) {
        return new Reflect(aClass);
    }

    public Reflect<T> property(String property) {
        this.property = property;
        return this;
    }

    public Object is(Object instance) {
        return get(instance);
    }

    public Object get(Object instance) {

        Method getter = findGetter(aClass, property);

        try {
            return getter.invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw exception(e);
        }
    }

    public Object set(Object instance, Object value) {

        Method getter = findSetter(aClass, property);

        try {
            return getter.invoke(instance, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw exception(e);
        }
    }

    public T newInstance() {
        try {
            return aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw exception(e);
        }
    }

    private RuntimeException exception(Exception e) {

        if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
        }

        throw new RuntimeException(e);
    }

    private Method findSetter(Class<?> aClass, String property) {
        String formatted = Strings.wordCase(property);
        try {
            return aClass.getMethod(SET_PREFIX + formatted);
        } catch (NoSuchMethodException e) {
            throw exception(e);
        }
    }

    private Method findGetter(Class<?> aClass, String property) {
        String formatted = Strings.wordCase(property);

        try {
            return aClass.getMethod(GET_PREFIX + formatted);
        } catch (NoSuchMethodException e) {
            try {
                return aClass.getMethod(IS_PREFIX + formatted);
            } catch (NoSuchMethodException e1) {
                throw exception(e1);
            }
        }
    }
}
