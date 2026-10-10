package net.bi4vmr.study.compatible

import android.content.res.Configuration
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import net.bi4vmr.study.databinding.TestuiComposeviewBinding

/**
 * 测试界面：在 View 中使用 ComposeView 。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIComposeView : AppCompatActivity() {

    private val binding: TestuiComposeviewBinding by lazy {
        TestuiComposeviewBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* 根据主题设置状态栏图标颜色 */
        val darkModeFlag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val darkMode = (darkModeFlag == Configuration.UI_MODE_NIGHT_YES)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !darkMode

        /*
         * Target SDK 为 Android 15 或更高版本时，系统强制启用 Edge-to-Edge 模式，且无法关闭。
         *
         * 此处添加 Insets 监听，将根布局内容绘制在状态栏和导航栏之间，避免重叠。
         */
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v: View, insets: WindowInsetsCompat ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, bars.top, 0, 0)
            WindowInsetsCompat.CONSUMED
        }
        ViewCompat.requestApplyInsets(binding.root)

        setContentView(binding.root)


        /* 在 View 中显示 Compose 组件 */
        binding.composeview.setContent {
            Text(text = "Hello Android!")
        }

        /* 在 Window 中显示 Compose 组件 */
        val composeView = ComposeView(this)

        // 设置 ComposeView 的生命周期管理器
        val lifecycleManager = WindowComposeLifecycleManager()
        composeView.apply {
            setViewTreeLifecycleOwner(lifecycleManager)
            setViewTreeViewModelStoreOwner(lifecycleManager)
            setViewTreeSavedStateRegistryOwner(lifecycleManager)
        }

        // 设置 Compose 内容
        composeView.setContent {
            Column(modifier = Modifier.background(Color.Magenta.copy(0.3F))) {
                // 重组功能测试
                var count by remember { mutableIntStateOf(0) }
                Button(onClick = { count += 1 }) {
                    Text("计数：$count")
                }

                // 关闭窗口
                Button(onClick = {
                    // 移除 ComposeView Window
                    windowManager.removeView(composeView)
                    // 更新生命周期状态，停止重组。
                    lifecycleManager.onPause()
                    lifecycleManager.onStop()
                }) {
                    Text("关闭窗口")
                }
            }
        }

        binding.btnOpenWindow.setOnClickListener {
            // 将 ComposeView 作为 Window 内容显示
            windowManager.addView(composeView, createLayoutParams())
            // 更新生命周期状态，使重组能够正常运行。
            lifecycleManager.onStart()
            lifecycleManager.onResume()
        }
    }

    private fun createLayoutParams(): WindowManager.LayoutParams {
        return WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            0,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = android.view.Gravity.CENTER
        }
    }
}
