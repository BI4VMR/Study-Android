package net.bi4vmr.study.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import net.bi4vmr.study.common.TestComposeTheme

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
                // 顶层容器
                Column(modifier = Modifier.fillMaxSize()) {
                    Text("列：")
                    Spacer(Modifier.height(8.dp))

                    Column {
                        // 子元素 1
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color.Cyan)
                        )
                        // 子元素 2
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .background(Color.Red)
                        )
                        // 子元素 3
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(100.dp)
                                .background(Color.Yellow)
                        )
                    }

                    Text("行：")
                    Spacer(Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                    ) {
                        // 子元素 1
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color.Cyan)
                        )
                        // 子元素 2
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .background(Color.Red)
                        )
                        // 子元素 3
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(100.dp)
                                .background(Color.Yellow)
                        )
                    }
                }
            }
        }
    }
}
