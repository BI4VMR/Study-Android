package net.bi4vmr.study.statesave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.bi4vmr.study.base.common.TestComposeTheme

/**
 * 测试界面：状态保持。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIStateSave : ComponentActivity() {

    // 使用 Kotlin Flow 保存可观察变量的 ViewModel 。
    private val viewModel: MyViewModel by viewModels()

    // 使用 LiveData 保存可观察变量的 ViewModel 。
    private val viewModel2: MyViewModel2 by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("使用 `remember()` 的状态变量：")
                    Counter()
                    Text("使用 `rememberSaveable()` 的状态变量：")
                    Counter2()
                    Text("使用 Kotlin Flow 作为状态变量：")
                    Counter3()
                    Text("使用 LiveData 作为状态变量：")
                    Counter4()
                }
            }
        }
    }

    /**
     * 示例五：比较 `remember()` 和 `rememberSaveable()` 的行为。
     *
     * 在本示例中，我们声明两个状态变量，并观察运行时的行为。
     */
    @Composable
    fun Counter() {
        var count: Int by remember { mutableIntStateOf(0) }

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

    @Composable
    fun Counter2() {
        var count: Int by rememberSaveable { mutableIntStateOf(0) }

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
     * 示例六：将 Kotlin Flow 作为 Compose 状态。
     *
     * 在本示例中，我们将 Kotlin Flow 作为 Compose 状态。
     */
    @Composable
    fun Counter3() {
        // 在界面 `onStart()` 时监听数据，在界面 `onStop()` 时取消监听数据。
        val count: Int by viewModel.count.collectAsStateWithLifecycle()

        Column {
            Text("当前数值：[$count]")
            Row {
                Button(onClick = { viewModel.add() }) {
                    Text("数值增加")
                }
                Button(onClick = { viewModel.reduce() }) {
                    Text("数值减少")
                }
            }
        }
    }

    /**
     * 示例七：将 LiveData 作为 Compose 状态。
     *
     * 在本示例中，我们将 LiveData 作为 Compose 状态。
     */
    @Composable
    fun Counter4() {
        // 监听 LiveData 数据，并绑定界面的生命周期。
        val count: Int? by viewModel2.count.observeAsState()

        Column {
            Text("当前数值：[$count]")
            Row {
                Button(onClick = { viewModel2.add() }) {
                    Text("数值增加")
                }
                Button(onClick = { viewModel2.reduce() }) {
                    Text("数值减少")
                }
            }
        }
    }
}
