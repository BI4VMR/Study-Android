package net.bi4vmr.study

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.os.UserHandle
import android.os.UserManager
import java.lang.reflect.Method

/**
 * [UserManager]的功能扩展类。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class UserManagerExtend private constructor(private val mContext: Context) {

    companion object {
        // 本实例的生命周期跟随整个进程，不会导致内存泄露，因此可以忽略警告。
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: UserManagerExtend? = null

        @JvmStatic
        fun getInstance(context: Context): UserManagerExtend {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = UserManagerExtend(context.applicationContext)
                    }
                }
            }
            return instance!!
        }

        // private val TAG = "${PrivacyLog.TAG_PREFIX}${AppOpsManagerExt::class.java.simpleName}"

        /**
         * 特殊用户ID：所有用户。
         *
         * 本变量值与[UserHandle]中的 `USER_ALL` 常量保持一致。
         */
        private const val USER_ID_ALL: Int = -1

        /**
         * 特殊用户ID：当前用户。
         *
         * 本变量值与[UserHandle]中的 `USER_CURRENT` 常量保持一致。
         */
        private const val USER_ID_CURRENT: Int = -2
    }

    private val userManagerClass = UserManager::class.java
    private val userManager: UserManager = mContext.getSystemService(userManagerClass)
    private val activityManagerClass = ActivityManager::class.java
    private val activityManager: ActivityManager = mContext.getSystemService(activityManagerClass)

    /**
     * TODO 添加简述。
     *
     * "android.permission.INTERACT_ACROSS_USERS"
     * "android.permission.INTERACT_ACROSS_USERS_FULL"
     *
     * @param
     * @return
     */
    fun getCurrentUserID(): Int {
        val method: Method = activityManagerClass.getMethod("getCurrentUser")
        return method.invoke(null) as? Int ?: -2
    }

    /**
     * TODO 添加简述。
     *
     * "android.permission.INTERACT_ACROSS_USERS"
     * "android.permission.INTERACT_ACROSS_USERS_FULL"
     *
     * @param
     * @return
     */
    // fun getCurrentUserID(): Int {
    //     val method: Method = activityManagerClass.getMethod("getCurrentUser")
    //     return method.invoke(null) as? Int ?: -2
    // }
}
