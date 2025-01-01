package net.bi4vmr.study.base.analogclock

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import net.bi4vmr.study.databinding.AnalogClockViewBinding
import java.time.Instant
import java.time.LocalTime
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * 模拟时钟控件。
 *
 * 支持秒级指针平滑显示，可以自由替换素材并设置缩放，用于取代原生的AnalogClock。
 *
 * 设计参考：[FsClock-Android](https://github.com/schorschii/FsClock-Android)
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class AnalogClock @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    companion object {
        // 刷新间隔（缓慢）
        private const val INTERVAL_SLOW = 1000L
        // 刷新间隔（快速）
        private const val INTERVAL_FAST = 100L
    }

    private val binding: AnalogClockViewBinding =
        AnalogClockViewBinding.inflate(LayoutInflater.from(context), this, true)
    private var executor: ScheduledExecutorService? = null

    // 秒针是否需要显示
    private var needShowSecondPointer = true
    // 秒针是否平滑移动
    private var secondPointerSmoothMove = false

    /* ----- 公开方法 ----- */

    fun setDialBackgroundResource(@DrawableRes resID: Int) {
        binding.ivDial.setBackgroundResource(resID)
    }


    fun setDialBackgroundColor(@ColorInt color: Int) {
        binding.ivDial.setBackgroundColor(color)
    }

    fun setDialImageResource(@DrawableRes resID: Int) {
        binding.ivDial.setImageResource(resID)
    }

    fun setDialImageDrawable(drawable: Drawable?) {
        binding.ivDial.setImageDrawable(drawable)
    }

    fun setHourImageResource(@DrawableRes resID: Int) {
        binding.ivHourPointer.setImageResource(resID)
    }

    fun setHourImageBitmap(bm: Bitmap?) {
        binding.ivHourPointer.setImageBitmap(bm)
    }

    fun setHourImageScale(multiple: Float) {
        setViewScale(binding.ivHourPointer, multiple)
    }

    fun setMinuteImageResource(@DrawableRes resID: Int) {
        binding.ivMinutePointer.setImageResource(resID)
    }

    fun setMinuteImageBitmap(bm: Bitmap?) {
        binding.ivMinutePointer.setImageBitmap(bm)
    }

    fun setMinuteImageScale(multiple: Float) {
        setViewScale(binding.ivMinutePointer, multiple)
    }

    fun setSecondImageResource(@DrawableRes resID: Int) {
        binding.ivSecondPointer.setImageResource(resID)
    }

    fun setSecondImageBitmap(bm: Bitmap?) {
        binding.ivSecondPointer.setImageBitmap(bm)
    }

    fun setSecondImageScale(multiple: Float) {
        setViewScale(binding.ivSecondPointer, multiple)
    }


    fun getSecondPointerShowState(): Boolean {
        return needShowSecondPointer
    }

    fun setSecondPointerShowState(newState: Boolean) {
        needShowSecondPointer = newState
        // 该属性被更新后，需要刷新秒针控件的显示与隐藏。
        updateSecondPointerShowState()
    }

    fun isSecondPointerSmoothMove(): Boolean {
        return secondPointerSmoothMove
    }

    /**
     * 是否开启秒针平滑移动。
     *
     * @param[newState] "true"表示开启秒针平滑移动；"false"表示关闭秒针平滑移动。
     */
    fun setSecondPointerSmoothMove(newState: Boolean) {
        synchronized(this) {
            if (secondPointerSmoothMove == newState) {
                return
            }

            secondPointerSmoothMove = newState
            stopTasks()
            startTasks()
        }
    }

    /* ----- 重写方法 ----- */

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startTasks()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopTasks()
    }

    /* ----- 内部方法 ----- */

    // 更新指针位置
    private fun updatePointerAngle() {
        val localTime: LocalTime = LocalTime.now();
        val second: Int = localTime.second
        val minute: Int = localTime.minute
        val hour: Int = localTime.hour

        /*
         * 计算旋转角度
         *
         * 时、分、秒的计算方法是相似的，此处以时针为例进行说明：
         * 首先"360/12"可以得出“每个小时的角度”，“当前小时 * 每个小时的角度”可以得出当前时针旋转的角度，但此时缺少
         * 分钟数所造成的额外偏转角度。“当前小时 + (当前分钟 / 每小时的分钟数)”即为包含小数的精确小时数，用它再乘以
         * “每个小时的角度”即为精确的偏转角度。
         */
        val hourDegree: Float = (hour + (minute / 60.0F)) * (360.0F / 12)
        val minDegree: Float = (minute + (second / 60.0F)) * (360.0F / 60)
        // 如果已开启秒针平滑移动，则需要计算毫秒带来的偏转角度。
        val secDegree: Float = if (secondPointerSmoothMove) {
            val milli: Long = Instant.now().toEpochMilli() % 1000
            (second + (milli / 1000.0F)) * (360.0F / 60)
        } else {
            second * (360.0F / 60)
        }

        with(binding) {
            ivHourPointer.rotation = hourDegree
            ivMinutePointer.rotation = minDegree
            ivSecondPointer.rotation = secDegree
        }
    }

    // 更新秒针的显示状态
    private fun updateSecondPointerShowState() {
        if (needShowSecondPointer) {
            binding.ivSecondPointer.visibility = VISIBLE
        } else {
            binding.ivSecondPointer.visibility = GONE
            // 无需显示秒针时，不必使用高频刷新，可以节约性能。
            secondPointerSmoothMove = false
        }
    }

    // 开启定时任务
    private fun startTasks() {
        if (executor == null) {
            executor = Executors.newScheduledThreadPool(1)
        }

        val interval = if (secondPointerSmoothMove) INTERVAL_FAST else INTERVAL_SLOW
        executor?.scheduleWithFixedDelay({ updatePointerAngle() }, 0, interval, TimeUnit.MILLISECONDS)
    }

    // 关闭定时任务
    private fun stopTasks() {
        executor?.shutdown()
        executor = null
    }

    /**
     * 等比缩放控件尺寸。
     *
     * @param[view] 控件。
     * @param[multiple] 缩放倍数。
     */
    private fun setViewScale(view: View, multiple: Float) {
        view.scaleX = multiple
        view.scaleY = multiple
    }
}
