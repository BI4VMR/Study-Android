package net.bi4vmr.study.modifier

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：全局属性。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIModifier : ComponentActivity() {

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
                    ColorBackground()
                    ShapeBackground()
                    GradientBackground()
                }
            }
        }
    }


    @Composable
    fun FixSize() = Example("固定尺寸") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            // 固定尺寸：100dp x 100dp
            Modifier
                .size(100.dp)
                .background(Color.Gray)
        )
    }

    // 纯色背景
    @Composable
    fun ColorBackground() {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier.background(Color.Gray)
        )
    }


    // 形状背景
    @Composable
    fun ShapeBackground() {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier.background(Color.Gray, RoundedCornerShape(16.dp))
        )
    }


    // 渐变背景
    @Composable
    fun GradientBackground() {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier.background(Brush.linearGradient(listOf(Color.Red, Color.Green, Color.Blue)))
        )
    }


    // 示例框架
    @Composable
    private inline fun Example(title: String, crossinline content: @Composable () -> Unit) {
        Column(Modifier.padding(10.dp)) {
            // 显示标题
            Text(text = title)
            // 显示内容
            Box(
                // 为容器设置边框以便观察布局属性的效果
                Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .border(1.dp, Color.Blue)
            ) {
                content()
            }
        }
    }
}
