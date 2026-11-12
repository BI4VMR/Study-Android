package net.bi4vmr.study.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/* 定义数据类，描述主题属性。 */
@Immutable
data class MyTextColors(
    val warning: Color,
    val error: Color
)

// `staticCompositionLocalOf` 不追踪具体组件的读取行为，主题变化时刷新整个组件树，效率更高。
val LocalMyTextColors: ProvidableCompositionLocal<MyTextColors> = staticCompositionLocalOf {
    // 此处提供默认值即可。
    MyTextColors(
        warning = Color.Unspecified,
        error = Color.Unspecified
    )
}

// 浅色配置
val LightTextColors = MyTextColors(
    warning = Color(0xFFEEEE00),
    error = Color(0xFFEF5350)
)

// 深色配置
val DarkTextColors = MyTextColors(
    warning = Color.Cyan,
    error = Color.Magenta
)


@Composable
fun MyCustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // 根据传入参数选择对应的主题色实例
    val myTextColors = if (darkTheme) DarkTextColors else LightTextColors

    // 通过 CompositionLocalProvider 向作用域内注入 LocalMyTextColors 变量
    CompositionLocalProvider(LocalMyTextColors provides myTextColors) {
        // 保留 MaterialTheme
        MaterialTheme(
            colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme(),
            content = content
        )
    }
}


// 提供一个单例对象便于访问
object MyCustomTheme {

    val textColors: MyTextColors
        @Composable
        get() = LocalMyTextColors.current
}
