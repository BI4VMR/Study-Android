package net.bi4vmr.study.skills

import android.os.Bundle
import android.os.SystemClock
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiSkillsBinding

/**
 * 测试界面：实用技巧。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUISkillsKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUISkillsKT::class.java.simpleName
    }

    private val binding: TestuiSkillsBinding by lazy {
        TestuiSkillsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()
        }

        testDebounceClick()
        testClickTenTimes()
    }

    // 防止高频点击
    private fun testDebounceClick() {
        /* 点击事件防抖 */
        // 记录按钮上次被点击的时间点
        var lastClickTS = 0L

        // 设置点击事件监听器
        binding.btnDebounceClick.setOnClickListener {
            // 当前时间点
            val currentTS = SystemClock.uptimeMillis()
            if (currentTS - lastClickTS < 1000L) {
                // 如果当前时间与上次点击的时间差值小于1秒，则认为是连续点击，不执行业务操作。
                Log.w(TAG, "连续点击过于频繁，忽略！")
                appendLog("连续点击过于频繁，忽略！")
            } else {
                // 如果当前时间与上次点击的时间差值达到1秒，更新时间记录，并执行业务操作。
                lastClickTS = currentTS
                // 业务操作：打印日志。
                Log.i(TAG, "点击间隔超过1秒，允许触发事件。")
                appendLog("点击间隔超过1秒，允许触发事件。")
            }
        }
    }

    // 连续点击触发事件
    private fun testClickTenTimes() {
        // 记录按钮被点击的时间点
        val clickRecords = LongArray(10)

        // 设置点击事件监听器
        binding.btnClickTenTimes.setOnClickListener {
            // 现有元素全部左移一位，舍弃最旧的一条记录。
            System.arraycopy(clickRecords, 1, clickRecords, 0, clickRecords.size - 1)
            // 将当前点击时间记录到数组末尾
            clickRecords[clickRecords.size - 1] = SystemClock.uptimeMillis()
            // 当前时间与最早一次的点击时间比较，如果差值小于5秒，则触发连点事件。
            if (SystemClock.uptimeMillis() - clickRecords[0] <= 5000L) {
                // 重置记录器
                clickRecords.fill(0L)

                // 业务操作：打印日志。
                Log.i(TAG, "5秒内已点击10次，允许触发事件。")
                appendLog("5秒内已点击10次，允许触发事件。")
            }
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private fun appendLog(text: Any) {
        binding.tvLog.apply {
            post { append("\n$text") }
            post {
                runCatching {
                    val offset = layout.getLineTop(lineCount) - height
                    if (offset > 0) {
                        scrollTo(0, offset)
                    }
                }.onFailure { e ->
                    Log.w(TAG, "TextView scroll failed!", e)
                }
            }
        }
    }
}
