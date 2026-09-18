package net.bi4vmr.study.modifier

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
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
                // 顶层容器
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .systemBarsPadding()
                ) {
                    Default()
                    FixSize()
                    ParentFraction()
                    MinSize()
                    MaxSize()

                    Padding()
                    Order()

                    Optional()
                }
            }
        }
    }


    @Composable
    fun Default() = Example("尺寸约束 - 默认规则：") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            // 不指定尺寸，默认以内容尺寸为准。
            modifier = Modifier.background(Color.Gray)
        )
    }

    @Composable
    fun FixSize() = Example("尺寸约束 - 固定尺寸：") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier
                // 固定宽度：50 dp
                .width(50.dp)
                .background(Color.Gray)
        )
    }

    @Composable
    fun ParentFraction() = Example("尺寸约束 - 比例约束：") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier
                // 当前元素的宽高都跟随父容器
                .fillMaxSize()
                .background(Color.Gray)
        )
    }

    @Composable
    fun MinSize() = Example("尺寸约束 - 最小尺寸：") {
        Text(
            text = "Hello!",
            modifier = Modifier
                // 最小宽高为 120 dp ，当内容尺寸小于 120 dp 时，仍会占用 120 dp 的空间。
                .defaultMinSize(120.dp, 120.dp)
                .background(Color.Gray)
        )
    }

    @Composable
    fun MaxSize() = Example("尺寸约束 - 最大尺寸：") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier
                // 最大宽度为 50 dp ，最大高度为未指定。
                .sizeIn(maxWidth = 50.dp, maxHeight = Dp.Unspecified)
                .background(Color.Gray)
        )
    }

    @Composable
    fun Padding() = Example("边距：") {
        Text(
            text = "我能吞下玻璃而不伤身体。The quick brown fox jumps over the lazy dog.",
            modifier = Modifier
                .padding(50.dp)
                .background(Color.Gray)
        )
    }

    @Composable
    fun Order() = Example("配置顺序：") {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier
                .background(Color.Gray)
                .padding(25.dp)
                .background(Color.Cyan)
        )
    }

    @Composable
    fun Optional() = Example("可选属性：") {
        Row {
            Tab("可点击表项", allowClick = true)
            Tab("不可点击表项", allowClick = false)
        }
    }

    @Composable
    fun Tab(
        title: String,
        modifier: Modifier = Modifier,
        allowClick: Boolean = true
    ) {
        val context = LocalContext.current
        // 组件的根布局，继承外部传入的 Modifier 。
        Column(
            modifier = modifier
                .background(Color.Magenta.copy(0.3F), RoundedCornerShape(16.dp))
                // 可选属性，根据 `allowClick` 决定是否添加点击事件。
                .then(
                    if (allowClick) Modifier.clickable {
                        Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
                    }
                    else Modifier
                )
                .padding(8.dp)
        ) {
            Text(title)
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
                    .height(150.dp)
                    .border(1.dp, Color.Blue)
            ) {
                content()
            }
        }
    }
}
