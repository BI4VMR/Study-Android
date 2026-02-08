package net.bi4vmr.bookkeeper.ui.common

import androidx.recyclerview.widget.RecyclerView

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestRVSpinnerAdapter(
    private val rvAdapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>
) : RVSpinnerAdapter<RecyclerView.ViewHolder>() {

    override fun onLoadRecyclerViewAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder> = rvAdapter

    override fun onLoadSelectedItemPosition(): Int {
        return 0
    }
}
