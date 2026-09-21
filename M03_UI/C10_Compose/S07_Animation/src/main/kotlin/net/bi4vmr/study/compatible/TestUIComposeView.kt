package net.bi4vmr.study.compatible

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.bi4vmr.study.databinding.TestuiComposeviewBinding

/**
 * 测试界面：在View中使用ComposeView。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIComposeView : AppCompatActivity() {

    private val binding: TestuiComposeviewBinding by lazy {
        TestuiComposeviewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // 放置Compose控件
        binding.composeview.setContent {
            Greeting(name = "Android")
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        // 声明文本框
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}
