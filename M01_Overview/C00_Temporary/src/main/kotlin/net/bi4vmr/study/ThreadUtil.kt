package net.bi4vmr.study

import android.os.Looper

/**
 * 线程相关工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
object ThreadUtil {

    fun isMainThread(): Boolean {
        // 如果当前线程的Looper与主线程Looper相同，则说明当前线程是主线程。
        return Looper.myLooper() == Looper.getMainLooper()
    }

    fun requireMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw IllegalStateException("This operation must be called from main thread!")
        }
    }

    fun requireSubThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw IllegalStateException("This operation must be called from main thread!")
        }
    }
}
