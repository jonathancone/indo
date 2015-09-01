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
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jcone on 8/25/15.
 */
public class Reflect<T> {
    private static final String GET_PREFIX = "get";
    private static final String IS_PREFIX = "is";
    private static final String SET_PREFIX = "set";

    private Class<T> aClass;
    private T instance;
    private String property;
    private Stack<Object> returnValues;

    private Reflect(Class<T> aClass) {
        this.aClass = aClass;
        this.returnValues = new Stack<>();
    }

    private Reflect(T instance) {
        this((Class<T>) instance.getClass());
        this.instance = instance;
    }

    public static <T> Reflect<T> on(Class<T> aClass) {
        return new Reflect<>(aClass);
    }

    public static <T> Reflect<T> on(T instance) {
        return new Reflect<>(instance);
    }

    public Reflect<T> property(String property) {
        this.property = property;
        return this;
    }

    public Object pop() {
        return returnValues.pop();
    }

    public <S> S pop(Class<S> type) {
        return (S) returnValues.pop();
    }

    public Reflect<T> is() {
        return get();
    }

    public Reflect<T> get() {
        return invoke(findGetter(aClass, property));
    }

    public Reflect<T> set(Object value) {
        return invoke(findSetter(aClass, property, value.getClass()), value);
    }

    public Reflect<T> set(byte value) {
        return invoke(findSetter(aClass, property, byte.class), value);
    }

    public Reflect<T> set(byte[] value) {
        return invoke(findSetter(aClass, property, byte[].class), value);
    }

    public Reflect<T> set(short value) {
        return invoke(findSetter(aClass, property, short.class), value);
    }

    public Reflect<T> set(short[] value) {
        return invoke(findSetter(aClass, property, short[].class), value);
    }

    public Reflect<T> set(int value) {
        return invoke(findSetter(aClass, property, int.class), value);
    }

    public Reflect<T> set(int[] value) {
        return invoke(findSetter(aClass, property, int[].class), value);
    }

    public Reflect<T> set(long value) {
        return invoke(findSetter(aClass, property, long.class), value);
    }

    public Reflect<T> set(long[] value) {
        return invoke(findSetter(aClass, property, long[].class), value);
    }

    public Reflect<T> set(float value) {
        return invoke(findSetter(aClass, property, float.class), value);
    }

    public Reflect<T> set(float[] value) {
        return invoke(findSetter(aClass, property, float[].class), value);
    }

    public Reflect<T> set(double value) {
        return invoke(findSetter(aClass, property, double.class), value);
    }

    public Reflect<T> set(double[] value) {
        return invoke(findSetter(aClass, property, double[].class), value);
    }

    public Reflect<T> set(char value) {
        return invoke(findSetter(aClass, property, char.class), value);
    }

    public Reflect<T> set(char[] value) {
        return invoke(findSetter(aClass, property, char[].class), value);
    }

    public Reflect<T> set(boolean value) {
        return invoke(findSetter(aClass, property, boolean.class), value);
    }

    public Reflect<T> set(boolean[] value) {
        return invoke(findSetter(aClass, property, boolean[].class), value);
    }

    public Reflect<T> newInstance() {
        try {
            returnValues.push(aClass.newInstance());
            return this;
        } catch (InstantiationException | IllegalAccessException e) {
            throw exception(e);
        }
    }

    public Reflect<T> invoke(String method, Object... values) {
        return invoke(findMethod(aClass, method, toType(values)), values);
    }

    public Reflect<T> invoke(Method method, Object... values) {
        try {
            returnValues.push(method.invoke(instance, values));
            return this;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw exception(e);
        }
    }

    private RuntimeException exception(Exception e) {

        if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
        }

        throw new RuntimeException(e);
    }

    private Class<?>[] toType(Object... values) {
        if (Multi.isNotEmpty(values)) {

            List<Class<?>> classes = new ArrayList<>();

            for (Object value : values) {
                classes.add(value != null ? value.getClass() : null);
            }
        }
        return null;
    }


    private Method findMethod(Class<?> aClass, String method, Class<?>... parameterTypes) {
        try {
            return aClass.getMethod(method, parameterTypes);
        } catch (NoSuchMethodException e) {
            try {
                Method declaredMethod = aClass.getDeclaredMethod(method, parameterTypes);

                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException e1) {
                return null;
            }
        }
    }

    private Method findSetter(Class<?> aClass, String property, Class<?> parameter) {
        return findMethod(aClass, SET_PREFIX + Strings.wordCase(property), parameter);
    }

    private Method findGetter(Class<?> aClass, String property) {
        String formatted = Strings.wordCase(property);

        Method method = findMethod(aClass, GET_PREFIX + formatted);

        return method != null ? method : findMethod(aClass, IS_PREFIX + formatted);
    }
}
