package net.bi4vmr.tool.base

import android.content.pm.LauncherApps
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import net.bi4vmr.tool.base.launcher.CellLayout
import net.bi4vmr.tool.base.launcher.DragLayer
import net.bi4vmr.tool.base.launcher.IconView
import net.bi4vmr.tool.base.launcher.ItemInfo
import net.bi4vmr.tool.base.launcher.LauncherModel
import net.bi4vmr.tool.base.launcher.PageIndicator
import net.bi4vmr.tool.base.launcher.Workspace

/**
 * 示例界面：桌面图标拖拽排序。
 *
 * 结构（对应 MIUI Launcher）：
 * ```
 * DragLayer
 *  └─ LinearLayout
 *      ├─ Workspace        分页容器，每页一个 CellLayout
 *      ├─ PageIndicator    页面指示器
 *      └─ CellLayout       Dock（Hotseats）
 * ```
 *
 * 操作：左右滑动翻页；点击启动应用；长按拖拽排序，可拖入/拖出 Dock，拖到屏幕边缘自动翻页。
 */
class TestUIWorkspaceKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIWorkspaceKT::class.java.simpleName}"

        private const val COUNT_X: Int = 6
        private const val COUNT_Y: Int = 2
        private const val HOTSEAT_COUNT: Int = 4
    }

    private lateinit var dragLayer: DragLayer
    private lateinit var workspace: Workspace
    private lateinit var hotseat: CellLayout
    private lateinit var model: LauncherModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setupViews()

        model = LauncherModel(this, COUNT_X, COUNT_Y, HOTSEAT_COUNT)
        model.load { items -> bindItems(items) }
    }

    override fun onPause() {
        super.onPause()
        dragLayer.cancelDrag()
    }

    override fun onDestroy() {
        super.onDestroy()
        model.close()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // 模仿桌面：返回键回到第一页
        if (workspace.currentPage != 0) workspace.snapToPage(0) else super.onBackPressed()
    }

    private fun setupViews() {
        val density = resources.displayMetrics.density
        workspace = Workspace(this, COUNT_X, COUNT_Y)
        hotseat = CellLayout(this, HOTSEAT_COUNT, 1).apply {
            val padding = (8 * density).toInt()
            setPadding(padding, 0, padding, 0)
        }
        val indicator = PageIndicator(this)
        workspace.onPageChangeListener = { count, _, progress -> indicator.update(count, progress) }

        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(workspace, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f))
            addView(indicator, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (24 * density).toInt()))
            addView(hotseat, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (96 * density).toInt()))
        }
        ViewCompat.setOnApplyWindowInsetsListener(content) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        dragLayer = DragLayer(this).apply {
            addView(content, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            workspace = this@TestUIWorkspaceKT.workspace
            hotseat = this@TestUIWorkspaceKT.hotseat
            onDropCompleted = { saveLayout() }
        }
        setContentView(dragLayer)
    }

    /** 对应 BaseLauncher.bindItem：按 container 分发到 Dock 或 Workspace 对应页。 */
    private fun bindItems(items: List<ItemInfo>) {
        val pageCount = (items.filter { it.container == ItemInfo.CONTAINER_DESKTOP }
            .maxOfOrNull { it.screen } ?: 0) + 1
        repeat(pageCount) { workspace.addPage() }

        items.forEach { item ->
            val icon = createIcon(item)
            val layout = if (item.container == ItemInfo.CONTAINER_HOTSEAT) hotseat else workspace.page(item.screen)
            layout.addView(icon, CellLayout.LayoutParams(item.cellX, item.cellY))
        }
        // 中间可能存在空页（例如图标被卸载），saveLayout 中统一清理并回写
        saveLayout()
        Log.d(TAG, "bindItems: ${items.size} items, $pageCount pages")
    }

    private fun createIcon(item: ItemInfo): IconView = IconView(this, item).apply {
        setOnClickListener { startApp(this) }
        setOnLongClickListener {
            dragLayer.startDrag(this)
            true
        }
    }

    private fun startApp(icon: IconView) {
        val bounds = Rect()
        icon.getGlobalVisibleRect(bounds)
        try {
            getSystemService(LauncherApps::class.java)
                .startMainActivity(icon.item.component, Process.myUserHandle(), bounds, null)
        } catch (e: Exception) {
            Toast.makeText(this, "无法启动：${icon.item.title}", Toast.LENGTH_SHORT).show()
            Log.w(TAG, "startApp failed", e)
        }
    }

    /** 从视图树回收所有图标的最新位置并写入数据库。 */
    private fun saveLayout() {
        workspace.stripEmptyPages()
        val items = mutableListOf<ItemInfo>()
        for (page in 0 until workspace.pageCount) {
            collect(workspace.page(page), ItemInfo.CONTAINER_DESKTOP, page, items)
        }
        collect(hotseat, ItemInfo.CONTAINER_HOTSEAT, 0, items)
        model.savePositions(items)
    }

    private fun collect(layout: CellLayout, container: Int, screen: Int, out: MutableList<ItemInfo>) {
        for (i in 0 until layout.childCount) {
            val icon = layout.getChildAt(i) as? IconView ?: continue
            val lp = icon.layoutParams as CellLayout.LayoutParams
            icon.item.container = container
            icon.item.screen = screen
            icon.item.cellX = lp.cellX
            icon.item.cellY = lp.cellY
            out.add(icon.item)
        }
    }
}
