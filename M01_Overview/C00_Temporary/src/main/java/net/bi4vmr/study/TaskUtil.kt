package net.bi4vmr.study

import android.app.usage.UsageEvents
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.ComponentName
import android.content.Context
import android.util.Log
import java.util.TreeMap

object TaskUtil {
    // System.currentTimeMillis() - 5 * 60 * 1000,
    fun getbgapps(context: Context) {
        val mUsageStatsManager = context.getSystemService(UsageStatsManager::class.java)

        val ts: Long = System.currentTimeMillis()
        val stats: List<UsageStats> = mUsageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_BEST,
            ts - 5000,
            ts
        )
        stats.filter {
            if (it.totalTimeInForeground == 0L) {
                return@filter false
            }

            true
        }.forEach {
            Log.d("TestApp", "name:[${it.packageName}] FGTime:${it.totalTimeInForeground} Last:${it.lastTimeUsed}")
        }
    }

    private fun getTopRunningTasksByEvent(context:Context): ComponentName? {
        val mUsageStatsManager = context.getSystemService(UsageStatsManager::class.java)

        val ts: Long = System.currentTimeMillis()
        var topComponentName: ComponentName? = null
        val time = System.currentTimeMillis()
        val usageEvents: UsageEvents =
            mUsageStatsManager.queryEvents(time - 60 * 60 * 1000, time)
        // 临时变量，用于记录每个事件。
        var out: UsageEvents.Event
        val map: TreeMap<Long?, UsageEvents.Event?> = TreeMap()

        while (usageEvents.hasNextEvent()) {
            out = UsageEvents.Event()
            if (usageEvents.getNextEvent(out)) {
                map[out.timeStamp] = out
            } else {
                "UsageEvents is unavailable."
            }
        }

        if (!map.isEmpty()) {
            // 将keyset颠倒过来，让最近的应用排列在前面。
            val keySets = map.navigableKeySet()
            val iterator: Iterator<*> = keySets.descendingIterator()

            while (iterator.hasNext()) {
                val event = map[iterator.next()]
                // 如果有APP移动到前台，则更新topComponentName。
                if (event?.eventType == UsageEvents.Event.ACTIVITY_RESUMED) {
                    topComponentName = ComponentName(event.packageName, event.className)
                    break
                }
            }
        }

        return topComponentName
    }
}