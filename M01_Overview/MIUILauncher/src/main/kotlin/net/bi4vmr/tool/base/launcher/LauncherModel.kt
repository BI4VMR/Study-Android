package net.bi4vmr.tool.base.launcher

import android.content.Context
import android.content.pm.LauncherActivityInfo
import android.content.pm.LauncherApps
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.util.Log
import java.util.concurrent.Executors

/**
 * 数据加载与持久化（对应 MIUI 的 LauncherModel + LoaderTask + AllAppsLoaderTask，已简化）。
 *
 * 流程：
 * 1. 通过 LauncherApps 获取所有可启动的 Activity（AllAppsLoaderTask）。
 * 2. 读取数据库中已保存的位置；组件已不存在的记录会被删除（RemovedComponentInfoList）。
 * 3. 数据库中没有的应用按"第一个空位"追加（PackageAddTask）。
 * 4. 切回主线程，交给界面绑定（Callbacks.bindItems）。
 */
class LauncherModel(
    context: Context,
    private val countX: Int,
    private val countY: Int,
    private val hotseatCount: Int,
) {

    companion object {
        private const val TAG: String = "TestApp-LauncherModel"
    }

    private val appContext: Context = context.applicationContext
    private val db = LauncherDatabase(appContext)
    private val launcherApps = appContext.getSystemService(LauncherApps::class.java)
    private val worker = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())

    fun load(callback: (List<ItemInfo>) -> Unit) {
        worker.execute {
            val start = System.currentTimeMillis()
            val items = loadInternal()
            Log.d(TAG, "load ${items.size} items in ${System.currentTimeMillis() - start}ms")
            mainHandler.post { callback(items) }
        }
    }

    fun savePositions(items: List<ItemInfo>) {
        // 拷贝一份快照，避免主线程后续修改影响写入
        val snapshot = items.map {
            ItemInfo(it.id, it.title, it.component, it.container, it.screen, it.cellX, it.cellY)
        }
        worker.execute { db.updatePositions(snapshot) }
    }

    fun close() {
        worker.execute { db.close() }
        worker.shutdown()
    }

    private fun loadInternal(): List<ItemInfo> {
        val density = appContext.resources.displayMetrics.densityDpi
        val activities: Map<String, LauncherActivityInfo> = launcherApps
            .getActivityList(null, Process.myUserHandle())
            .filter { it.componentName.packageName != appContext.packageName }
            .associateBy { it.componentName.flattenToString() }

        val saved = db.queryAll()
        val isFirstLoad = saved.isEmpty()
        val occupied = HashSet<String>()
        val result = mutableListOf<ItemInfo>()

        // 1. 恢复已保存的元素，剔除无效记录
        for (item in saved) {
            val key = item.component.flattenToString()
            if (activities[key] == null || !isPositionValid(item) || !occupied.add(posKey(item))) {
                Log.d(TAG, "remove invalid item: $item")
                db.delete(item.id)
                continue
            }
            result.add(item)
        }

        // 2. 新应用追加到第一个空位
        val loaded = result.map { it.component.flattenToString() }.toHashSet()
        activities.values
            .filter { it.componentName.flattenToString() !in loaded }
            .sortedBy { it.label.toString() }
            .forEach { info ->
                val item = ItemInfo(title = info.label.toString(), component = info.componentName)
                if (!(isFirstLoad && findHotseatVacant(item, occupied))) {
                    findDesktopVacant(item, occupied)
                }
                occupied.add(posKey(item))
                db.insert(item)
                result.add(item)
            }

        // 3. 加载图标
        result.forEach { item ->
            item.icon = activities[item.component.flattenToString()]?.getIcon(density)
        }
        return result
    }

    private fun isPositionValid(item: ItemInfo): Boolean = when (item.container) {
        ItemInfo.CONTAINER_HOTSEAT -> item.cellX in 0 until hotseatCount && item.cellY == 0
        ItemInfo.CONTAINER_DESKTOP -> item.screen >= 0 && item.cellX in 0 until countX && item.cellY in 0 until countY
        else -> false
    }

    private fun posKey(item: ItemInfo): String =
        "${item.container}/${item.screen}/${item.cellX}/${item.cellY}"

    private fun findHotseatVacant(item: ItemInfo, occupied: Set<String>): Boolean {
        item.container = ItemInfo.CONTAINER_HOTSEAT
        item.screen = 0
        item.cellY = 0
        for (x in 0 until hotseatCount) {
            item.cellX = x
            if (posKey(item) !in occupied) return true
        }
        return false
    }

    private fun findDesktopVacant(item: ItemInfo, occupied: Set<String>) {
        item.container = ItemInfo.CONTAINER_DESKTOP
        var screen = 0
        while (true) {
            for (y in 0 until countY) for (x in 0 until countX) {
                item.screen = screen
                item.cellX = x
                item.cellY = y
                if (posKey(item) !in occupied) return
            }
            screen++
        }
    }
}
