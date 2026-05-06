package net.bi4vmr.study;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import sun.misc.Unsafe;

/**
 * Unsafe工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@SuppressWarnings("sunapi")
public final class UnsafeTools {

    private static final Unsafe UNSAFE;

    static {
        try {
            Field instanceField = Unsafe.class.getDeclaredField("theUnsafe");
            instanceField.setAccessible(true);
            Object obj = instanceField.get(null);
            if (obj == null) {
                throw new IllegalStateException("Failed to get Unsafe instance!");
            }
            UNSAFE = (Unsafe) obj;
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private UnsafeTools() {
        // 工具类，禁止实例化。
    }

    /**
     * 修改 static final boolean 字段的值。
     *
     * @param clazz     字段所在的 Class。
     * @param fieldName 字段名称。
     * @param value     目标值。
     */
    public static void setFinalBooleanValue(Class<?> clazz, String fieldName, boolean value) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (!Modifier.isStatic(field.getModifiers())) {
                throw new IllegalArgumentException(
                        "Field '" + fieldName + "' is not static. " +
                        "Use setFinalBooleanValue(target, fieldName, value) instead."
                );
            }
            long offset = UNSAFE.staticFieldOffset(field);
            Object base = UNSAFE.staticFieldBase(field);
            UNSAFE.putBoolean(base, offset, value);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to set static final boolean field '" + fieldName + "'", e);
        }
    }

    /**
     * 修改指定对象实例的 final boolean 字段的值。
     *
     * @param target    目标对象实例。
     * @param fieldName 字段名称（在目标对象的 Class 或其父类中声明）。
     * @param value     目标值。
     */
    public static void setFinalBooleanValue(Object target, String fieldName, boolean value) {
        try {
            Field field = findField(target.getClass(), fieldName);
            if (Modifier.isStatic(field.getModifiers())) {
                throw new IllegalArgumentException(
                        "Field '" + fieldName + "' is static. " +
                        "Use setFinalBooleanValue(clazz, fieldName, value) instead."
                );
            }
            long offset = UNSAFE.objectFieldOffset(field);
            UNSAFE.putBoolean(target, offset, value);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to set final boolean field '" + fieldName + "'", e);
        }
    }

    /**
     * 从当前类及其父类中查找指定名称的字段。
     */
    private static Field findField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Class<?> current = clazz;
        while (current != null) {
            try {
                return current.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            }
        }
        throw new NoSuchFieldException(
                "Field '" + fieldName + "' not found in class hierarchy of '" + clazz.getName() + "'"
        );
    }
}

