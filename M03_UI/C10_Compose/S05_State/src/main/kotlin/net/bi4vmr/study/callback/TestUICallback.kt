package net.bi4vmr.study.callback

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import kotlinx.coroutines.delay
import net.bi4vmr.study.base.common.TestComposeTheme
import kotlin.time.Duration.Companion.seconds

/**
 * 测试界面：`rememberUpdatedState()`。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUICallback : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* 根据主题设置状态栏图标颜色 */
        val darkModeFlag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val darkMode = (darkModeFlag == Configuration.UI_MODE_NIGHT_YES)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !darkMode

        setContent {
            TestComposeTheme {
                Surface {
                    // 顶层容器
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .systemBarsPadding()
                    ) {
                        // 语言选择器
                        var language: String by remember { mutableStateOf("中文") }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("中文")
                            Switch(
                                checked = (language == "英文"),
                                onCheckedChange = {
                                    language = if (it) "英文" else "中文"
                                }
                            )
                            Text("英文")
                        }

                        // 测试组件（错误示范）
                        // TestScope(language = language)
                        // 测试组件（正确示范）
                        TestScope2(language = language)
                    }
                }
            }
        }
    }

    // 计时器（错误示范）
    @Composable
    fun Timer(
        seconds: Int,
        onTimeout: () -> Unit
    ) {
        var currentSecond: Int by remember { mutableIntStateOf(0) }

        LaunchedEffect(Unit) {
            // 循环计时和更新界面
            while (currentSecond < seconds) {
                delay(1.seconds)
                currentSecond++
            }
            // 计时结束，触发回调。
            onTimeout()
        }

        Text("已经过的秒数：[$currentSecond]")
    }

    @Composable
    fun TestScope(language: String) {
        Timer(5) {
            // 计时结束回调
            Toast.makeText(
                this@TestUICallback,
                "当前选择的语言：$language",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    // 计时器（正确示范）
    @Composable
    fun Timer2(
        seconds: Int,
        onTimeout: () -> Unit
    ) {
        var currentSecond: Int by remember { mutableIntStateOf(0) }
        // 使用 `rememberUpdatedState()` 获取最新的 `onTimeout` 回调
        val currentCallback by rememberUpdatedState(onTimeout)

        LaunchedEffect(Unit) {
            // 循环计时和更新界面
            while (currentSecond < seconds) {
                delay(1.seconds)
                currentSecond++
            }
            // 计时结束，触发回调。
            currentCallback()
        }

        Text("已经过的秒数：[$currentSecond]")
    }

    @Composable
    fun TestScope2(language: String) {
        Timer2(5) {
            // 计时结束回调
            Toast.makeText(
                this@TestUICallback,
                "当前选择的语言：$language",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
