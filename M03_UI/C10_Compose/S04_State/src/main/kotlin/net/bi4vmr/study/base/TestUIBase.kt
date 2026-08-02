package net.bi4vmr.study.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.bi4vmr.study.base.common.TestComposeTheme

/**
 * 测试界面：基本应用。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIBase : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("缺少 `stateOf()` 部分：")
                    Counter()
                    Text("缺少 `remember()` 部分：")
                    Counter2()
                    Text("缺少 `by` 部分：")
                    Counter3()
                    Text("重置状态：")
                    UserInfo()
                }
            }
        }
    }

    /**
     * 示例一：缺少 `mutableStateOf()` 的状态变量。
     *
     * 在本示例中，我们声明状态变量但不使用 `mutableStateOf()` 方法，并观察运行时的行为。
     */
    @Composable
    fun Counter() {
        // 声明状态变量，但不使用 `mutableStateOf()` 方法。
        var count: Int = remember { 0 }

        Column {
            Text("当前数值：[$count]")
            Row {
                Button(onClick = { count++ }) {
                    Text("数值增加")
                }
                Button(onClick = { count-- }) {
                    Text("数值减少")
                }
            }
        }
    }

    /**
     * 示例二：缺少 `remember()` 的状态变量。
     *
     * 在本示例中，我们声明状态变量但不使用 `remember()` 方法，并观察运行时的行为。
     */
    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun Counter2() {
        // 声明状态变量，但不使用 `remember()` 方法。
        var count: Int by mutableIntStateOf(0)

        Column {
            Text("当前数值：[$count]")
            Row {
                Button(onClick = { count++ }) {
                    Text("数值增加")
                }
                Button(onClick = { count-- }) {
                    Text("数值减少")
                }
            }
        }
    }

    /**
     * 示例三：缺少 `by` 的状态变量。
     *
     * 在本示例中，我们声明状态变量但不使用 `by` 关键字，并观察运行时的行为。
     */
    @Composable
    fun Counter3() {
        // 声明状态变量，但不使用属性委托。
        val count: MutableState<Int> = remember { mutableIntStateOf(0) }

        Column {
            Text("当前数值：[${count.value}]")
            Row {
                Button(onClick = { count.value++ }) {
                    Text("数值增加")
                }
                Button(onClick = { count.value-- }) {
                    Text("数值减少")
                }
            }
        }
    }

    /**
     * 示例四：重置状态。
     *
     * 在本示例中，我们为状态变量添加重置条件。
     */
    @Composable
    fun UserInfo() {
        // 用户 ID
        var id: Int by remember { mutableIntStateOf(1) }

        // 用户名称
        var name: String by remember(id) { mutableStateOf("") }

        Column {
            Text("用户名称")
            TextField(
                value = name,
                onValueChange = { name = it }
            )
            Spacer(Modifier.height(16.dp))
            Button(onClick = { id++ }) {
                Text("切换用户")
            }
        }
    }
}
