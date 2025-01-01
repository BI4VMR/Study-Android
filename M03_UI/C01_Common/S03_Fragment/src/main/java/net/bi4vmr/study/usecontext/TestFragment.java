package net.bi4vmr.study.usecontext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import net.bi4vmr.study.R;

/**
 * Name        : TestFragment
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-05-30 22:40
 * Description : 测试Fragment
 */
public class TestFragment extends Fragment {

    private Context mContext;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 此处可以保存Application的Context以便后续使用，最好不要保存Context本身，因为Fragment生命周期比Activity短，可能会导致内存泄漏。
        mContext = context.getApplicationContext();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView tv = view.findViewById(R.id.tvContent);
        tv.setText("获取网络数据...");

        /* 假设此Fragment需要联网获取数据，并在结果返回时弹出提示消息。 */

        /*
         * 以下是错误示范：
         *
         * 在回调触发时获取了Context，此时Fragment可能已经从Activity分离了，这种情况会导致异常。
         */
        // 延时5秒执行，模拟耗时操作。
        // handler.postDelayed(() -> {
        //     // 获取Context
        //     Context ctx = requireContext();
        //     // 显示Toast
        //     Toast.makeText(ctx, "Test", Toast.LENGTH_SHORT)
        //             .show();
        // }, 5000L);

        /*
         * 以下是正确示范：
         *
         * 在回调外面提前获取Context，等到触发时直接使用。
         */
        // 延时5秒执行，模拟耗时操作。
        handler.postDelayed(() -> {
            // 使用 "onAttach()" 方法中保存的Context，显示Toast。
            Toast.makeText(mContext, "Test", Toast.LENGTH_SHORT)
                    .show();
        }, 5000L);
        return view;
    }
}
