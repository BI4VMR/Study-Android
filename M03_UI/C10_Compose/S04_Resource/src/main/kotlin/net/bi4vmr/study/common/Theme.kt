package net.bi4vmr.study.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 配色方案：浅色模式
private val LightColorScheme = lightColorScheme(
    primary = Color.Black
)

// 配色方案：深色模式
private val DarkColorScheme = darkColorScheme(
    // 主要颜色：文本框、按钮等元素的主色调
    primary = Color.White
)


// 自定义主题
@Composable
fun MyTheme(
    // 是否启用深色模式
    darkMode: Boolean,
    content: @Composable () -> Unit
) {
    // 根据深色模式是否启用选择配色方案
    val colorScheme = if (darkMode) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
