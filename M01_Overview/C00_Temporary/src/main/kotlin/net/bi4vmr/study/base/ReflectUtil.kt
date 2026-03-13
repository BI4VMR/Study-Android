package net.bi4vmr.study.base

import net.bi4vmr.study.ReflectUtil
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

/**
 * JVM反射相关工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
object ReflectUtil {

    fun setLazyPropertyValueUnsafe(target: Any, name: String, value: Any) {
        val property = try {
            // 首先尝试查找当前类的属性
            target::class.declaredMemberProperties.first { it.name == name }
        } catch (e: NoSuchElementException) {
            // 如果当前类没有该属性，则尝试查找从父类继承的属性。
            target::class.memberProperties.first { it.name == name }
        }
        property.isAccessible = true

        // 访问属性的后备字段，取得当前属性的值。
        val jvmField = property.javaField
            ?: throw IllegalArgumentException("Kotlin property [$name] does not have a backing field!")
        val current = jvmField.get(target)

        // 兼容性措施：如果目标属性是一个非Lazy委托属性，尝试直接设置新的值。
        if (current !is Lazy<*>) {
            jvmField.set(target, value)
            return
        }

        /*
         * 如果目标属性是一个Lazy委托属性，需要修改Lazy接口实现类内部的值，通常名称为 `_value` ，外部访问Lazy属性时通过 `value` 属性的
         * Getter方法实际上即访问实现类内部属性的值。
         */
        ReflectUtil.setFieldValueUnsafe(current, "_value", value)
    }

    fun setLazyPropertyValue(target: Any, name: String, value: Any): Boolean {
        return try {
            setLazyPropertyValueUnsafe(target, name, value)
            true
        } catch (e: Exception) {
            false
        }
    }
}
