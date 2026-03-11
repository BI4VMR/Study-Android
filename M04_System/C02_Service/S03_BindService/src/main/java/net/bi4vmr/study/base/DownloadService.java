package net.bi4vmr.study.base;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * 示例服务：下载管理服务。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class DownloadService extends Service {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private DownloadImpl downloadImpl = null;

    @Override
    public void onCreate() {
        Log.i(TAG, "OnCreate.");
    }

    /**
     * 生命周期回调方法，当外部组件调用 `bindService()` 方法绑定本服务时，该方法将被触发。
     * <p>
     * 服务运行过程中，如果同一个组件多次请求绑定， `onBind()` 方法只会在初次绑定时执行，后续系统直接向调用者返回之前
     * 创建的Binder实例。
     * <p>
     * 系统判断绑定服务的请求是否来自“同一个组件”的依据是：Intent属性是否相同，包括Action和Type，但不包括Extra。
     * 即使是不同APK中的组件，若使用具有相同属性的Intent绑定某个Service，系统也会返回同一个Binder实例。
     * 如果我们需要获取不同的Binder实例，可以使用 `setAction()` 和 `setType()` 方法为请求Intent设置不同的属性。
     *
     * @param intent 绑定服务的外部组件创建的Intent。
     * @return 自定义Binder类的实例。
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "OnBind.");
        // 创建服务对象，并返回给调用者。
        downloadImpl = new DownloadImpl();
        return downloadImpl;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "OnDestroy.");
        if (downloadImpl != null) {
            downloadImpl.stopTask();
        }
    }

    // 回调接口
    public interface CallBack {
        void onDataChanged(double percent);
    }

    /**
     * Binder的实现类。
     */
    static class DownloadImpl extends Binder {

        // 下载线程
        private Thread downloadThread;
        // 回调接口的实现类，用于向客户端反馈结果。
        private CallBack callback;

        private final float TOTAL = 100.0F;
        private float length = 0.0F;

        // 设置回调
        public void setCallback(CallBack callback) {
            this.callback = callback;
        }

        // 添加下载任务
        public void addTask(String url) {
            // 在子线程中进行耗时操作
            downloadThread = new Thread(() -> {
                Log.i(TAG, "下载开始：" + url);
                try {
                    while (length < TOTAL) {
                        length += 10;
                        if (callback != null) {
                            callback.onDataChanged((length / TOTAL) * 100);
                        }
                        // 休眠1秒，模拟耗时操作。
                        Thread.sleep(1000);
                    }
                    Log.i(TAG, "下载完成");
                } catch (InterruptedException e) {
                    Log.i(TAG, "任务终止");
                }
            });
            // 启动任务
            downloadThread.start();
        }

        // 停止任务
        public void stopTask() {
            if (downloadThread != null) {
                downloadThread.interrupt();
            }
        }
    }
}
