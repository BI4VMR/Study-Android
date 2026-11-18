@file:SuppressLint("SetTextI18n")

package net.bi4vmr.study.shadow

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：在 Compose 中使用 AndroidView 。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIBaseShadow : ComponentActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseShadow::class.java.simpleName}"
    }


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
                    TestMaterialShadow()
                }
            }
        }
    }

    @Composable
    fun ElevationBasedShadow() {
        Box(
            modifier = Modifier
                // .aspectRatio(1f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .size(100.dp, 100.dp)
                    .shadow(
                        10.dp,
                        RectangleShape,
                        ambientColor = Color(0x003B82F6),
                        spotColor = Color(0xFF3B82F6)
                    )
                    .background(Color.White)
            )
        }
    }


    @Composable
    fun TestMaterialShadow() = Example("基本阴影") {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(32.dp)
        ) {
            Box(
                Modifier
                    .size(75.dp, 75.dp)
                    .shadow(
                        10.dp,
                        RectangleShape,
                        ambientColor = Color(0xFF3B82F6),
                        spotColor = Color(0xFF3B82F6)
                    )
                    .background(Color.White)
            )

            Spacer(Modifier.width(32.dp))

            Box(
                Modifier
                    .size(75.dp, 75.dp)
                    .shadow(
                        30.dp,
                        RectangleShape,
                        ambientColor = Color(0xFFFF0000),
                        spotColor = Color(0xFFFF0000)
                    )
                    .background(Color.White)
            )
        }
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
