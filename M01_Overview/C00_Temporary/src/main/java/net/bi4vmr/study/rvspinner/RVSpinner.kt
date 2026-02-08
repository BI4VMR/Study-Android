package net.bi4vmr.bookkeeper.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import net.bi4vmr.bookkeeper.R
import net.bi4vmr.tool.android.common.logcat.LogUtil

/**
 * RecyclerView 版 Spinner。
 *
 * - 常态下显示"已选中"的表项视图（你可以用 ViewHolder 自定义样式）。
 * - 点击后弹出 PopupWindow，内部使用 RecyclerView 显示下拉列表。
 *
 * 适配器需实现 [RVSpinnerAdapter]，分别提供"已选中"和"下拉列表"两种 ViewHolder 的创建/绑定逻辑。
 */
class RVSpinner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        const val SELECT_INVALID = -1
    }

    private val viewRoot: View = inflate(context, R.layout.view_rvspinner, this)
    private val viewContainer: FrameLayout = viewRoot.findViewById(R.id.layout_container)
    private val viewClick: View = viewRoot.findViewById(R.id.view_click)

    private val popupWindow: PopupWindow by lazy { initPopupWindow() }
    private val recyclerview: RecyclerView by lazy { popupWindow.contentView.findViewById(R.id.recyclerview) }

    private var adapter: RVSpinnerAdapter<out RecyclerView.ViewHolder>? = null

    private var selectedIndex: Int = SELECT_INVALID

    /**
     * 当前选中项视图。
     *
     * 用于监听尺寸变化（例如文本换行、字体缩放、异步内容刷新等），从而驱动 RVSpinner 重新测量/布局。
     */
    private var selectedView: View? = null

    private val selectedViewLayoutChangeListener =
        View.OnLayoutChangeListener { _, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            val newW = right - left
            val newH = bottom - top
            val oldW = oldRight - oldLeft
            val oldH = oldBottom - oldTop
            if (newW != oldW || newH != oldH) {
                LogUtil.d("Selected view size changed: ${oldW}x${oldH} -> ${newW}x${newH}")
                // 让外层高度/宽度能跟随内容变化（wrap_content 时尤为关键）
                requestLayout()
                invalidate()
            }
        }

    init {
        viewClick.setOnClickListener {
            popupWindow.showAsDropDown(this)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 先测量容器视图以获取准确的高度
        if (viewContainer.childCount > 0) {
            viewContainer.measure(
                MeasureSpec.makeMeasureSpec(measuredWidth - paddingLeft - paddingRight, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )

            // 设置 RVSpinner 的最终高度为容器视图的高度
            val containerHeight = viewContainer.measuredHeight
            val finalHeight = containerHeight + paddingTop + paddingBottom

            LogUtil.d("RVSpinner measuring: container=$containerHeight, padding=${paddingTop + paddingBottom}, final=$finalHeight")
            setMeasuredDimension(measuredWidth, finalHeight)
        } else {
            // 没有子视图时使用默认测量结果
            LogUtil.d("RVSpinner measuring: no child views")
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        // 确保容器视图正确布局
        if (changed && viewContainer.childCount > 0) {
            LogUtil.d("RVSpinner layout changed, height: ${bottom - top}")
        }
    }

    fun setAdapter(adapter: RVSpinnerAdapter<out RecyclerView.ViewHolder>) {
        this.adapter = adapter
        recyclerview.adapter = adapter.onLoadRecyclerViewAdapter()

        selectedIndex = SELECT_INVALID
        selectedView?.removeOnLayoutChangeListener(selectedViewLayoutChangeListener)
        selectedView = null

        viewContainer.removeAllViews()
        popupWindow.dismiss()

        // adapter 变化后，尺寸可能也会变化。
        requestLayout()
        LogUtil.d("Adapter set, requesting layout")

        // adapter.setSelectListener(object : RVSpinnerAdapterListener {
        //     override fun onItemSelected(position: Int) {
        //         LogUtil.d("onItemSelected p:$position")
        //     }
        // })
    }

    fun setSelectedIndex(position: Int) {
        popupWindow.dismiss()

        val adapter = adapter
        if (adapter != null) {
            // 更新选中序号
            selectedIndex = position

            // 从适配器获取选中项View实例并显示
            selectedView?.removeOnLayoutChangeListener(selectedViewLayoutChangeListener)

            viewContainer.removeAllViews()
            val view = adapter.onLoadSelectedItemView(this, position).apply {
                // 防御：部分 itemView 可能没有 layoutParams，导致测量不稳定。
                if (layoutParams == null) {
                    layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                } else {
                    // 确保宽度填满容器，高度自适应
                    layoutParams.width = LayoutParams.MATCH_PARENT
                    layoutParams.height = LayoutParams.WRAP_CONTENT
                }
                addOnLayoutChangeListener(selectedViewLayoutChangeListener)
            }
            selectedView = view
            viewContainer.addView(view)

            LogUtil.d("Selected index set to $position, view added to container")

            // 关键：立刻触发一次重测/重排，让高度尽快更新。
            viewContainer.requestLayout()
            requestLayout()
            invalidate()
            // adapter?.getRecyclerViewAdapter().
        }
    }

    private fun updateSelectedItemView() {
        viewContainer.removeAllViews()

        if (selectedIndex < 0) {
            return
        }

        adapter?.let {
            val view = it.onLoadSelectedItemView(this, selectedIndex).apply {
                // 防御：部分 itemView 可能没有 layoutParams，导致测量不稳定。
                // if (layoutParams == null) {
                //     layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                // } else {
                //     // 确保宽度填满容器，高度自适应
                //     layoutParams.width = LayoutParams.MATCH_PARENT
                //     layoutParams.height = LayoutParams.WRAP_CONTENT
                // }
                // addOnLayoutChangeListener(selectedViewLayoutChangeListener)
            }
            viewContainer.addView(view)
        }
    }

    private fun initPopupWindow(): PopupWindow {
        val popupView = inflate(context, R.layout.view_rvspinner_popup, null)
        return PopupWindow(popupView).apply {
            width = LayoutParams.WRAP_CONTENT
            height = LayoutParams.WRAP_CONTENT
            isFocusable = true
            isOutsideTouchable = true
            setOnDismissListener {
                adapter?.let {
                    val newIndex: Int = it.onLoadSelectedItemPosition()
                    if (selectedIndex != newIndex) {
                        selectedIndex = newIndex
                        updateSelectedItemView()
                    }
                }
            }
        }
    }
}
