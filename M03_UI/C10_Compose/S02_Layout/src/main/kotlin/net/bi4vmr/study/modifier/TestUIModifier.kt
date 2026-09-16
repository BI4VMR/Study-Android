package net.bi4vmr.study.modifier

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import net.bi4vmr.study.R
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
                Column(
                    Modifier
                        .systemBarsPadding()
                        .verticalScroll(rememberScrollState())
                ) {
                    Default()
                    FixSize()
                    MatchParent()
                    MinSize()
                    MaxSize()

                    Padding()
                    Order()
                }
            }
        }
    }


    // 尺寸约束：默认约束
    @Composable
    fun Default() = Example("尺寸约束 - 默认约束") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            // 不指定尺寸，默认以内容尺寸为准。
            Modifier.background(Color.Gray)
        )
    }

    // 尺寸约束：固定尺寸
    @Composable
    fun FixSize() = Example("尺寸约束 - 固定尺寸") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            // 固定尺寸：100dp x 100dp
            Modifier
                .size(100.dp)
                .background(Color.Gray)
        )
    }


    // 尺寸约束：跟随父容器
    @Composable
    fun MatchParent() = Example("尺寸约束 - 跟随父容器") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        )
    }

    // 尺寸约束：最小尺寸限制
    @Composable
    fun MinSize() = Example("尺寸约束 - 最小尺寸限制") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier
                .defaultMinSize(150.dp, 150.dp)
                .background(Color.Gray)
        )
    }

    // 尺寸约束：最大尺寸限制
    @Composable
    fun MaxSize() = Example("尺寸约束 - 最大尺寸限制") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier
                .sizeIn(maxWidth = 50.dp, maxHeight = 50.dp)
                .background(Color.Gray)
        )
    }


    // 边距
    @Composable
    fun Padding() = Example("边距") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier
                .padding(50.dp)
                .background(Color.Gray)
        )
    }


    // 配置顺序
    @Composable
    fun Order() = Example("配置顺序") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier
                .background(Color.Gray)
                .padding(25.dp)
                .background(Color.Cyan)
        )
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
                    .height(150.dp)
                    .border(1.dp, Color.Blue)
            ) {
                content()
            }
        }
    }
}
