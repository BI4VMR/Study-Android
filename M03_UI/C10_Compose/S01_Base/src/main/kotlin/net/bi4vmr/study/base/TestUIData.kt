package net.bi4vmr.study.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.bi4vmr.study.base.theme.TestComposeTheme

/**
 * 测试界面：基本应用。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIData : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 显示Compose UI组件
        setContent {
            // 应用主题
            TestComposeTheme {
                // 应用布局
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 放置控件
                    TestUILogic(viewModel)
                }
            }
        }
    }

    // 有状态UI组件，包含具体的逻辑，依赖于ViewModel，不能复用。
    @Composable
    fun TestUILogic(viewModel: MyViewModel) {
        val s by viewModel.state.collectAsState()

        TestUI(
            s.toString(),
            c = { viewModel.add() }
        )
    }

    // 无状态UI组件，只暴露可变数据和回调方法，不包含具体的逻辑，可复用。
    @Composable
    fun TestUI(text: String, c: () -> Unit) {
        Column {
            Text("当前数值：$text")
            Button(onClick = c) {
                Text("数值增加")
            }
            Button(onClick = c) {
                Text("数值减少")
            }
        }
    }

    // 声明Compose组件预览
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TestComposeTheme {
            TestUI("Android", c = { println() })
        }
    }
}
