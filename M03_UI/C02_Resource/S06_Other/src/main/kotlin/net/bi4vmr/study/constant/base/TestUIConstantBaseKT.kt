package net.bi4vmr.study.constant.base

import android.annotation.SuppressLint
import android.content.res.TypedArray
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiConstantBaseBinding

/**
 * 测试界面：常量 - 基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@SuppressLint("SetTextI18n")
class TestUIConstantBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIConstantBaseKT::class.java.simpleName}"
    }

    private val binding: TestuiConstantBaseBinding by lazy {
        TestuiConstantBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        testConstant()
        testConstantArray()
        testResArray()
    }

    /**
     * 示例一：常量资源的简单应用。
     *
     * 在本示例中，我们定义一些常量资源，然后使用这些资源。
     */
    private fun testConstant() {
        // 将布尔常量资源解析为布尔值
        val booleanValue: Boolean = resources.getBoolean(R.bool.booleanValue)

        // 将布尔常量资源解析为布尔值
        val intValue: Int = resources.getInteger(R.integer.intValue)

        // 将文本常量资源解析为字符串（忽略格式）
        val stringValue1: String = resources.getString(R.string.stringValue)
        // 将文本常量资源解析为字符序列（解析格式）
        val stringValue2: CharSequence = resources.getText(R.string.stringValue)

        binding.tvBoolean.text = booleanValue.toString()
        binding.tvInt.text = intValue.toString()
        binding.tvStringRaw.text = stringValue1
        binding.tvCharSequence.text = stringValue2
    }

    /**
     * 示例二：常量数组资源的简单应用。
     *
     * 在本示例中，我们定义一些常量数组资源，并使用这些资源。
     */
    private fun testConstantArray() {
        // 获取整数数组
        val intArray: IntArray = resources.getIntArray(R.array.intArray)

        // 获取字符串数组（忽略格式）
        val stringArray1: Array<String> = resources.getStringArray(R.array.stringArray)
        // 获取字符串数组（解析格式）
        val stringArray2: Array<CharSequence> = resources.getTextArray(R.array.stringArray)

        binding.tvIntArray.text = intArray.joinToString()
        binding.tvStringArray.text = stringArray1.joinToString()
        binding.tvTextArray.text = TextUtils.concat(*stringArray2)
    }

    /**
     * 示例三：资源数组的简单应用。
     *
     * 在本示例中，我们定义一些资源数组，并使用这些资源。
     */
    private fun testResArray() {
        // 获取资源数组
        val colorResArray: TypedArray = resources.obtainTypedArray(R.array.colorResArray)
        // 遍历数组获取每个颜色资源
        for (i in 0 until colorResArray.length()) {
            // 使用索引获取单个资源
            val id = colorResArray.getResourceId(i, 0)
            if (id == 0) {
                return
            }

            // 将资源ID解析为色值
            val color = ContextCompat.getColor(this, id)
            // 使用颜色值
            val tv = TextView(this)
            tv.text = " $i "
            tv.textSize = 32.0F
            tv.setBackgroundColor(color)
            binding.container.addView(tv)
        }

        // 释放资源数组
        colorResArray.recycle()
    }
}
