package net.bi4vmr.study.base

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * 业务组件：HTTP接口管理。
 *
 * `@Inject` 注解表示该类以依赖注入方式管理，程序运行时Hilt会根据参数自动创建实例。
 *
 * @constructor 构造方法。
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class HTTPManagerKT @Inject constructor(

    /**
     * 上下文。
     *
     * `@ApplicationContext` 表示使用应用级Context，即带有 `@HiltAndroidApp` 注解的Application实例。
     */
    @param:ApplicationContext private val context: Context
) {

    companion object {
        private const val TAG = "HTTPManager"
    }

    // 业务接口：登录
    fun login() {
        Log.i(TAG, "Login. App:[$context]")
    }
}
