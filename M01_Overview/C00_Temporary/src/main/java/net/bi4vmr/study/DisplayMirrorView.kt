@file:Suppress("DEPRECATION")

package net.bi4vmr.study

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Matrix
import android.graphics.Point
import android.graphics.Rect
import android.hardware.display.DisplayManager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceControl
import android.view.SurfaceHolder
import android.view.SurfaceView
import net.bi4vmr.tool.android.ability.framework.input.InputEventExtend
import net.bi4vmr.tool.android.ability.framework.input.InputManagerExtend
import net.bi4vmr.tool.android.ability.framework.view.SurfaceControlExtend
import net.bi4vmr.tool.android.ability.framework.window.WindowManagerServiceExtend

/**
 * 显示屏镜像组件。
 *
 * 可以镜像特定显示屏的画面，并转发当前控件的触摸事件实现远程控制。
 *
 * 本工具所使用的系统接口从 Android 13 (API 33) 开始提供。
 *
 * 调用者应当具有 `Manifest.permission.READ_FRAME_BUFFER` 权限。
 *
 * 若需要启用触摸事件转发，调用者应当具有 `Manifest.permission.INJECT_EVENTS` 权限。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */

class DisplayMirrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : SurfaceView(context, attrs) {

    companion object {
        private val TAG = DisplayMirrorView::class.java.simpleName
    }


    private val displayManager = context.getSystemService(DisplayManager::class.java)

    /**
     * 是否启用远程控制。
     */
    private var mAllowInput: Boolean = false

    /**
     * 目标显示器 ID。
     */
    private var mDisplayID = -1

    /**
     * 目标画面的 SurfaceControl。
     */
    private var mTargetSurfaceControl: SurfaceControl? = null

    /**
     * 目标画面的宽度。
     *
     * 用于计算缩放比例，转发触摸事件。
     */
    private var mTargetWidth = 0

    /**
     * 目标画面的高度。
     *
     * 用于计算缩放比例，转发触摸事件。
     */
    private var mTargetHeight = 0

    /**
     * 触摸事件变换矩阵。
     */
    private val touchMatrix = Matrix()

    private val surfaceCallback = SurfaceCallback()


    init {
        holder.addCallback(surfaceCallback)
    }


    fun setAllowInput(allow: Boolean) {
        mAllowInput = allow
    }

    fun isAllowInput(): Boolean = mAllowInput

    /**
     * 开始镜像。
     *
     * @param[displayID] 目标显示器 ID。
     */
    fun start(displayID: Int) {
        mDisplayID = displayID

        if (holder.surface?.isValid == true) {
            attachMirror()
        } else {
            Log.w(TAG, "surface not valid, wait for surfaceCreated")
        }
    }

    /**
     * 停止镜像。
     */
    fun stop() {
        detachMirror()

        mDisplayID = -1
    }


    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    private fun attachMirror() {
        detachMirror()

        val display = displayManager.getDisplay(mDisplayID)
        if (display == null) {
            Log.e(TAG, "rear display $mDisplayID not found")
            return
        }

        val size = Point()
        display.getRealSize(size)
        mTargetWidth = size.x
        mTargetHeight = size.y

        try {
            val surfaceControl = SurfaceControlExtend.newInstance()
            val success = WindowManagerServiceExtend.mirrorDisplay(mDisplayID, surfaceControl)
            if (!success || !surfaceControl.isValid) {
                Log.e(TAG, "Mirror display [$mDisplayID] failed!")
                return
            }

            mTargetSurfaceControl = surfaceControl
            // 挂载到 SurfaceView 自身的 SurfaceControl 下
            SurfaceControl.Transaction()
                .reparent(surfaceControl, this@DisplayMirrorView.surfaceControl)
                .setVisibility(surfaceControl, true)
                .apply()
            applyLayout(surfaceControl, width, height)
            Log.i(TAG, "mirror attached, displayId = $mDisplayID, src = ${mTargetWidth}x$mTargetHeight")
        } catch (e: Exception) {
            Log.e(TAG, "attachMirror error", e)
        }
    }

    private fun applyLayout(surfaceControl: SurfaceControl, w: Int, h: Int) {
        if (mTargetWidth <= 0 || mTargetHeight <= 0 || w <= 0 || h <= 0) return
        val scale = minOf(w.toFloat() / mTargetWidth, h.toFloat() / mTargetHeight)
        val dx = (w - mTargetWidth * scale) / 2f
        val dy = (h - mTargetHeight * scale) / 2f
        SurfaceControl.Transaction()
            .setCrop(surfaceControl, Rect(0, 0, mTargetWidth, mTargetHeight))
            .setScale(surfaceControl, scale, scale)
            .setPosition(surfaceControl, dx, dy)
            .apply()
        // view coords -> rear display coords
        touchMatrix.reset()
        touchMatrix.postTranslate(-dx, -dy)
        touchMatrix.postScale(1f / scale, 1f / scale)
    }

    private fun detachMirror() {
        mTargetSurfaceControl?.let {
            runCatching {
                SurfaceControl.Transaction().reparent(it, null).apply()
                it.release()
            }
        }

        mTargetSurfaceControl = null
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!mAllowInput) return false
        if (mDisplayID < 0 || mTargetSurfaceControl == null) return false

        try {
            // 变换触摸事件坐标
            event.transform(touchMatrix)
            InputEventExtend.setDisplayID(event, mDisplayID)
            InputManagerExtend.injectEvent(event)
        } catch (e: Exception) {
            Log.e(TAG, "inject touch error.", e)
        }

        return true
    }


    /**
     * Surface 回调。
     */
    private inner class SurfaceCallback : SurfaceHolder.Callback {

        override fun surfaceCreated(holder: SurfaceHolder) {
            if (mDisplayID >= 0) attachMirror()
        }

        override fun surfaceChanged(
            holder: SurfaceHolder,
            format: Int,
            width: Int,
            height: Int
        ) {
            mTargetSurfaceControl?.let { applyLayout(it, width, height) }
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            detachMirror()
        }
    }
}
