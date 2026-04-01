package net.bi4vmr.study.skills

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiSkillsBinding

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

        /* 点击事件防抖 */
        var lastClickTS = 0L
        binding.btnDebounceClick.setOnClickListener {
            val currentTS = SystemClock.uptimeMillis()
            if (currentTS - lastClickTS < 1000L) {
                Log.w(TAG, "Click too fast, ignore!")
                return@setOnClickListener
            }

            // action
            Log.d(TAG, "Click action performed.")
            lastClickTS = currentTS
        }

        /* 连续点击触发 */
        val clickRecords = LongArray(10)
        binding.btnClickTenTimes.setOnClickListener {
            // 所有现有数据左移一位，舍弃最旧的一位数据。
            System.arraycopy(clickRecords, 1, clickRecords, 0, clickRecords.size - 1)
            // 将当前点击时间记录到数组末尾
            clickRecords[clickRecords.size - 1] = SystemClock.uptimeMillis()
            // 当前时间与最早一次的点击时间比较，如果差值小于5秒，则触发连点事件。
            if (SystemClock.uptimeMillis() - clickRecords[0] <= 5000L) {
                // action
                Log.d(TAG, "Repeat click 10 times in 5 seconds, ")
                // 事件已经触发，重置记录器。
                clickRecords.fill(0L)
            }
        }
    }
}
