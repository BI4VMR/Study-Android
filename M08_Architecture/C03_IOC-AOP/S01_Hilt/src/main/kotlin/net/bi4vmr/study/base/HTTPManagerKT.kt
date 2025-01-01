package net.bi4vmr.study.base

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class HTTPManagerKT @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val TAG = "HTTPManager"
    }

    fun login() {
        Log.i(TAG, "Login.")
    }
}

