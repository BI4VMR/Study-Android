package net.bi4vmr.tool.base.launcher

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView

/**
 * 应用图标视图（对应 MIUI 的 ShortcutIcon / Launcher3 的 BubbleTextView，已简化）。
 */
@SuppressLint("ViewConstructor")
class IconView(context: Context, val item: ItemInfo) : TextView(context) {

    private val iconSize: Int = dp(220f)

    init {
        gravity = Gravity.CENTER
        maxLines = 1
        ellipsize = TextUtils.TruncateAt.END
        setTextColor(Color.WHITE)
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        setShadowLayer(dp(2f).toFloat(), 0f, dp(1f).toFloat(), 0x88000000.toInt())
        compoundDrawablePadding = dp(4f)
        val padding = dp(4f)
        setPadding(padding, padding, padding, padding)

        item.icon?.mutate()?.let {
            it.setBounds(0, 0, iconSize, iconSize)
            setCompoundDrawables(null, it, null, null)
        }
        applyContainer()
    }

    /** Dock 中的图标不显示名称。 */
    fun applyContainer() {
        text = if (item.container == ItemInfo.CONTAINER_HOTSEAT) "" else item.title
        contentDescription = item.title
    }

    private fun dp(value: Float): Int =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics).toInt()
}
