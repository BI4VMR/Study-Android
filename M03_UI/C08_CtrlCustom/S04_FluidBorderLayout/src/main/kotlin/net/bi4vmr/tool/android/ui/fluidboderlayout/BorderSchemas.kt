package net.bi4vmr.tool.android.ui.fluidboderlayout

import android.graphics.Color

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author yigangzhan@pateo.com.cn
 * @since 1.0.0
 */
enum class BorderSchemas(
    val colors: IntArray,

    val positions: FloatArray
) {
    // 前1/2光谱过渡，后1/2透明的参数（注释保留）
    SCHEMA_1(
        intArrayOf(
            Color.TRANSPARENT,           // 起始透明
            Color.RED,                   // 红色
            Color.rgb(255, 165, 0),     // 橙色
            Color.YELLOW,                // 黄色
            Color.GREEN,                 // 绿色
            Color.CYAN,                  // 青色
            Color.BLUE,                  // 蓝色
            Color.MAGENTA,               // 紫红色
            Color.TRANSPARENT,           // 中间点透明
            Color.TRANSPARENT            // 结束透明
        ),

        floatArrayOf(
            0.0F,                        // 起始点
            0.05F,                       // 红色开始
            0.15F,                       // 橙色
            0.25F,                       // 黄色
            0.35F,                       // 绿色
            0.42F,                       // 青色
            0.47F,                       // 蓝色
            0.5F,                        // 紫红色结束
            0.5F,                        // 透明区域开始
            1.0F                         // 结束点
        )
    ),

    // 对角光谱渐变参数
    SCHEMA_3(
        intArrayOf(
            Color.TRANSPARENT,           // 起始透明
            Color.RED,                   // 第一组光谱开始：红色
            Color.rgb(255, 165, 0),     // 橙色
            Color.YELLOW,                // 黄色
            Color.GREEN,                 // 绿色
            Color.TRANSPARENT,           // 第一组结束，透明区域开始
            Color.TRANSPARENT,           // 透明区域
            Color.CYAN,                  // 第二组光谱开始：青色
            Color.BLUE,                  // 蓝色
            Color.MAGENTA,               // 紫红色
            Color.rgb(128, 0, 128),     // 紫色
            Color.TRANSPARENT,           // 第二组结束
            Color.TRANSPARENT            // 最终透明
        ),
        floatArrayOf(
            0.0F,                        // 起始点
            0.02F,                       // 第一组光谱开始
            0.08F,                       // 橙色
            0.15F,                       // 黄色
            0.22F,                       // 绿色
            0.25F,                       // 第一组结束（1/4圆）
            0.5F,                        // 透明区域中点
            0.52F,                       // 第二组光谱开始
            0.58F,                       // 蓝色
            0.65F,                       // 紫红色
            0.72F,                       // 紫色
            0.75F,                       // 第二组结束（3/4圆）
            1.0F                         // 结束点
        )
    ),

    // 新的渐变参数：完整光谱圆周渐变，无透明间隙
    SCHEMA_322(

        intArrayOf(
            Color.RED,                   // 红色
            Color.rgb(255, 165, 0),     // 橙色
            Color.YELLOW,                // 黄色
            Color.rgb(154, 205, 50),    // 黄绿色
            Color.GREEN,                 // 绿色
            Color.rgb(0, 255, 127),     // 春绿色
            Color.CYAN,                  // 青色
            Color.rgb(0, 191, 255),     // 深天蓝
            Color.BLUE,                  // 蓝色
            Color.rgb(138, 43, 226),    // 蓝紫色
            Color.MAGENTA,               // 紫红色
            Color.rgb(255, 20, 147),    // 深粉红
            Color.RED                    // 回到红色，形成闭环
        ),

        floatArrayOf(
            0.0F,                        // 红色
            0.083F,                      // 橙色 (1/12)
            0.167F,                      // 黄色 (2/12)
            0.25F,                       // 黄绿色 (3/12)
            0.333F,                      // 绿色 (4/12)
            0.417F,                      // 春绿色 (5/12)
            0.5F,                        // 青色 (6/12)
            0.583F,                      // 深天蓝 (7/12)
            0.667F,                      // 蓝色 (8/12)
            0.75F,                       // 蓝紫色 (9/12)
            0.833F,                      // 紫红色 (10/12)
            0.917F,                      // 深粉红 (11/12)
            1.0F                         // 回到红色 (12/12)
        )
    )
}
