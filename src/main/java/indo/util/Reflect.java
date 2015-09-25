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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class follows the fluent builder pattern to allow working with Java
 * classes via reflection.
 *
 * @param <T> The type to reflect upon.
 */
public class Reflect<T> {
    private static final String GET_PREFIX = "get";
    private static final String IS_PREFIX = "is";
    private static final String SET_PREFIX = "set";

    /**
     * This is a cache of the fields that we're aware of. WeakHashMap keys have
     * == identity semantics which allow us to safely key on the class object
     * since it also has == identity semantics.
     */
    private static Map<Class<?>, Map<String, Field>> fieldCache =
            Collections.synchronizedMap(new WeakHashMap<>());

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

    public Set<String> fieldNames() {
        return fields().keySet();
    }

    public Map<String, Field> fields() {

        fieldCache.computeIfAbsent(aClass, (input) -> {
            ArrayList<Field> fields = new ArrayList<>();

            fields.addAll(Arrays.asList(input.getFields()));
            fields.addAll(Arrays.asList(input.getDeclaredFields()));

            ConcurrentMap<String, Field> result = fields.stream().
                    collect(Collectors.toConcurrentMap(Field::getName, Function.identity()));

            result.values().stream()
                    .filter(field -> !field.isAccessible())
                    .forEach(field -> field.setAccessible(true));

            return Collections.unmodifiableMap(result);
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
        Optional<Method> setter = findSetter(aClass, property, toType(value)[0]);
        if (setter.isPresent()) {
            return invoke(setter.get(), value);
        } else {
            throw new NoSuchMethodError("There is no setter method for property " + property);
        }
    }

    public T getInstance() {
        return instance;
    }

    public Reflect<T> newInstanceIfAbsent() {
        return instance != null ? this : newInstance();
    }


    public Reflect<T> newInstance() {
        Constructor<T> constructor = null;
        try {
            try {
                constructor = aClass.getConstructor();
            } catch (NoSuchMethodException nsm) {
                constructor = aClass.getDeclaredConstructor();
            }

            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }

            instance = constructor.newInstance();
            return this;

        } catch (Exception e) {
            throw Unchecked.exception(e);
        }
    }

    public T newInstanceNow() {
        return newInstance().getInstance();
    }

    public Reflect<T> get(String property) {
        Optional<Method> getter = findGetter(aClass, property);
        if (getter.isPresent()) {
            return invoke(getter.get());
        } else {
            throw new NoSuchMethodError("There is no getter method for property " + property);
        }
    }

    public Reflect<T> set(String property, Object value) {
        return property(property, value);
    }

    public Reflect<T> invoke(String method, Object... values) {
        Optional<Method> optional = findMethod(aClass, method, toType(values));
        if (optional.isPresent()) {
            return invoke(optional.get(), values);
        } else {
            throw new NoSuchMethodError("No method " + method + " exists.");
        }
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
            throw Unchecked.exception(e);
        }
    }

    private Optional<Method> findMethod(Class<?> searchClass, String targetMethod, Class<?>... targetTypes) {

        Optional<Method> publicMatch = findCompatibleMethod(searchClass.getMethods(), targetMethod, targetTypes);

        if (publicMatch.isPresent()) {
            return publicMatch;
        } else {
            Optional<Method> declaredMatch = findCompatibleMethod(searchClass.getDeclaredMethods(), targetMethod, targetTypes);

            if (declaredMatch.isPresent()) {
                if (!declaredMatch.get().isAccessible()) {
                    declaredMatch.get().setAccessible(true);
                }
                return declaredMatch;
            } else if (Objects.nonNull(targetTypes) && targetTypes.length == 1) {
                // If we take a single parameter, lets introspect the type and see if
                // we can coerce it to a primitive.
                Class<?> targetType = targetTypes[0];
                if (targetType == Boolean.class) {
                    return findMethod(searchClass, targetMethod, boolean.class);
                } else if (targetType == Byte.class) {
                    return findMethod(searchClass, targetMethod, byte.class);
                } else if (targetType == Character.class) {
                    return findMethod(searchClass, targetMethod, char.class);
                } else if (targetType == Short.class) {
                    return findMethod(searchClass, targetMethod, short.class);
                } else if (targetType == Integer.class) {
                    return findMethod(searchClass, targetMethod, int.class);
                } else if (targetType == Long.class) {
                    return findMethod(searchClass, targetMethod, long.class);
                } else if (targetType == Float.class) {
                    return findMethod(searchClass, targetMethod, float.class);
                } else if (targetType == Double.class) {
                    return findMethod(searchClass, targetMethod, double.class);
                }
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

    public Optional<Method> findSetter(String property, Object value) {
        return findSetter(aClass, property, toType(value)[0]);
    }

    private Optional<Method> findSetter(Class<?> aClass, String property, Class<?> parameter) {
        return findMethod(aClass, SET_PREFIX + Strings.wordCase(property), parameter);
    }

    public Optional<Method> findGetter(String property) {
        return findGetter(aClass, property);
    }

    private Optional<Method> findGetter(Class<?> aClass, String property) {
        String formatted = Strings.wordCase(property);

        Optional<Method> method = findMethod(aClass, GET_PREFIX + formatted);

        return method.isPresent() ? method : findMethod(aClass, IS_PREFIX + formatted);
    }
}
