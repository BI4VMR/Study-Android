package net.bi4vmr.study.theme

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import net.bi4vmr.study.common.LocalMyTextColors
import net.bi4vmr.study.common.MyCustomTheme

/**
 * 测试界面：主题切换。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUICustomTheme : ComponentActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUITheme::class.java.simpleName}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* 根据主题设置状态栏图标颜色 */
        val darkModeFlag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val darkMode = (darkModeFlag == Configuration.UI_MODE_NIGHT_YES)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !darkMode

        setContent {
            // 深色模式配置项
            var darkMode: Boolean by remember { mutableStateOf(false) }

            MyCustomTheme(darkMode) {
                Surface {
                    // 顶层容器
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .systemBarsPadding()
                    ) {
                        /* 深色模式设置 */
                        Text(text = "深色模式设置：")
                        Row {
                            RadioButton(
                                selected = (!darkMode),
                                onClick = { darkMode = false },
                            )
                            Text(text = "浅色")
                        }
                        Row {
                            RadioButton(
                                selected = (darkMode),
                                onClick = { darkMode = true },
                            )
                            Text(text = "深色")
                        }


                        /* 示例控件 */
                        Text(text = "示例控件：")

                        // 使用原始方式访问
                        Text(text = "警告消息", color = LocalMyTextColors.current.warning)
                        // 使用单例对象访问
                        Text(text = "错误消息", color = MyCustomTheme.textColors.error)
                    }
                }
            }
        }
    }
}
