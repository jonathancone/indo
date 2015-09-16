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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by jcone on 8/25/15.
 */
@SuppressWarnings("PrimitiveArrayArgumentToVariableArgMethod")
public class Reflect<T> {
    private static final String GET_PREFIX = "get";
    private static final String IS_PREFIX = "is";
    private static final String SET_PREFIX = "set";

    private static ConcurrentMap<Class<?>, ConcurrentMap<String, Field>> fieldCache = new ConcurrentHashMap<>();

    private Class<T> aClass;
    private T instance;
    private List<Object> returnValues;


    private Reflect(Class<T> aClass) {
        this.aClass = aClass;
        this.returnValues = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
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

    protected static Class<?>[] toType(Object... values) {
        return Arrays.stream(values)
                .map(value -> value != null ? value.getClass() : null)
                .collect(Collectors.toList())
                .toArray(new Class<?>[values.length]);
    }

    public ConcurrentMap<String, Field> fields() {

        fieldCache.computeIfAbsent(aClass, (input) -> {
            ArrayList<Field> fields = new ArrayList<>();

            fields.addAll(Arrays.asList(input.getFields()));
            fields.addAll(Arrays.asList(input.getDeclaredFields()));

            ConcurrentMap<String, Field> result = fields.stream().
                    collect(Collectors.toConcurrentMap(f -> f.getName(), Function.identity()));

            result.values().stream()
                    .filter(field -> !field.isAccessible())
                    .forEach(field -> field.setAccessible(true));

            return result;
        });

        return fieldCache.get(aClass);
    }

    public <S> Optional<S> lastReturn() {
        return returnedOn(returnValues.size() - 1);
    }

    public int returnCount() {
        return returnValues != null ? returnValues.size() : 0;
    }

    public boolean hasReturn() {
        return returnCount() > 0;
    }

    @SuppressWarnings("unchecked")
    public <S> Optional<S> returnedOn(int index) {
        return Optional.ofNullable((S) (index > -1 && index < returnValues.size() ? returnValues.get(index) : null));
    }

    public <S> Optional<S> property(String property) {
        return get(property).lastReturn();
    }

    public Reflect<T> property(String property, Object value) {
        return invoke(findSetter(aClass, property, toType(value)[0]), value);
    }

    public Reflect<T> property(String property, byte value) {
        return invoke(findSetter(aClass, property, byte.class), value);
    }

    public Reflect<T> property(String property, byte[] value) {
        return invoke(findSetter(aClass, property, byte[].class), value);
    }

    public Reflect<T> property(String property, short value) {
        return invoke(findSetter(aClass, property, short.class), value);
    }

    public Reflect<T> property(String property, short[] value) {
        return invoke(findSetter(aClass, property, short[].class), value);
    }

    public Reflect<T> property(String property, int value) {
        return invoke(findSetter(aClass, property, int.class), value);
    }

    public Reflect<T> property(String property, int[] value) {
        return invoke(findSetter(aClass, property, int[].class), value);
    }

    public Reflect<T> property(String property, long value) {
        return invoke(findSetter(aClass, property, long.class), value);
    }

    public Reflect<T> property(String property, long[] value) {
        return invoke(findSetter(aClass, property, long[].class), value);
    }

    public Reflect<T> property(String property, float value) {
        return invoke(findSetter(aClass, property, float.class), value);
    }

    public Reflect<T> property(String property, float[] value) {
        return invoke(findSetter(aClass, property, float[].class), value);
    }

    public Reflect<T> property(String property, double value) {
        return invoke(findSetter(aClass, property, double.class), value);
    }

    public Reflect<T> property(String property, double[] value) {
        return invoke(findSetter(aClass, property, double[].class), value);
    }

    public Reflect<T> property(String property, char value) {
        return invoke(findSetter(aClass, property, char.class), value);
    }

    public Reflect<T> property(String property, char[] value) {
        return invoke(findSetter(aClass, property, char[].class), value);
    }

    public Reflect<T> property(String property, boolean value) {
        return invoke(findSetter(aClass, property, boolean.class), value);
    }

    public Reflect<T> property(String property, boolean[] value) {
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

    public T newInstanceNow() {
        return newInstance().getInstance();
    }

    public Reflect<T> get(String property) {
        return invoke(findGetter(aClass, property));
    }

    public Reflect<T> set(String property, Object value) {
        return property(property, value);
    }

    public Reflect<T> invoke(String method, Object... values) {
        return invoke(findMethod(aClass, method, toType(values)), values);
    }

    public Reflect<T> invoke(Method method, Object... values) {
        try {

            if (!method.isAccessible()) {
                method.setAccessible(true);
            }

            Object result = method.invoke(instance, values);

            Class<?> returnType = method.getReturnType();

            if (!Objects.equals(returnType, void.class)) {
                returnValues.add(result);
            }

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
                        .allMatch(index -> targetTypes[index] == null ||
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
