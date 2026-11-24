package net.bi4vmr.tool.base.launcher

import android.content.ComponentName
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * 桌面布局数据库（对应 MIUI 的 LauncherProvider 中的 favorites 表，已简化）。
 */
class LauncherDatabase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {

    companion object {
        const val DB_NAME: String = "launcher.db"
        private const val TABLE: String = "favorites"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT, " +
                    "component TEXT NOT NULL, " +
                    "container INTEGER NOT NULL, " +
                    "screen INTEGER NOT NULL, " +
                    "cellX INTEGER NOT NULL, " +
                    "cellY INTEGER NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE")
        onCreate(db)
    }

    fun queryAll(): List<ItemInfo> {
        val result = mutableListOf<ItemInfo>()
        readableDatabase.query(
            TABLE, null, null, null, null, null,
            "container ASC, screen ASC, cellY ASC, cellX ASC"
        ).use { c ->
            while (c.moveToNext()) {
                val component = ComponentName.unflattenFromString(
                    c.getString(c.getColumnIndexOrThrow("component"))
                ) ?: continue
                result.add(
                    ItemInfo(
                        id = c.getLong(c.getColumnIndexOrThrow("_id")),
                        title = c.getString(c.getColumnIndexOrThrow("title")) ?: "",
                        component = component,
                        container = c.getInt(c.getColumnIndexOrThrow("container")),
                        screen = c.getInt(c.getColumnIndexOrThrow("screen")),
                        cellX = c.getInt(c.getColumnIndexOrThrow("cellX")),
                        cellY = c.getInt(c.getColumnIndexOrThrow("cellY")),
                    )
                )
            }
        }
        return result
    }

    fun insert(item: ItemInfo) {
        val values = toValues(item).apply {
            put("title", item.title)
            put("component", item.component.flattenToString())
        }
        item.id = writableDatabase.insert(TABLE, null, values)
    }

    fun delete(id: Long) {
        writableDatabase.delete(TABLE, "_id=?", arrayOf(id.toString()))
    }

    /** 批量更新位置（拖拽结束后调用）。 */
    fun updatePositions(items: List<ItemInfo>) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            items.forEach { db.update(TABLE, toValues(it), "_id=?", arrayOf(it.id.toString())) }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    private fun toValues(item: ItemInfo): ContentValues = ContentValues().apply {
        put("container", item.container)
        put("screen", item.screen)
        put("cellX", item.cellX)
        put("cellY", item.cellY)
    }
}
