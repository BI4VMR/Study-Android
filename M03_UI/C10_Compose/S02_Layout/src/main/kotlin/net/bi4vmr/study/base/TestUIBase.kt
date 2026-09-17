package net.bi4vmr.study.base

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
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
 * 测试界面： Column / Row 。
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

        setContent {
            TestComposeTheme {
                // 顶层容器
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .systemBarsPadding()
                ) {
                    Column()
                    Row()
                    Align()
                    Weight()
                    WeightFill()
                }
            }
        }
    }


    @Composable
    fun Column() = Example("列容器：") {
        Column {
            // 子元素 A
            Text(
                text = "A",
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Cyan)
            )
            // 子元素 B
            Text(
                text = "B",
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .background(Color.Red)
            )
            // 子元素 C
            Text(
                text = "C",
                modifier = Modifier
                    .width(50.dp)
                    .height(75.dp)
                    .background(Color.Yellow)
            )
        }
    }

    @Composable
    fun Row() = Example("行容器：") {
        Row {
            // 子元素 A
            Text(
                text = "A",
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Cyan)
            )
            // 子元素 B
            Text(
                text = "B",
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .background(Color.Red)
            )
            // 子元素 C
            Text(
                text = "C",
                modifier = Modifier
                    .width(50.dp)
                    .height(75.dp)
                    .background(Color.Yellow)
            )
        }
    }

    @Composable
    fun Align() = Example("对齐规则：") {
        Row(
            // 主要轴对齐方式
            horizontalArrangement = Arrangement.End,
            // 辅助轴对齐方式
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "A",
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.Cyan)
            )
            Text(
                text = "B",
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .background(Color.Red)
            )
            Text(
                text = "C",
                modifier = Modifier
                    .width(50.dp)
                    .height(75.dp)
                    .background(Color.Yellow)
            )
        }
    }

    @Composable
    fun Weight() = Example("比例布局：") {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "A",
                modifier = Modifier
                    .weight(0.7F)
                    .background(Color.Green)
            )
            Text(
                text = "B",
                modifier = Modifier
                    .weight(0.3F)
                    .background(Color.Magenta)
            )
        }
    }

    @Composable
    fun WeightFill() = Example("填充剩余空间：") {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "A",
                modifier = Modifier
                    .background(Color.Blue)
            )
            Text(
                text = "B",
                modifier = Modifier
                    .weight(1F)
                    .background(Color.Yellow)
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
