package net.bi4vmr.study.style;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import net.bi4vmr.study.R;

/**
 * Name        : MyDialog
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-05-07 16:27
 * <p>
 * Description : DialogFragment示例
 */
public class MyDialog extends DialogFragment {

    public static MyDialog newInstance() {
        return new MyDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myapp", "OnCreate.");
        setStyle(STYLE_NORMAL, R.style.DialogFullScreen);
    }

    /* 使用自定义View构建对话框 */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("myapp", "OnCreateView.");
        View view = inflater.inflate(R.layout.mydialog, container, false);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("标题");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("myapp", "OnStart.");
        // 获取窗体属性对象
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        // 设置宽高属性
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        // 设置位置属性
        params.gravity = Gravity.TOP;
        // 设置窗口周围阴影不透明度
        params.dimAmount = 0.3F;
        // 应用修改
        getDialog().getWindow().setAttributes(params);
    }
}
