package net.bi4vmr.study.theme

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
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
import net.bi4vmr.study.common.MyTheme

/**
 * 测试界面：主题切换。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUITheme : ComponentActivity() {

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
            var darkModeConfig: DarkMode by remember { mutableStateOf(DarkMode.AUTO) }
            // 是否启用深色模式
            val darkModeState = when (darkModeConfig) {
                DarkMode.AUTO -> isSystemInDarkTheme()
                DarkMode.LIGHT -> false
                DarkMode.DARK -> true
            }

            MyTheme(darkModeState) {
                Surface {
                    // 顶层容器
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .systemBarsPadding()
                    ) {
                        Row {
                            RadioButton(
                                selected = (darkModeConfig == DarkMode.AUTO),
                                onClick = { darkModeConfig = DarkMode.AUTO },
                            )
                            Text(text = "跟随系统")
                        }
                        Row {
                            RadioButton(
                                selected = (darkModeConfig == DarkMode.LIGHT),
                                onClick = { darkModeConfig = DarkMode.LIGHT },
                            )
                            Text(text = "浅色")
                        }
                        Row {
                            RadioButton(
                                selected = (darkModeConfig == DarkMode.DARK),
                                onClick = { darkModeConfig = DarkMode.DARK },
                            )
                            Text(text = "深色")
                        }
                    }
                }
            }
        }
    }
}


/**
 * 深色模式配置。
 */
enum class DarkMode {

    /**
     * 跟随系统设置。
     */
    AUTO,

    /**
     * 浅色。
     */
    LIGHT,

    /**
     * 深色。
     */
    DARK;
}
