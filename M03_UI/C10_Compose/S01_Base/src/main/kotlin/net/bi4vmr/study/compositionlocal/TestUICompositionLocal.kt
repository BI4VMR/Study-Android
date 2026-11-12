package net.bi4vmr.study.compositionlocal

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：CompositionLocal。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUICompositionLocal : ComponentActivity() {

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
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .systemBarsPadding()
                    ) {
                        /* 变量值编辑 */
                        var message: String by remember { mutableStateOf("初始值") }
                        Text("修改 LocalString 的值：")
                        TextField(
                            value = message,
                            onValueChange = { message = it }
                        )


                        /* 测试组件 */
                        Parent(message) {
                            List {
                                Item()
                                Item()
                            }
                        }
                    }
                }
            }
        }
    }
}


// 定义 CompositionLocal 变量
val LocalString: ProvidableCompositionLocal<String> = compositionLocalOf { "默认值" }


@Composable
fun Parent(
    strParam: String,
    content: @Composable (ColumnScope.() -> Unit)
) {
    // 向作用域内注入 `LocalString` 的值，与 `strParam` 保持一致。
    CompositionLocalProvider(LocalString provides strParam) {
        Column { content() }
    }
}

@Composable
fun List(content: @Composable (ColumnScope.() -> Unit)) {
    Column { content() }
}

@Composable
fun Item() {
    // 使用 `<变量名>.current` 获取当前的变量值
    val s = LocalString.current
    Text("当前 LocalString 的值：$s")
}
