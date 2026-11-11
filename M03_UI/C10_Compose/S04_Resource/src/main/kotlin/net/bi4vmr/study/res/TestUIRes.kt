package net.bi4vmr.study.res

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import net.bi4vmr.study.R
import net.bi4vmr.study.common.Purple_500

/**
 * 测试界面：主题切换。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIRes : ComponentActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIRes::class.java.simpleName}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* 根据主题设置状态栏图标颜色 */
        val darkModeFlag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val darkMode = (darkModeFlag == Configuration.UI_MODE_NIGHT_YES)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !darkMode

        setContent {
            // 顶层容器
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .systemBarsPadding()
            ) {
                TestText()
                TestTextPlaceHolder()
                TestTextPlurals()
                TestDimen()
                TestColor()
                TestColor2()
            }
        }
    }

    @Composable
    fun TestText() = Example("普通文本") {
        Text(text = stringResource(R.string.text_test))
    }

    @Composable
    fun TestTextPlaceHolder() = Example("占位符") {
        Text(text = stringResource(R.string.text_template, "李田所", 24))
    }

    @Composable
    fun TestTextPlurals() = Example("复数") {
        Text(text = pluralStringResource(R.plurals.apple_info, 3, 10))
    }

    @Composable
    fun TestDimen() = Example("尺寸") {
        Text(
            text = "Hello World!",
            modifier = Modifier.padding(dimensionResource(R.dimen.common_space))
        )
    }

    @Composable
    fun TestColor() = Example("颜色") {
        Text(text = "Hello World!", color = colorResource(R.color.purple_500))
    }

    @Composable
    fun TestColor2() = Example("颜色（在代码中定义）") {
        Text(text = "Hello World!", color = Purple_500)
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
                    .height(100.dp)
                    .border(1.dp, Color.Blue)
            ) {
                content()
            }
        }
    }
}
