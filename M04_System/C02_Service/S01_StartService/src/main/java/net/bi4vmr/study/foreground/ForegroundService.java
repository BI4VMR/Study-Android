package net.bi4vmr.study.foreground;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import net.bi4vmr.study.R;
import net.bi4vmr.study.base.TestUIBase;

/**
 * 示例服务：前台服务。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class ForegroundService extends Service {

    private static final String TAG = "TestApp-" + ForegroundService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.i(TAG, "OnCreate.");

        // 创建通知
        Notification notification = createNotification();

        /*
         * 后台服务转为前台模式，并绑定通知。
         *
         * @param id 通知的唯一标识。
         * @param notification 通知实例。
         */
        startForeground(101, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "OnStartCommand.");
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy.");

        /*
         * 终止前台服务。
         *
         * 该方法只会使服务从前台状态转为后台状态，并不会终止整个服务，因此我们可以在其他业务逻辑中按需调用它。
         *
         * @param removeNotification 是否移除通知。
         */
        stopForeground(true);
    }

    // 创建持久通知
    private Notification createNotification() {
        // 通知渠道的ID与名称
        final String CHANNEL_ID = "net.bi4vmr.study";
        final String CHANNEL_NAME = "BI4VMR";

        // 构造通知Channel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

        // 设置PendingIntent（可选）
        Intent nIntent = new Intent(this, TestUIBase.class);
        // 指定动作为打开TestUIBase
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, nIntent, PendingIntent.FLAG_IMMUTABLE);

        // 构造通知
        Notification.Builder builder = new Notification.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setChannelId(CHANNEL_ID)
                .setContentIntent(pIntent)
                // 设置图标
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_funny_256))
                // 设置通知栏小图标
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                // 设置标题
                .setContentTitle("前台服务")
                // 设置描述文字
                .setContentText("测试前台服务与持久通知")
                // 设置显示时间
                .setWhen(System.currentTimeMillis());
        return builder.build();
    }
}
