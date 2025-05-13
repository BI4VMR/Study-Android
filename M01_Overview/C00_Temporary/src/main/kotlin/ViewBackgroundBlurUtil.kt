import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewRootImpl
import com.android.internal.graphics.drawable.BackgroundBlurDrawable
import java.lang.reflect.Method

/**
 * 全局模糊背景工具。
 *
 * 该工具只能在Android 12及以上版本系统中使用。
 *
 * @author yigangzhan@pateo.com.cn
 * @since 1.0.0
 */
@SuppressLint("BlockedPrivateApi, DiscouragedPrivateApi")
object ViewBackgroundBlurUtil {

    private val TAG: String = ViewBackgroundBlurUtil::class.java.simpleName

    fun getBlurBackground(view: View, blurRadius: Int = 32, cornerRadius: Float = 32.0F): Drawable? {
        // 获取ViewRootImpl
        val viewRootImpl: ViewRootImpl? = getViewRootImplByReflect(view)
        if (null == viewRootImpl) {
            Log.e(TAG, "Get ViewRootImpl error!")
            return null
        }

        // 获取模糊背景Drawable实例
        val drawable: BackgroundBlurDrawable? = createBlurBackgroundByReflect(viewRootImpl)
        if (drawable == null) {
            Log.e(TAG, "Create BlurBackground failed!")
            return null
        }

        drawable.setBlurRadius(blurRadius)
        drawable.setCornerRadius(cornerRadius)
        return drawable
    }

    private fun getViewRootImplByReflect(view: View): ViewRootImpl? {
        var viewRootImpl: ViewRootImpl? = null

        runCatching {
            val method: Method = View::class.java.getDeclaredMethod("getViewRootImpl")
            val result: Any? = method.invoke(view)
            if (result is ViewRootImpl) {
                viewRootImpl = result
            }
        }.onFailure {
            Log.e(TAG, "Reflect operate failed! Reason:[${it.message}]")
            it.printStackTrace()
        }

        return viewRootImpl
    }

    private fun createBlurBackgroundByReflect(viewRootImpl: ViewRootImpl): BackgroundBlurDrawable? {
        var drawable: BackgroundBlurDrawable? = null

        runCatching {
            val method = ViewRootImpl::class.java.getDeclaredMethod("createBackgroundBlurDrawable")
            method.isAccessible = true
            val result: Any? = method.invoke(viewRootImpl)
            if (result is BackgroundBlurDrawable) {
                drawable = result
            }
        }.onFailure {
            Log.e(TAG, "Reflect operate failed! Reason:[${it.message}]")
            it.printStackTrace()
        }

        return drawable
    }

    // // 在 BaseActivity 中统一管理
    // override fun onDestroy() {
    //     // 遍历根布局解除所有 BlurDrawable
    //     window.decorView.rootView.let { root ->
    //         root.background?.let {
    //             if (it is BackgroundBlurDrawable) releaseBlurDrawable(root)
    //         }
    //         clearBlurInViewHierarchy(root)
    //     }
    //     super.onDestroy()
    // }
    // private fun clearBlurInViewHierarchy(view: View) {
    //     if (view is ViewGroup) {
    //         for (i in 0 until view.childCount) {
    //             val child = view.getChildAt(i)
    //             child.background?.let {
    //                 if (it is BackgroundBlurDrawable) {
    //                     releaseBlurDrawable(child)
    //                 }
    //             }
    //             if (child is ViewGroup) {
    //                 clearBlurInViewHierarchy(child)
    //             }
    //         }
    //     }
    // }

}
