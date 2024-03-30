package net.bi4vmr.study.config

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiConfigBinding

class TestUIConfigKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUIConfigKT::class.java.simpleName
    }

    private val binding: TestuiConfigBinding by lazy {
        TestuiConfigBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "OnCreate.")
        setContentView(binding.root)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i(TAG, "OnConfigurationChanged.")

        // 从标志位中分离出主题代码
        when (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            /* 浅色模式（默认） */
            Configuration.UI_MODE_NIGHT_NO -> {
                Log.i(TAG, "OnConfigurationChanged. NightMode OFF.")
                binding.tvInfo.setTextColor(Color.BLACK)
                binding.root.setBackgroundColor(Color.WHITE)
            }
            /* 深色模式 */
            Configuration.UI_MODE_NIGHT_YES -> {
                Log.i(TAG, "OnConfigurationChanged. NightMode ON.")
                binding.tvInfo.setTextColor(Color.WHITE)
                binding.root.setBackgroundColor(Color.BLACK)
            }
        }
    }
}
