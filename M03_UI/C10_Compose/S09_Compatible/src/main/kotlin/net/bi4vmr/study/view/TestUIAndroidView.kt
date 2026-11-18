@file:SuppressLint("SetTextI18n")

package net.bi4vmr.study.compatible

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import com.google.android.material.button.MaterialButton
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：在 Compose 中使用 AndroidView 。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIAndroidView : ComponentActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIAndroidView::class.java.simpleName}"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* 根据主题设置状态栏图标颜色 */
        val darkModeFlag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val darkMode = (darkModeFlag == Configuration.UI_MODE_NIGHT_YES)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !darkMode

        setContent {
            TestComposeTheme {
                // 顶层容器
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .systemBarsPadding()
                ) {
                    var count by remember { mutableIntStateOf(0) }

                    // 在 Compose 中绘制原生 View 组件
                    AndroidView(
                        modifier = Modifier.padding(10.dp),
                        // 初始化回调，仅在首次创建 View 时触发一次。
                        factory = { context ->
                            Log.i(TAG, "AndroidView `factory()` callbacked.")
                            // 创建 View 实例
                            MaterialButton(context).apply {
                                // 设置文本样式
                                textSize = 20F
                                // 设置点击事件，更新 Compose 状态变量。
                                setOnClickListener { count += 1 }
                            }
                        },
                        // 状态更新回调，可选，首次创建时触发一次，关联状态变化时按需触发。
                        update = { view ->
                            Log.i(TAG, "AndroidView `update()` callbacked.")
                            // 此处引用了 Compose 状态，该状态更新时 Compose 运行环境将会触发本回调方法。
                            view.text = "计数：$count"
                        },
                        // 销毁回调，可选，节点从组合退出时触发一次。
                        onRelease = {
                            Log.i(TAG, "AndroidView `onRelease()` callbacked.")
                        }
                    )
                }
            }
        }
    }
}
