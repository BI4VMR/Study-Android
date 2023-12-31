package net.bi4vmr.study.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Name        : DownloadService
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@bi4vmr.net
 * <p>
 * Date        : 2023-03-15 15:42
 * <p>
 * Description : 启动服务示例。
 */
public class DownloadService extends Service {

    // 下载线程
    private Thread downloadThread;

    /**
     * Name        : onCreate()
     * Description : 服务首次被创建时调用此方法，用于进行初始化操作。只要服务组件没有被销毁，后续再启动服务不会再次
     * 调用本方法。
     */
    @Override
    public void onCreate() {
        Log.i("myapp", "OnCreate.");
    }

    /**
     * Name        : onStartCommand()
     * <p>
     * Description : 外部组件调用"startService()"等方法启动本服务时，将会执行本方法。无论服务当前是否已经被启动，
     * 外部每次调用"startService()"时都会触发本方法，而"onCreate()"方法仅会在第一次被调用。
     *
     * @param intent  外部组件启动服务时创建的Intent，可能是空值。
     * @param flags   标志位
     *                "0"
     *                服务正常启动时取值为数字0。
     *                "START_FLAG_REDELIVERY"
     *                服务被强制关闭后，自动重启时取此数值。
     *                "START_FLAG_RETRY"
     *                服务启动至"onStartCommand()"执行完毕（成功返回）前如果出错，再次启动将收到此数值。
     * @param startId 外部组件每次调用"startService()"方法都会生成不同的ID，如果本服务每次都创建新线程执行任务，
     *                任务完成后应该使用"stopSelf(startId)"终止服务，系统会在所有任务都结束后再销毁服务。
     *                这种场景下如果使用无参的"stopSelf()"方法，最先结束的任务会直接销毁整个服务实例，后续任务
     *                调用"stopSelf()"方法将会发生异常。
     * @return 表示服务因内存不足而被系统强制关闭后的处理方式，取值为Service类中的"START"系列常量：
     * "START_STICKY"
     * 若Service因内存不足而被系统强制关闭，后续内存空闲时，系统将重新创建此Service，并依次执行"onCreate()"和"on
     * StartCommand()"方法，但"onStartCommand()"中的Intent参数将为空值，除非配置了PendingIntent。
     * <p>
     * "START_NOT_STICKY"
     * 若Service因内存不足而被系统强制关闭，后续内存空闲时也不会自动启动，除非其它组件明确的调用"startService()"等
     * 方法。
     * <p>
     * "START_REDELIVER_INTENT"
     * 此数值与"START_STICKY"的行为一致，但系统会保存最后一个启动该服务的Intent，并在回调"onStartCommand()"方法
     * 时将Intent传入形参中，因此Intent参数不为空值。
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("myapp", "OnStartCommand.");

        // 取出外部传入的数据
        String link = intent.getStringExtra("LINK");
        Log.i("myapp", "下载地址：" + link);

        // 在子线程中进行耗时操作
        downloadThread = new Thread(() -> {
            Log.i("myapp", "下载开始");
            try {
                // 休眠5秒，模拟耗时操作。
                Thread.sleep(5000);
                Log.i("myapp", "下载完成");
            } catch (InterruptedException e) {
                Log.i("myapp", "任务终止");
            } finally {
                // 终止服务自身
                stopSelf(startId);
            }
        });
        // 启动任务
        downloadThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Name        : onDestroy()
     * <p>
     * Description : 服务自行关闭或被外部"stopService()"时将调用该方法，用于释放资源。
     */
    @Override
    public void onDestroy() {
        Log.i("myapp", "OnDestroy.");
        // 如果服务被销毁，发送终止信号，停止任务。
        downloadThread.interrupt();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 此服务不需要与调用者通信，因此将返回值设为"null"。
        return null;
    }
}
