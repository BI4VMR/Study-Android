package net.bi4vmr.study.privacy

import android.os.UserHandle
import java.lang.reflect.Method

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 */
object UserHandleExt {

    @JvmStatic
    fun getUserId(uid: Int): Int? {
        var userID: Int? = null
        try {
            val method: Method = UserHandle::class.java.getMethod("getUserId", Int::class.java)
            userID = method.invoke(null, uid) as? Int
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return userID
    }
}
