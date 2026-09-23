package net.bi4vmr.study.skill

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import net.bi4vmr.study.R
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：组件可见性。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIVisibility : ComponentActivity() {

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
                    NoData()
                    HasData()

                    ShowIcon()
                    HideIcon()
                }
            }
        }
    }

    // 组件：新闻列表
    @Composable
    fun News(datas: List<String>) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(alpha = 0.2F))
        ) {
            // 当数据列表为空时，显示提示文本；当列表非空时，显示内容。
            if (datas.isEmpty()) {
                Text(text = "暂无新闻。")
            } else {
                datas.forEach { title ->
                    Text(text = title)
                }
            }
        }
    }

    @Composable
    fun NoData() = Example("无数据：") {
        News(emptyList())
    }

    @Composable
    fun HasData() = Example("有数据：") {
        val newsList = listOf("标题 1", "标题 2", "标题 3", "标题 N")
        News(newsList)
    }

    // 组件：状态指示灯
    @Composable
    fun Lamps(show: Boolean) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(android.R.drawable.ic_dialog_alert), contentDescription = null)
            Icon(
                painter = painterResource(android.R.drawable.ic_delete),
                contentDescription = null,
                // 无需显示时，将不透明度设为 `0` ，组件不可见但仍参与布局占据空间。
                modifier = Modifier.alpha(if (show) 1.0F else 0F)
            )
            Icon(painter = painterResource(android.R.drawable.ic_menu_help), contentDescription = null)
        }
    }

    @Composable
    fun ShowIcon() = Example("显示图标：") {
        Lamps(true)
    }

    @Composable
    fun HideIcon() = Example("隐藏图标：") {
        Lamps(false)
    }


    // 组件：示例框架
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
