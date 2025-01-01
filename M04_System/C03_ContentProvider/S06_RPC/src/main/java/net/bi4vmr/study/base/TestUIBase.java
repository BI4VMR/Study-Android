package net.bi4vmr.study.base;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();
    private static final String URI_TEST = "content://net.bi4vmr.provider.testobserver/test";

    private TestuiBaseBinding binding;

    private DataObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnNotify.setOnClickListener(v -> testNotify());
        binding.btnRegister.setOnClickListener(v -> testRegister());
        binding.btnUnregister.setOnClickListener(v -> testUnregister());

        // 初始化DataObserver，在主线程执行回调。
        observer = new DataObserver(new Handler(Looper.getMainLooper()));
    }

    // 服务端发出数据变更通知
    private void testNotify() {
        binding.tvLog.append("\n--- 服务端发出数据变更通知 ---\n");
        Log.i(TAG, "--- 服务端发出数据变更通知 ---");

        getContentResolver().notifyChange(Uri.parse(URI_TEST), null);
    }

    // 注册监听器
    private void testRegister() {
        binding.tvLog.append("\n--- 注册监听器 ---\n");
        Log.i(TAG, "--- 注册监听器 ---");

        /*
         * Name        : 注册监听器
         *
         * Description : 注册监听器。
         *
         * @param uri                  需要监听的URI。
         * @param notifyForDescendants 是否接收关联数据的变化通知。
         * 如果该属性被设为"true"，并监听"content://net.bi4vmr.provider.testobserver/test"；若"test"的
         * 子记录"content://net.bi4vmr.provider.testobserver/test/1"发生变化，则该监听器也会被触发。
         * @param observer             Observer实现类的实例。
         */
        getContentResolver().
                registerContentObserver(Uri.parse(URI_TEST), false, observer);
    }

    // 注销监听器
    private void testUnregister() {
        binding.tvLog.append("\n--- 注销监听器 ---\n");
        Log.i(TAG, "--- 注销监听器 ---");

        getContentResolver().
                unregisterContentObserver(observer);
    }

    /*
     * ContentObserver实现类
     */
    private class DataObserver extends ContentObserver {

        /**
         * Name        : 构造方法
         * <p>
         * Description : 构造方法。
         *
         * @param handler 工作Handler，用于控制"onChange()"回调触发时，使用何种线程执行操作。
         */
        public DataObserver(Handler handler) {
            super(handler);
        }

        /**
         * Name        : 回调方法：数据发生改变
         * <p>
         * Description : 回调方法：数据发生改变。
         *
         * @param selfChange 通常不使用该参数。
         * @param uri        发生变化的数据URI。
         */
        @Override
        public void onChange(boolean selfChange, @Nullable Uri uri) {
            super.onChange(selfChange, uri);
            binding.tvLog.append("DataObserver-OnChange. URI: " + uri);
            Log.i(TAG, "DataObserver-OnChange. URI: " + uri);
        }
    }
}
