package net.bi4vmr.study.base

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：基本应用。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIBase : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* 根据主题设置状态栏图标颜色 */
        val darkModeFlag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val darkMode = (darkModeFlag == Configuration.UI_MODE_NIGHT_YES)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !darkMode

        // 显示Compose UI组件
        setContent {
            // 应用主题
            TestComposeTheme {
                // 应用布局
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 放置控件
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    // 声明Compose组件
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        // 声明文本框
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    // 声明Compose组件预览
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TestComposeTheme {
            Greeting("Android")
        }
    }
}
