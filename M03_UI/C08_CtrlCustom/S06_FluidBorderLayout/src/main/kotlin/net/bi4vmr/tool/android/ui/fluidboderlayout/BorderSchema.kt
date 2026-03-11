package net.bi4vmr.tool.android.ui.fluidboderlayout

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.core.graphics.toColorInt

/**
 * 预设边框渐变效果。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
enum class BorderSchema(

    /**
     * 渐变颜色数组。
     */
    @ColorInt
    val colors: IntArray,

    /**
     * 渐变点位数组。
     *
     * 每个元素的取值范围为：[0.0, 1.0]，元素数量与 [colors] 一致，用于描述每个颜色在渐变圆中的比例。
     */
    val positions: FloatArray
) {

    /**
     * 彩色圆环。
     *
     * 边框为光谱颜色渐变。
     */
    COLORFUL_FULL(
        intArrayOf(
            Color.RED,                                  // 红色（起点）
            Color.rgb(255, 165, 0),     // 橙色
            Color.YELLOW,                               // 黄色
            Color.rgb(154, 205, 50),    // 黄绿色
            Color.GREEN,                                // 绿色
            Color.rgb(0, 255, 127),     // 春绿色
            Color.CYAN,                                 // 青色
            Color.rgb(0, 191, 255),     // 深天蓝
            Color.BLUE,                                 // 蓝色
            Color.rgb(138, 43, 226),    // 蓝紫色
            Color.MAGENTA,                              // 紫红色
            Color.rgb(255, 20, 147),    // 深粉红
            Color.RED                                   // 红色（终点）
        ),
        floatArrayOf(
            0.0F,           // 红色 (0/12)
            0.08333333F,    // 橙色 (1/12)
            0.16666667F,    // 黄色 (2/12)
            0.25F,          // 黄绿色 (3/12)
            0.33333334F,    // 绿色 (4/12)
            0.41666666F,    // 春绿色 (5/12)
            0.5F,           // 青色 (6/12)
            0.5833333F,     // 深天蓝 (7/12)
            0.6666667F,     // 蓝色 (8/12)
            0.75F,          // 蓝紫色 (9/12)
            0.8333333F,     // 紫红色 (10/12)
            0.9166667F,     // 深粉红 (11/12)
            1.0F            // 红色 (12/12)
        )
    ),

    /**
     * 彩色半圆。
     *
     * 边框的半个圆周为光谱颜色渐变，另外半个圆周为透明。
     */
    COLORFUL_HALF(
        intArrayOf(
            Color.TRANSPARENT,                         // 透明（起点）
            Color.RED,                                 // 红色
            Color.rgb(255, 165, 0),    // 橙色
            Color.YELLOW,                              // 黄色
            Color.GREEN,                               // 绿色
            Color.CYAN,                                // 青色
            Color.BLUE,                                // 蓝色
            Color.MAGENTA,                             // 紫红色
            Color.TRANSPARENT,                         // 透明（中心点）
            Color.TRANSPARENT                          // 透明（终点）
        ),
        floatArrayOf(
            0.0F,     // 透明（起点）
            0.05F,    // 红色
            0.15F,    // 橙色
            0.25F,    // 黄色
            0.35F,    // 绿色
            0.42F,    // 青色
            0.47F,    // 蓝色
            0.5F,     // 紫红色
            0.5F,     // 透明（中心点）
            1.0F      // 透明（终点）
        )
    ),

    /**
     * 彩色对角。
     *
     * 光谱颜色分为两组，分别在矩形对角位置，其他位置为透明。
     */
    COLORFUL_DIAGON(
        intArrayOf(
            Color.TRANSPARENT,                         // 透明（第一组起点）
            Color.RED,                                 // 红色
            Color.rgb(255, 165, 0),    // 橙色
            Color.YELLOW,                              // 黄色
            Color.GREEN,                               // 绿色
            Color.TRANSPARENT,                         // 透明（第一组终点）
            Color.TRANSPARENT,                         // 透明（第二组起点）
            Color.CYAN,                                // 青色
            Color.BLUE,                                // 蓝色
            Color.MAGENTA,                             // 紫红色
            Color.rgb(128, 0, 128),    // 紫色
            Color.TRANSPARENT,                         // 透明（第二组终点）
            Color.TRANSPARENT                          // 透明
        ),
        floatArrayOf(
            0.0F,     // 透明（第一组起点）
            0.02F,    // 红色
            0.08F,    // 橙色
            0.15F,    // 黄色
            0.22F,    // 绿色
            0.25F,    // 透明（第一组终点）
            0.5F,     // 透明（第二组起点）
            0.52F,    // 青色
            0.58F,    // 蓝色
            0.65F,    // 紫红色
            0.72F,    // 紫色
            0.75F,    // 透明（第二组终点）
            1.0F      // 透明
        )
    ),

    /**
     * 白色半圆。
     *
     * 边框的半个圆周为白色，边缘逐步过渡到透明，另外半个圆周为透明。
     */
    WHITE_HALF(
        intArrayOf(
            Color.TRANSPARENT,
            Color.TRANSPARENT,
            Color.WHITE,
            Color.WHITE,
            Color.TRANSPARENT
        ),
        floatArrayOf(
            0.0F,
            0.5F,
            0.6666667F,
            0.8333333F,
            1.0F
        )
    ),

    CUSTOME(
        intArrayOf(
            "#858585".toColorInt(),
            "#9F9F9F".toColorInt(),
            "#A6A6A6".toColorInt(),
            "#9E9E9E".toColorInt(),
            "#878787".toColorInt(),
            "#3D3D3D".toColorInt(),
            "#262626".toColorInt()
        ),
        floatArrayOf(
            0.0F,
            0.14F,
            0.23F,
            0.34F,
            0.46F,
            0.73F,
            0.87F,
        )
    );

    companion object {

        /**
         * 根据序号获取枚举常量。
         *
         * 未匹配到对应的常量时，将返回默认值 [COLORFUL_DIAGON] 。
         *
         * @param[ordinal] 枚举列表中的序号。
         * @return 枚举常量。
         */
        @JvmStatic
        fun parseByOrder(ordinal: Int): BorderSchema {
            entries.forEach {
                if (it.ordinal == ordinal) {
                    return it
                }
            }

            return COLORFUL_DIAGON
        }
    }
}
