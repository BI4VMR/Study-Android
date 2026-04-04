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
import net.bi4vmr.study.databinding.AttrTestViewBinding

/**
 * 自定义控件：属性类型测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@SuppressLint("SetTextI18n", "CustomViewStyleable")
class AttrTestViewKT @JvmOverloads constructor(
    @param:UiContext private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.attrInTheme,
    defStyleRes: Int = R.style.AttrTestDefaultStyle
) : FrameLayout(mContext, attrs, defStyleAttr, defStyleRes) {

    companion object {
        private val TAG: String = "TestApp-${AttrTestViewKT::class.java.simpleName}"
    }

    private val binding: AttrTestViewBinding by lazy {
        // 将布局文件渲染生成View实例
        AttrTestViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        testBaseType(attrs)
        testTextType(attrs)
        testDimenType(attrs)
        testFractionType(attrs)
        testColorType(attrs)
        testEnumType(attrs)
        testFlagType(attrs)
        testRefType(attrs)
        testMultiTypes(attrs)

        testInitParams(attrs, defStyleAttr, defStyleRes)
    }

    /**
     * 示例三：基本数据类型属性的使用方法。
     *
     * 在本示例中，我们定义一些基本数据类型的XML属性，并在自定义View中解析它们。
     */
    private fun testBaseType(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AttrTypes)
        try {
            // 获取布尔值
            val booleanValue: Boolean = ta.getBoolean(R.styleable.AttrTypes_booleanValue, false)
            binding.tvBooleanValue.text = booleanValue.toString()

            // 获取整型值
            val intValue: Int = ta.getInt(R.styleable.AttrTypes_integerValue, 0)
            binding.tvIntegerValue.text = intValue.toString()

            // 获取浮点值
            val floatValue: Float = ta.getFloat(R.styleable.AttrTypes_floatValue, 0.0F)
            binding.tvFloatValue.text = floatValue.toString()
        } finally {
            ta.recycle()
        }
    }

    /**
     * 示例四：文本类型属性的使用方法。
     *
     * 在本示例中，我们定义一些文本类型的XML属性，并在自定义View中解析它们。
     */
    private fun testTextType(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AttrTypes)
        try {
            // 获取文本（忽略格式）
            val textValue1: String? = ta.getString(R.styleable.AttrTypes_textValue1)
            binding.tvTextValue1.text = textValue1

            // 获取文本（解析格式）
            val textValue2: CharSequence? = ta.getText(R.styleable.AttrTypes_textValue2)
            binding.tvTextValue2.text = textValue2
        } finally {
            ta.recycle()
        }
    }

    /**
     * 示例五：尺寸类型属性的使用方法。
     *
     * 在本示例中，我们定义一些尺寸类型的XML属性，并在自定义View中解析它们。
     */
    private fun testDimenType(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AttrTypes)
        try {
            // 解析尺寸为像素值
            val dimenValue1: Float = ta.getDimension(R.styleable.AttrTypes_dimenValue, 1.0F)
            binding.tvDimenValue.text = dimenValue1.toString()

            // 解析尺寸为像素值（如果有小数部分，则四舍五入到整数。）
            val dimenValue2: Int = ta.getDimensionPixelSize(R.styleable.AttrTypes_dimenValue, 1)
            // 解析尺寸为像素值（如果有小数部分，则丢弃并截断到整数。）
            val dimenValue3: Int = ta.getDimensionPixelOffset(R.styleable.AttrTypes_dimenValue, 1)
        } finally {
            ta.recycle()
        }
    }

    /**
     * 示例六：比例类型属性的使用方法。
     *
     * 在本示例中，我们定义一些比例类型的XML属性，并在自定义View中解析它们。
     */
    private fun testFractionType(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AttrTypes)
        try {
            // 解析比例
            val fractionValue1: Float = ta.getFraction(R.styleable.AttrTypes_fractionValue1, 500, 1000, 1.0f)
            binding.tvFractionValue1.text = fractionValue1.toString()

            // 解析比例
            val fractionValue2: Float = ta.getFraction(R.styleable.AttrTypes_fractionValue2, 500, 1000, 1.0f)
            binding.tvFractionValue2.text = fractionValue2.toString()
        } finally {
            ta.recycle()
        }
    }

    /**
     * 示例七：颜色类型属性的使用方法。
     *
     * 在本示例中，我们定义一些颜色类型的XML属性，并在自定义View中解析它们。
     */
    private fun testColorType(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AttrTypes)
        try {
            // 解析颜色属性的值为色值
            @ColorInt
            val colorValue: Int = ta.getColor(R.styleable.AttrTypes_colorValue, Color.BLACK)
            binding.tvColorValue.setTextColor(colorValue)
        } finally {
            ta.recycle()
        }
    }

    /**
     * 示例八：枚举类型属性的使用方法。
     *
     * 在本示例中，我们定义一些枚举类型的XML属性，并在自定义View中解析它们。
     */
    private fun testEnumType(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AttrTypes)
        try {
            // 获取枚举对应的整型值
            val enumValue: Int = ta.getInt(R.styleable.AttrTypes_enumValue, 0)
            binding.tvEnumValue.text = enumValue.toString()
        } finally {
            ta.recycle()
        }
    }

    /**
     * 示例九：标志位属性的使用方法。
     *
     * 在本示例中，我们定义一些标志位类型的XML属性，并在自定义View中解析它们。
     */
    private fun testFlagType(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AttrTypes)
        try {
            // 获取标志位组合对应的整型值
            val flags: Int = ta.getInt(R.styleable.AttrTypes_flagValue, 0)
            binding.tvFlagValue.text = flags.toString()
        } finally {
            ta.recycle()
        }
    }

    /**
     * 示例十：引用属性的使用方法。
     *
     * 在本示例中，我们定义一些引用类型的XML属性，并在自定义View中解析它们。
     */
    private fun testRefType(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AttrTypes)
        try {
            // 将资源解析为Drawable
            val drawable: Drawable? = ta.getDrawable(R.styleable.AttrTypes_refValue1)
            binding.ivRefType1.setImageDrawable(drawable)

            // 将资源解析为ColorStateList
            val colorStateList: ColorStateList? = ta.getColorStateList(R.styleable.AttrTypes_refValue2)
            binding.cbRefType2.setTextColor(colorStateList)

            // 将资源解析为CharSequence数组
            val array: Array<CharSequence> = ta.getTextArray(R.styleable.AttrTypes_refValue3)
            binding.tvRefType3.text = array.joinToString()
        } finally {
            ta.recycle()
        }
    }

    /**
     * 示例十一：组合属性的使用方法。
     *
     * 在本示例中，我们定义一些支持多种类型的XML属性，并在自定义View中解析它们。
     */
    private fun testMultiTypes(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AttrTypes)
        try {
            // 将资源解析为Drawable
            val drawable: Drawable? = ta.getDrawable(R.styleable.AttrTypes_multiTypeValue)
            binding.ivMultiTypes.setImageDrawable(drawable)
        } finally {
            ta.recycle()
        }
    }

    /**
     * 示例十二：主题支持。
     * <p>
     * 在本示例中，我们对测试View的构造方法进行改造，使其外观能够跟随主题变化。
     */
    private fun testInitParams(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AttrTypes, defStyleAttr, defStyleRes)
        try {
            val text1 = ta.getString(R.styleable.AttrTypes_initParam1)
            binding.tvInitParam1.text = text1
            val text2 = ta.getString(R.styleable.AttrTypes_initParam2)
            binding.tvInitParam2.text = text2
        } finally {
            ta.recycle()
        }
    }
}
