package net.bi4vmr.study.xmlattrs

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.UiContext
import net.bi4vmr.study.R
import net.bi4vmr.study.base.BusinessCardSimpleKT
import net.bi4vmr.study.databinding.AttrTestViewBinding

/**
 * 自定义控件：属性类型测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@SuppressLint("SetTextI18n", "CustomViewStyleable")
class AttrTestViewKT @JvmOverloads constructor(
    @UiContext
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(mContext, attrs, defStyleAttr, defStyleRes) {

    companion object {
        private val TAG: String = "TestApp-${BusinessCardSimpleKT::class.java.simpleName}"
    }

    private val binding: AttrTestViewBinding by lazy {
        // 将布局文件渲染生成View实例
        AttrTestViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        // 如果当前实例不是通过布局文件生成的，则不必解析XML属性。
        if (attrs != null) {
            testBaseType(attrs)
            testTextType(attrs)
            testDimenType(attrs)
            testFractionType(attrs)
            testColorType(attrs)
            testEnumType(attrs)
            testFlagType(attrs)
            testRefType(attrs)
            testMultiTypes(attrs)
        }
    }

    /**
     * 示例三：基本数据类型属性的使用方法。
     *
     * 在本示例中，我们定义一些基本数据类型的XML属性，并在自定义View中解析它们。
     */
    private fun testBaseType(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.AttrTypes).use {
            // 获取布尔值
            val booleanValue: Boolean = it.getBoolean(R.styleable.AttrTypes_booleanValue, false)
            binding.tvBooleanValue.text = booleanValue.toString()

            // 获取整型值
            val intValue: Int = it.getInt(R.styleable.AttrTypes_integerValue, 0)
            binding.tvIntegerValue.text = intValue.toString()

            // 获取浮点值
            val floatValue: Float = it.getFloat(R.styleable.AttrTypes_floatValue, 0.0F)
            binding.tvFloatValue.text = floatValue.toString()
        }
    }

    /**
     * 示例四：文本类型属性的使用方法。
     *
     * 在本示例中，我们定义一些文本类型的XML属性，并在自定义View中解析它们。
     */
    private fun testTextType(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.AttrTypes).use {
            // 获取文本（忽略格式）
            val textValue1: String? = it.getString(R.styleable.AttrTypes_textValue1)
            binding.tvTextValue1.text = textValue1

            // 获取文本（解析格式）
            val textValue2: CharSequence? = it.getText(R.styleable.AttrTypes_textValue2)
            binding.tvTextValue2.text = textValue2
        }
    }

    /**
     * 示例五：尺寸类型属性的使用方法。
     *
     * 在本示例中，我们定义一些尺寸类型的XML属性，并在自定义View中解析它们。
     */
    private fun testDimenType(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.AttrTypes).use {
            // 解析尺寸为像素值
            val dimenValue1: Float = it.getDimension(R.styleable.AttrTypes_dimenValue, 1.0F)
            binding.tvDimenValue.text = dimenValue1.toString()

            // 解析尺寸为像素值（如果有小数部分，则四舍五入到整数。）
            val dimenValue2: Int = it.getDimensionPixelSize(R.styleable.AttrTypes_dimenValue, 1)
            // 解析尺寸为像素值（如果有小数部分，则丢弃并截断到整数。）
            val dimenValue3: Int = it.getDimensionPixelOffset(R.styleable.AttrTypes_dimenValue, 1)
        }
    }

    /**
     * 示例六：比例类型属性的使用方法。
     *
     * 在本示例中，我们定义一些比例类型的XML属性，并在自定义View中解析它们。
     */
    private fun testFractionType(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.AttrTypes).use {
            // 解析比例
            val fractionValue1: Float = it.getFraction(R.styleable.AttrTypes_fractionValue1, 500, 1000, 1.0f)
            binding.tvFractionValue1.text = fractionValue1.toString()

            // 解析比例
            val fractionValue2: Float = it.getFraction(R.styleable.AttrTypes_fractionValue2, 500, 1000, 1.0f)
            binding.tvFractionValue2.text = fractionValue2.toString()
        }
    }

    /**
     * 示例七：颜色类型属性的使用方法。
     *
     * 在本示例中，我们定义一些颜色类型的XML属性，并在自定义View中解析它们。
     */
    private fun testColorType(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.AttrTypes).use {
            // 解析颜色属性的值为色值
            @ColorInt
            val colorValue: Int = it.getColor(R.styleable.AttrTypes_colorValue, Color.BLACK)
            binding.tvColorValue.setTextColor(colorValue)
        }
    }

    /**
     * 示例八：枚举类型属性的使用方法。
     *
     * 在本示例中，我们定义一些枚举类型的XML属性，并在自定义View中解析它们。
     */
    private fun testEnumType(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.AttrTypes).use {
            // 获取枚举对应的整型值
            val enumValue: Int = it.getInt(R.styleable.AttrTypes_enumValue, 0)
            binding.tvEnumValue.text = enumValue.toString()
        }
    }

    /**
     * 示例九：标志位属性的使用方法。
     *
     * 在本示例中，我们定义一些标志位类型的XML属性，并在自定义View中解析它们。
     */
    private fun testFlagType(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.AttrTypes).use {
            // 获取标志位组合对应的整型值
            val flags: Int = it.getInt(R.styleable.AttrTypes_flagValue, 0)
            binding.tvFlagValue.text = flags.toString()
        }
    }

    /**
     * 示例十：引用属性的使用方法。
     *
     * 在本示例中，我们定义一些引用类型的XML属性，并在自定义View中解析它们。
     */
    private fun testRefType(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.AttrTypes).use {
            // 将资源解析为Drawable
            val drawable: Drawable? = it.getDrawable(R.styleable.AttrTypes_refValue1)
            binding.ivRefType1.setImageDrawable(drawable)

            // 将资源解析为ColorStateList
            val colorStateList: ColorStateList? = it.getColorStateList(R.styleable.AttrTypes_refValue2)
            binding.cbRefType2.setTextColor(colorStateList)

            // 将资源解析为CharSequence数组
            val array: Array<CharSequence> = it.getTextArray(R.styleable.AttrTypes_refValue3)
            binding.tvRefType3.text = array.joinToString()
        }
    }

    /**
     * 示例十一：组合属性的使用方法。
     *
     * 在本示例中，我们定义一些支持多种类型的XML属性，并在自定义View中解析它们。
     */
    private fun testMultiTypes(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.AttrTypes).use {
            // 将资源解析为Drawable
            val drawable: Drawable? = it.getDrawable(R.styleable.AttrTypes_multiTypeValue)
            binding.ivMultiTypes.setImageDrawable(drawable)
        }
    }
}
