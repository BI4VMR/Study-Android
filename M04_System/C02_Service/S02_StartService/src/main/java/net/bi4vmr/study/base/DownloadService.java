package net.bi4vmr.study.base;

import android.app.Service;
import android.content.Intent;
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

    private static final String TAG = "TestApp-" + DownloadService.class.getSimpleName();

    // 下载线程
    private Thread downloadThread;

    /**
     * 生命周期回调方法，服务首次被创建时该方法将被触发，用于进行初始化操作。
     * <p>
     * 只要服务实例没有被销毁，后续服务被启动或绑定时不会再触发此回调方法。
     */
    @Override
    public void onCreate() {
        Log.i(TAG, "OnCreate.");
    }

    /**
     * 生命周期回调方法，当外部组件调用 `startService()` 方法启动本服务时，该方法将被触发。
     * <p>
     * 外部组件每次调用 `startService()` 时本方法都会触发，但 `onCreate()` 方法仅会在服务未启动时被调用一次。
     *
     * @param intent  外部组件启动服务时创建的Intent，可能是空值。
     * @param flags   标志位。
     * @param startId 外部组件每次调用 `startService()` 方法都会生成不同的ID，可以配合 `stopSelf(startId)` 方法实现所有任务完成后
     *                终止服务。
     * @return 表示服务因内存不足而被系统强制关闭后的处理方式，取值为Service类中的 `START` 系列常量。
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "OnStartCommand.");

        // 获取外部组件传入的初始化数据
        String link = "";
        if (intent != null) {
            link = intent.getStringExtra("LINK");
        }
        Log.i(TAG, "下载地址：" + link);

        // 创建子线程执行耗时操作
        downloadThread = new Thread(() -> {
            Log.i(TAG, "下载开始");
            try {
                // 休眠5秒，模拟耗时操作。
                Thread.sleep(5000);
                Log.i(TAG, "下载完成");
            } catch (InterruptedException e) {
                Log.i(TAG, "任务终止");
            } finally {
                // 标记当前任务结束
                stopSelf(startId);
            }
        });
        // 启动异步任务
        downloadThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 生命周期回调方法，服务内部自行调用 `stopSelf()` 方法或被外部组件调用 `stopService()` 方法关闭时，该方法将被触发，用于释放资源。
     */
    @Override
    public void onDestroy() {
        Log.i(TAG, "OnDestroy.");
        // 如果服务被销毁，发送终止信号，停止异步任务。
        if (downloadThread != null) {
            downloadThread.interrupt();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 此服务不需要与调用者通信，因此将返回值设为"null"。
        return null;
    }
}
