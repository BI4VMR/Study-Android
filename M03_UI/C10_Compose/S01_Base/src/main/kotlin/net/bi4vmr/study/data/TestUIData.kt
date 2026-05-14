package net.bi4vmr.study.data

import android.annotation.SuppressLint
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
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：数据交互。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIData : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 显示Compose UI组件
        setContent {
            // 应用主题
            TestComposeTheme {
                // 应用布局
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    // 放置控件
                    Counter(viewModel)
                }
            }
        }
    }

    // 有状态UI组件，包含具体的业务逻辑，依赖于ViewModel，不可复用。
    @Composable
    fun Counter(viewModel: MyViewModel) {
        // 将ViewModel中的Flow转换为Compose状态。
        val num: Int by viewModel.state.collectAsState()

        // 组装控件，传递数据和回调函数。
        TestUI(
            num,
            onAdd = { viewModel.add() },
            onReduce = { viewModel.reduce() }
        )
    }

    // 无状态UI组件，只暴露可变数据和回调函数，不包含具体的逻辑，可被复用。
    @Composable
    fun TestUI(value: Int, onAdd: () -> Unit, onReduce: () -> Unit) {
        Column {
            // 文本框：显示当前数值
            Text("当前数值：[$value]")
            // 增加按钮：被点击时执行 `onAdd()` 函数
            Button(onClick = onAdd) {
                Text("数值增加")
            }
            // 减少按钮：被点击时执行 `onReduce()` 函数
            Button(onClick = onReduce) {
                Text("数值减少")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TestUIPreview() {
        TestComposeTheme {
            TestUI(1, onAdd = { }, onReduce = { })
        }
    }
}
