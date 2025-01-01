package net.bi4vmr.study.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import net.bi4vmr.study.base.theme.TestComposeTheme

/**
 * 测试界面：基本应用。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIBase : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 显示Compose UI组件
        setContent {
            // 应用主题
            TestComposeTheme {
                Column {
                    Text("示例一：")
                    TaskInfo()
                    Text("示例二：")
                    TaskInfo2()
                }
            }
        }
    }

    /**
     * 示例一：状态的简单应用。
     *
     * 在本示例中，我们为Composable组件添加状态，实现待办事项统计功能。
     */
    @Composable
    fun TaskInfo() {
        // 声明状态变量，每当状态变量被改变时，该Composable函数将被运行环境调用重绘UI。
        val count: MutableState<Int> = remember { mutableStateOf(0) }

        Column {
            // 读取状态变量
            Text("待办事项：${count.value}")
            Row {
                // 按钮被点击后修改状态变量的值
                Button(onClick = { count.value++ }) {
                    Text("增加")
                }
                Button(onClick = { count.value-- }) {
                    Text("减少")
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TaskInfoPreview() {
        TestComposeTheme {
            TaskInfo()
        }
    }

    @Composable
    fun TaskInfo2() {
        // 将普通变量委托给 `remember` 函数
        var count: Int by remember { mutableStateOf(0) }

        Column {
            Text("待办事项：$count")
            Row {
                Button(onClick = { count++ }) {
                    Text("增加")
                }
                Button(onClick = { count-- }) {
                    Text("减少")
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TaskInfo2Preview() {
        TestComposeTheme {
            TaskInfo2()
        }
    }

    @Composable
    fun TaskInfo3() {
        // 将普通变量委托给 `remember` 函数
        var count: Int by remember { mutableStateOf(0) }

        Column {
            Text("待办事项：$count")
            Row {
                Button(onClick = { count++ }) {
                    Text("增加")
                }
                Button(onClick = { count-- }) {
                    Text("减少")
                }
            }
        }
    }
}
