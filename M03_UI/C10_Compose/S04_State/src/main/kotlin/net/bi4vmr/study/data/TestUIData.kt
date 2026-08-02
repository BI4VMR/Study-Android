package net.bi4vmr.study.data

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import net.bi4vmr.study.base.common.TestComposeTheme

/**
 * 测试界面：数据表示。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIData : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("使用 Data Class 表示数据：")
                    StudentInfo()
                    Text("错误示范：使用可变属性表示数据。")
                    StudentInfo2()
                    Text("正确示范：在 Compose 中使用可变属性对象。")
                    StudentInfo3()
                }
            }
        }
    }

    /**
     * 示例五：使用 Data Class 对象作为 Compose 状态。
     *
     * 在本示例中，我们使用 Data Class 对象作为 Compose 状态。
     */
    @Composable
    fun StudentInfo() {
        var state: Student by remember {
            val student = Student("1", "Alice")
            mutableStateOf(student)
        }

        Column {
            Text("用户名称：[${state.name}]")
            Row {
                Button(onClick = {
                    // 创建新对象并复制原对象属性，然后更新名称属性。
                    val newStudent = state.copy(name = "Bob")
                    // 更新状态变量
                    state = newStudent
                }) {
                    Text("更新状态")
                }
            }
        }
    }

    /**
     * 示例五：使用 Data Class 对象作为 Compose 状态。
     *
     * 在本示例中，我们使用 Data Class 对象作为 Compose 状态。
     */
    @Composable
    fun StudentInfo2() {
        var state: Student2 by remember {
            val student = Student2("1", "Alice")
            mutableStateOf(student)
        }

        Column {
            Text("用户名称：[${state.name}]")
            Row {
                Button(onClick = {
                    // 直接修改原对象的名称属性
                    state.name = "Bob"
                }) {
                    Text("更新状态")
                }
            }
        }
    }

    /**
     * 示例五：使用 Data Class 对象作为 Compose 状态。
     *
     * 在本示例中，我们使用 Data Class 对象作为 Compose 状态。
     */
    @Composable
    fun StudentInfo3() {
        var state: Student2 by remember {
            val student = Student2("1", "Alice")
            mutableStateOf(student)
        }

        Column {
            Text("用户名称：[${state.name}]")
            Row {
                Button(onClick = {
                    // 新建对象并复制无需修改的属性，然后更新名称属性。
                    val old = state
                    val new = Student2(old.id, "Bob")
                    // 使用新的对象更新状态变量
                    state = new
                }) {
                    Text("更新状态")
                }
            }
        }
    }
}
