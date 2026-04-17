package net.bi4vmr.tool.android.ui.xrecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class XRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        val action = e.actionMasked
        if (action == MotionEvent.ACTION_POINTER_DOWN) {
            return true
        }
        return super.onInterceptTouchEvent(e)
    }
}
