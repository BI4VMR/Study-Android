package net.bi4vmr.study.state

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：数据交互。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIState : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                // 放置 Compose 组件
                Counter()
            }
        }
    }

    // 有状态组件，包含具体的业务逻辑，依赖于特定的 ViewModel ，不可跨工程复用。
    @Composable
    fun Counter() {
        // 声明状态：表示当前数量
        var count: Int by remember { mutableIntStateOf(0) }

        Text("计数器")
        // 组装控件，传递数据并处理回调函数。
        CounterStateless(
            count,
            onAdd = { count++ },
            onReduce = { count-- }
        )
    }

    // 无状态组件，只暴露可变数据和回调函数，不包含具体的逻辑，可被跨工程复用。
    @Composable
    fun CounterStateless(value: Int, onAdd: () -> Unit, onReduce: () -> Unit) {
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
    fun CounterStatelessPreview() {
        TestComposeTheme {
            CounterStateless(1, onAdd = { }, onReduce = { })
        }
    }
}