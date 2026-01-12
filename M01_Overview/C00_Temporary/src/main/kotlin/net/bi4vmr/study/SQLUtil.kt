package net.bi4vmr.study

import android.os.Looper

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class SQLUtil {

    fun merge() {

        // var remainingSql = sqlQuery
        // var paramIndex = 0
        //
        // while (remainingSql.contains("?")) {
        //     if (paramIndex >= bindArgs.size) {
        //         Log.w("StudentDBKT", "param more than ?")
        //     }
        //
        //     val paramValue = when (val param = bindArgs[paramIndex]) {
        //         is String -> "'${param.replace("'", "''")}'" // 转义单引号
        //         else -> param.toString()
        //     }
        //
        //     // 替换第一个出现的 ?
        //     remainingSql = remainingSql.replaceFirst("?", paramValue)
        //     paramIndex++
        // }
        //
        // if (paramIndex != bindArgs.size) {
        //     Log.w("StudentDBKT", "param more than ?")
        // }

        // Log.d("StudentDBKT", "SQL:[$remainingSql]")
    }

    fun checkMain(): Boolean {
        return Looper.myLooper() == Looper.getMainLooper()
    }

    fun requireMain() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw IllegalStateException("This operation must be called on the main thread.")
        }
    }
}
