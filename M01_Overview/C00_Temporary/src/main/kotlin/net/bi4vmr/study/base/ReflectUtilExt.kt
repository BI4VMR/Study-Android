@file:Suppress("UNCHECKED_CAST")

/*
 * JVM反射相关工具的扩展方法。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */

package net.bi4vmr.study.base

import net.bi4vmr.study.ReflectUtil
import java.lang.reflect.Field
import java.lang.reflect.Method

typealias ReflectUtilKT = net.bi4vmr.study.base.ReflectUtil

fun Any.requireField(name: String): Field = ReflectUtil.requireField(this.javaClass, name)

fun Any.getField(name: String): Field? = ReflectUtil.getField(this.javaClass, name)

fun <T : Any> Any.requireFieldValue(name: String): T = ReflectUtil.requireFieldValue(this, name)

fun <T : Any> Any.getFieldValue(name: String): T? = ReflectUtil.getFieldValue(this, name)

fun Any.setFieldValueUnsafe(name: String, value: Any) = ReflectUtil.setFieldValueUnsafe(this, name, value)

fun Any.setFieldValue(name: String, value: Any): Boolean = ReflectUtil.setFieldValue(this, name, value)

fun Any.clearFieldUnsafe(name: String) = ReflectUtil.clearFieldUnsafe(this, name)

fun Any.clearField(name: String): Boolean = ReflectUtil.clearField(this, name)

fun Any.requireMethod(name: String, vararg parameterTypes: Class<*>): Method =
    ReflectUtil.requireMethod(this.javaClass, name, *parameterTypes)

fun Any.getMethod(name: String, vararg parameterTypes: Class<*>): Method? =
    ReflectUtil.getMethod(this.javaClass, name, *parameterTypes)

fun Method.invokeUnsafe(target: Any, vararg parameters: Any) = ReflectUtil.invokeUnsafe(target, this, parameters)

fun Method.invoke(target: Any, vararg parameters: Any): Boolean = ReflectUtil.invoke(target, this, parameters)

fun <T : Any> Method.invokeAsUnsafe(target: Any, vararg parameters: Any): T? =
    ReflectUtil.invokeAsUnsafe<T>(target, this, parameters)

fun <T : Any> Method.invokeAs(target: Any, vararg parameters: Any): T? =
    ReflectUtil.invokeAs<T>(target, this, parameters)


fun Any.setLazyPropertyValueUnsafe(name: String, value: Any) =
    ReflectUtilKT.setLazyPropertyValueUnsafe(this, name, value)

fun Any.setLazyPropertyValue(name: String, value: Any) =
    ReflectUtilKT.setLazyPropertyValue(this, name, value)

fun <T : Any> Any.requireStaticField(fieldName: String): T {
    fun findField(startClass: Class<*>?): Field? {
        var current = startClass
        while (current != null) {
            try {
                return current.getDeclaredField(fieldName)
            } catch (_: NoSuchFieldException) {
                current = current.superclass
            }
        }
        return null
    }

    val candidates = linkedSetOf<Class<*>>().apply {
        add(this@requireStaticField.javaClass)
        // Kotlin companion fields are often emitted on the enclosing host class.
        this@requireStaticField.javaClass.enclosingClass?.let { add(it) }
    }

    val field = candidates.asSequence()
        .mapNotNull { findField(it) }
        .firstOrNull()
        ?: throw NoSuchFieldException(fieldName)

    field.isAccessible = true
    return field.get(null) as T
}
