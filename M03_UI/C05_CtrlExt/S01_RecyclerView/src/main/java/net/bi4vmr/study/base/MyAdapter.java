package net.bi4vmr.study.base;

import android.content.Context;
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

    // 上下文环境
    private final Context mContext;
    // 数据源
    private final List<SimpleVO> dataSource;

    /**
     * Name        : 构造方法
     * <p>
     * Description : 构造方法。
     *
     * @param context    上下文环境
     * @param dataSource 初始数据源
     */
    public MyAdapter(Context context, List<SimpleVO> dataSource) {
        this.mContext = context.getApplicationContext();
        this.dataSource = dataSource;
    }

    /**
     * Name        : onCreateViewHolder()
     * <p>
     * Description : 创建ViewHolder对象的回调方法。
     * <p>
     * 我们应当在此处创建表项的View实例，并将其封装进ViewHolder对象返回给RecyclerView。
     *
     * @param parent   当前Item将要被放入的视图容器
     * @param viewType 待创建的View类型
     * @return 构建完成的ViewHolder实例
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 将布局文件实例化为View对象
        View view = LayoutInflater.from(mContext)
                // 此处的第三参数必须为"false"，因为控件将由ViewHolder控制Attach与Detach。
                .inflate(R.layout.list_item_simple, parent, false);
        // 创建ViewHolder实例，并将View对象保存在其中。
        return new MyViewHolder(view);
    }

    /**
     * Name        : onBindViewHolder()
     * <p>
     * Description : 将数据与ViewHolder绑定的回调方法。
     * <p>
     * 我们需要从数据源中根据位置索引找到对应的条目，然后将数据通过ViewHolder设置给各个控件，实现UI与数据的同步。
     *
     * @param holder   ViewHolder实例
     * @param position Item在列表中的位置索引
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData();
    }

    /**
     * Name        : getItemCount()
     * <p>
     * Description : 获取列表项总数的回调方法。
     *
     * @return 表项的数量
     */
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    /**
     * ViewHolder
     * <p>
     * ViewHolder类，保存View实例，用于快速复用视图。
     */
    class MyViewHolder extends RecyclerView.ViewHolder {

        /* 保存控件的引用，以便后续绑定数据。 */
        TextView tvTitle;
        ImageView ivIcon;

        /**
         * Name        : 构造方法
         * <p>
         * Description : 初始化ViewHolder，获取各控件的引用，并保存在全局变量中，便于后续使用。
         *
         * @param itemView View实例
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivIcon = itemView.findViewById(R.id.ivIcon);
        }

        /**
         * 绑定数据。
         * <p>
         * 取出数据源集合中与当前Item位置一致的数据项，并更新到View中的控件。
         */
        public void bindData() {
            // 获取当前Item位置对应的数据项
            SimpleVO vo = dataSource.get(getAdapterPosition());
            // 将数据设置到视图中
            tvTitle.setText(vo.getTitle());
        }
    }
}
