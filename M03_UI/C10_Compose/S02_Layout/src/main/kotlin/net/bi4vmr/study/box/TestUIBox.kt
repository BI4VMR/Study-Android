package net.bi4vmr.study.box

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import net.bi4vmr.study.R
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：基本应用。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIBox : ComponentActivity() {

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
                    DefaultLayout()
                    Align()
                }
            }
        }
    }


    @Composable
    fun DefaultLayout() = Example("默认排列规则：") {
        Box(modifier = Modifier.fillMaxSize()) {
            // 子元素 A
            Text(
                text = "A",
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Blue)
            )
            // 子元素 B
            Text(
                text = "B",
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Cyan)
            )
            // 子元素 C
            Text(
                text = "C",
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Green)
            )
        }
    }

    @Composable
    fun Align() = Example("对齐规则：") {
        Box(
            // 全局规则：所有子元素都居中对齐。
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // 子元素 A
            Text(
                text = "A",
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Blue)
            )
            // 子元素 B
            Text(
                text = "B",
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Cyan)
            )
            // 子元素 C
            Text(
                text = "C",
                modifier = Modifier
                    .size(50.dp)
                    // 局部规则：对齐到父容器的右下角。
                    .align(Alignment.BottomEnd)
                    .background(Color.Green)
            )
        }
    }


    // 示例框架
    @Composable
    private inline fun Example(title: String, crossinline content: @Composable () -> Unit) {
        Column(Modifier.padding(10.dp)) {
            // 显示标题
            Text(text = title, color = colorResource(R.color.common_text))
            // 显示内容
            Box(
                // 为容器设置边框以便观察布局属性的效果
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(1.dp, Color.Blue)
            ) {
                content()
            }
        }
    }
}
