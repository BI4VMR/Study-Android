package net.bi4vmr.study.scaffold

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：全局属性。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIScaffold : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* 根据主题设置状态栏图标颜色 */
        val darkModeFlag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val darkMode = (darkModeFlag == Configuration.UI_MODE_NIGHT_YES)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !darkMode

        setContent {
            TestComposeTheme {
                // 顶层容器
                Scaffold(
                    topBar = { Text(text = "标题栏") },
                    floatingActionButton = { Text(text = "按钮") },
                    bottomBar = {
                        Text(text = "底栏")
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) { p ->
                    Text("内容")
                }
            }
        }
    }
}
