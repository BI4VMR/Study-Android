package net.bi4vmr.study.box

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：基本应用。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIBox : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                // 顶层容器
                Column(modifier = Modifier.fillMaxSize()) {
                    Text("默认排列规则：")
                    Spacer(Modifier.height(8.dp))

                    Box(Modifier.size(300.dp)) {
                        // 子元素 1
                        Box(
                            modifier = Modifier
                                .size(200.dp)
                                .background(Color.Blue)
                        )
                        // 子元素 2
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .background(Color.Cyan)
                        )
                        // 子元素 3
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color.Green)
                        )
                    }

                    Spacer(Modifier.height(8.dp))
                    Text("对齐方式：")
                    Spacer(Modifier.height(8.dp))

                    Box(
                        // 全局规则：所有子元素都居中对齐。
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(300.dp)
                    ) {
                        // 子元素 1
                        Box(
                            modifier = Modifier
                                .size(200.dp)
                                .background(Color.Blue)
                        )
                        // 子元素 2
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .background(Color.Cyan)
                        )
                        // 子元素 3
                        Box(
                            modifier = Modifier
                                // 局部规则：对齐到父容器的右下角。
                                .align(Alignment.BottomEnd)
                                .size(100.dp)
                                .background(Color.Green)
                        )
                    }
                }
            }
        }
    }
}
