package net.bi4vmr.study.antimultitouchrecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class AntiMultiTouchRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        val action = e.actionMasked
        if (action == MotionEvent.ACTION_POINTER_DOWN) {
            // 不再分发给下层的View。
            return true
        }
        return super.onInterceptTouchEvent(e)
    }
}
