package net.bi4vmr.study;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JVM反射相关工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class ReflectUtil {

    public static Field requireField(Class<?> clazz, String name) throws NoSuchFieldException {
        Field field;

        // 首先尝试查找当前类的属性
        try {
            field = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            // 如果当前类没有该属性，则尝试查找从父类继承的属性。
            field = clazz.getField(name);
        }

        return field;
    }

    public static Field getField(Class<?> clazz, String name) {
        try {
            return requireField(clazz, name);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T requireFieldValue(Object target, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = requireField(target.getClass(), name);
        // 取消属性访问权限检查，以便读取私有属性。
        field.setAccessible(true);
        // 将属性值转为目标类型并返回
        return (T) field.get(target);
    }

    public static <T> T getFieldValue(Object target, String name) {
        try {
            return requireFieldValue(target, name);
        } catch (Exception e) {
            return null;
        }
    }

    public static void setFieldValueUnsafe(Object target, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = requireField(target.getClass(), name);
        // 取消属性访问权限检查，以便修改私有属性。
        field.setAccessible(true);
        field.set(target, value);
    }

    public static boolean setFieldValue(Object target, String name, Object value) {
        try {
            setFieldValueUnsafe(target, name, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void clearFieldUnsafe(Object target, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = requireField(target.getClass(), name);
        field.setAccessible(true);
        field.set(target, null);
    }

    public static boolean clearField(Object target, String name) {
        try {
            clearFieldUnsafe(target, name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static Method requireMethod(Class<?> clazz, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method;

        // 首先尝试查找当前类的方法
        try {
            method = clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            // 如果当前类没有该方法，则尝试查找从父类继承的方法。
            method = clazz.getMethod(name, parameterTypes);
        }

        return method;
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return requireMethod(clazz, name, parameterTypes);
        } catch (Exception e) {
            return null;
        }
    }

    public static void invokeUnsafe(Object target, Method method, Object... parameters)
            throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        method.invoke(target, parameters);
    }

    public static boolean invoke(Object target, Method method, Object... parameters) {
        try {
            invokeUnsafe(target, method, parameters);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static <T> T invokeAsUnsafe(Object target, Method method, Object... parameters)
            throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        Object result = method.invoke(target, parameters);
        return (T) result;
    }

    public static <T> T invokeAs(Object target, Method method, Object... parameters) {
        try {
            return invokeAsUnsafe(target, method, parameters);
        } catch (Exception e) {
            return null;
        }
    }
}
