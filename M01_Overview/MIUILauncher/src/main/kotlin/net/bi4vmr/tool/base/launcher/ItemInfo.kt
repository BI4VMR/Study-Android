package net.bi4vmr.tool.base.launcher

import android.content.ComponentName
import android.graphics.drawable.Drawable

/**
 * 桌面元素数据（对应 MIUI 的 ItemInfo / ShortcutInfo）。
 *
 * 位置由 [container]、[screen]、[cellX]、[cellY] 共同决定，与 favorites 表一一对应。
 */
class ItemInfo(
    var id: Long = NO_ID,
    val title: String,
    val component: ComponentName,
    var container: Int = CONTAINER_DESKTOP,
    var screen: Int = 0,
    var cellX: Int = 0,
    var cellY: Int = 0,
) {

    companion object {
        const val NO_ID: Long = -1L

        // 与 MIUI LauncherSettings.Favorites 中的取值保持一致
        const val CONTAINER_DESKTOP: Int = -100
        const val CONTAINER_HOTSEAT: Int = -101
    }

    var icon: Drawable? = null

    override fun toString(): String =
        "ItemInfo(id=$id, title=$title, container=$container, screen=$screen, cell=[$cellX,$cellY])"
}
