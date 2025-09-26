package net.bi4vmr.study.base;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.bi4vmr.study.R;

import java.util.List;

/**
 * RecyclerView的适配器。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private static final String TAG = "TestApp-" + MyAdapter.class.getSimpleName();

    /**
     * 数据源。
     */
    private final List<SimpleVO> dataSource;

    /**
     * 构造方法。
     *
     * @param dataSource 初始数据源。
     */
    public MyAdapter(List<SimpleVO> dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * RecyclerView创建ViewHolder的回调方法。
     * <p>
     * 当RecyclerView创建新的ViewHolder时，将会回调此方法。我们应当在此处创建表项对应的View，并封装进ViewHolder返回给RecyclerView。
     *
     * @param parent   当前表项将要被放入的视图容器。
     * @param viewType 待创建的View类型。
     * @return ViewHolder实例。
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "OnCreateViewHolder. ViewType:[" + viewType + "]");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        /*
         * 将布局文件实例化为View对象。
         *
         * 此处的第三参数必须为"false"，因为控件将由ViewHolder控制Attach与Detach。
         */
        View view = inflater.inflate(R.layout.list_item_simple, parent, false);

        // 创建ViewHolder实例，并将View对象保存在其中。
        return new MyViewHolder(view);
    }

    /**
     * RecyclerView将数据与ViewHolder绑定的回调方法。
     * <p>
     * 当RecyclerView将View显示到屏幕上之前，将会回调此方法。我们需要从数据源中根据位置索引找到对应的数据项，然后通过ViewHolder设置各个
     * 控件，实现View与数据的同步。
     *
     * @param holder   ViewHolder实例。
     * @param position 表项在列表中的位置索引。
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i(TAG, "OnBindViewHolder. Position:[" + position + "]");
        holder.bindData();
    }

    /**
     * RecyclerView获取表项总数的回调方法。
     *
     * @return 表项总数。
     */
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * ViewHolder
     * <p>
     * 自定义ViewHolder类，内部保存了View实例，便于复用。
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {

        // 保存控件的引用，以便后续绑定数据。
        TextView tvTitle;
        ImageView ivIcon;

        /**
         * 构造方法。
         * <p>
         * 初始化ViewHolder，获取各控件的引用，并保存在全局变量中，便于后续使用。
         *
         * @param itemView View实例。
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }

        /**
         * 绑定数据。
         * <p>
         * 取出数据源集合中与当前表项位置对应的数据项，并更新View中的控件。
         */
        public void bindData() {
            // 获取当前表项位置对应的数据项
            SimpleVO vo = dataSource.get(getAdapterPosition());
            // 将数据设置到视图中
            if (tvTitle != null) {
                tvTitle.setText(vo.getTitle());
            }
        }
    }
}
