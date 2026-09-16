package net.bi4vmr.study

import android.app.ActivityManager
import android.os.Process

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
object UserUtil {

    fun getCurrentUserID(): Int {
        return ActivityManager.getCurrentUser()
    }

    fun getProcessUserID(): Int {
        return Process.myUserHandle().getIdentifier()
    }

    fun isCurrentUser(userId: Int): Boolean {
        return userId == getCurrentUserID()
    }

    fun isNotCurrentUser(userId: Int): Boolean {
        return userId != getCurrentUserID()
    }

    fun isProcessUser(userId: Int): Boolean {
        return userId == getProcessUserID()
    }

    fun isNotProcessUser(userId: Int): Boolean {
        return userId != getProcessUserID()
    }
}
