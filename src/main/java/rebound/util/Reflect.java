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
import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public <S> S pop() {
        return (S) returnValues.pop();
    }

    public int returnValueSize() {
        return returnValues != null ? returnValues.size() : 0;
    }

    public boolean hasReturnValues() {
        return returnValueSize() > 0;
    }

    public boolean is() {
        return get();
    }

    public <S> S get() {
        return invokeGetter(property).<S>pop();
    }

    public Reflect<T> set(Object value) {
        return invokeSetter(property, value);
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

    public T getInstance() {
        return instance;
    }

    public Reflect<T> newInstance() {
        try {
            instance = aClass.newInstance();
            return this;
        } catch (InstantiationException | IllegalAccessException e) {
            throw exception(e);
        }
    }


    public Reflect<T> invokeGetter(String property) {
        return invoke(findGetter(aClass, property));
    }

    public Reflect<T> invokeGetter() {
        return invokeGetter(property);
    }

    public Reflect<T> invokeSetter(String property, Object value) {
        return invoke(findSetter(aClass, property, toType(value)[0]), value);
    }

    public Reflect<T> invokeSetter(Object value) {
        return invokeSetter(property, value);
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
        if (values != null) {
            return Arrays.stream(values)
                    .map(value -> value != null ? value.getClass() : null)
                    .collect(Collectors.toList())
                    .toArray(new Class<?>[values.length]);
        }
        return null;
    }

    private Method findMethod(Class<?> searchClass, String targetMethod, Class<?>... targetTypes) {

        Optional<Method> publicMatch = findCompatibleMethod(searchClass.getMethods(), targetMethod, targetTypes);

        if (publicMatch.isPresent()) {
            return publicMatch.get();
        } else {
            Optional<Method> declaredMatch = findCompatibleMethod(searchClass.getDeclaredMethods(), targetMethod, targetTypes);

            if (declaredMatch.isPresent()) {
                if (!declaredMatch.get().isAccessible()) {
                    declaredMatch.get().setAccessible(true);
                }
                return declaredMatch.get();
            }
        }
        return null;
    }

    private Optional<Method> findCompatibleMethod(Method[] searchMethods, String targetMethod, Class<?>... targetTypes) {
        return Arrays.stream(searchMethods)
                .filter(m -> m.getName().equals(targetMethod))
                .filter(m -> m.getParameterCount() == targetTypes.length)
                .filter(m -> IntStream
                        .range(0, targetTypes.length)
                        .allMatch(index ->
                                m.getParameterTypes()[index].isAssignableFrom(targetTypes[index])))
                .findFirst();
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
