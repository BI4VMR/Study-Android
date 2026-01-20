package net.bi4vmr.study.base

import android.content.res.Resources
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding
import kotlin.math.roundToInt

class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnGetDisplayInfo.setOnClickListener { testGetDisplayInfo() }
            btnUnitConversion.setOnClickListener { testUnitConversion() }
        }
    }

    // 获取屏幕信息
    private fun testGetDisplayInfo() {
        Log.i(TAG, "----- 获取屏幕信息 -----")
        binding.tvLog.append("\n----- 获取屏幕信息 -----")

        /* 单显示器环境 */
        val dm = Resources.getSystem().displayMetrics
        Log.i(TAG, "屏幕宽度：${dm.widthPixels}")
        Log.i(TAG, "屏幕高度：${dm.heightPixels}")
        Log.i(TAG, "像素密度：${dm.densityDpi}")
        Log.i(TAG, "缩放倍率(DP)：${dm.density}")
        Log.i(TAG, "缩放倍率(SP)：${dm.scaledDensity}")

        binding.tvLog.append("屏幕宽度：${dm.widthPixels}\n")
        binding.tvLog.append("屏幕高度：${dm.heightPixels}\n")
        binding.tvLog.append("像素密度：${dm.densityDpi}\n")
        binding.tvLog.append("缩放倍率(DP)：${dm.density}\n")
        binding.tvLog.append("缩放倍率(SP)：${dm.scaledDensity}\n")

        /* 多显示器环境 */
        // 创建一个空的DisplayMetrics对象
        // val dm = DisplayMetrics()
        // 使用当前Context所关联的显示器设置DisplayMetrics参数
        // context.display.getRealMetrics(dm)
    }

    private fun testUnitConversion() {
        Log.i(TAG, "----- 单位转换 -----")
        binding.tvLog.append("\n----- 单位转换 -----")

        Log.i(TAG, "100dp -> ?px: ${dpToPX(100F)}")
        Log.i(TAG, "100sp -> ?px: ${spToPX(100F)}")
        Log.i(TAG, "300px -> ?dp: ${pxToDP(300)}")
        Log.i(TAG, "300px -> ?sp: ${pxToSP(300)}")

        binding.tvLog.append("100dp -> ?px: ${dpToPX(100F)}\n")
        binding.tvLog.append("100sp -> ?px: ${spToPX(100F)}\n")
        binding.tvLog.append("300px -> ?dp: ${pxToDP(300)}\n")
        binding.tvLog.append("300px -> ?sp: ${pxToSP(300)}\n")
    }

    // 将DP转换为PX
    private fun dpToPX(dpValue: Float): Int {
        val dm: DisplayMetrics = Resources.getSystem().displayMetrics
        // "applyDimension()"方法用于将指定的单位转换为像素
        val rawValue: Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, dm)
        // 物理像素不可能为小数，因此保留整数部分即可。
        return rawValue.roundToInt()
    }

    // 将SP转换为PX
    private fun spToPX(spValue: Float): Int {
        val dm = Resources.getSystem().displayMetrics
        val rawValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, dm)
        return rawValue.roundToInt()
    }

    // 将PX转换为DP
    private fun pxToDP(pxValue: Int): Int {
        // 获取DP缩放倍率
        val density = Resources.getSystem().displayMetrics.density
        return (pxValue / density).roundToInt()
    }

    // 将PX转换为SP
    private fun pxToSP(pxValue: Int): Int {
        // 获取SP缩放倍率
        val density = Resources.getSystem().displayMetrics.scaledDensity
        return (pxValue / density).roundToInt()
    }
}
