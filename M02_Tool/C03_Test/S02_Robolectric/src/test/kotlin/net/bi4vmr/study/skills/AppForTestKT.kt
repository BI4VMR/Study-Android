package net.bi4vmr.study.skills

import android.app.Application

/**
 * 测试专用Application类。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class AppForTestKT : Application() {

    override fun onCreate() {
        super.onCreate()
        println("这是单元测试专用的Application类。")
    }
}
