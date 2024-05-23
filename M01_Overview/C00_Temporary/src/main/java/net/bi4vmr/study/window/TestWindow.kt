package net.bi4vmr.study.window

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.util.AttributeSet
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 */
@SuppressLint("WrongConstant")
class TestWindow : FrameLayout {

    private val wm: WindowManager by lazy {
        context.getSystemService(WindowManager::class.java)
    }
    private val lp: WindowManager.LayoutParams by lazy {
        WindowManager.LayoutParams().apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
            format = PixelFormat.RGBA_8888
            type = 2041
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
        }
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setBackgroundColor(Color.parseColor("#3300FF00"))
    }

    fun show() {
        wm.addView(this, lp)
    }

    fun close() {
        wm.removeViewImmediate(this)
    }
}
