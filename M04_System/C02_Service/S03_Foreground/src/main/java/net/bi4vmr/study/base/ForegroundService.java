package net.bi4vmr.study.base;

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

/**
 * Name        : ForegroundService
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-03-15 15:42
 * <p>
 * Description : 前台服务示例。
 */
public class ForegroundService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("myapp", "OnStartCommand.");

        // 创建通知
        Notification notification = createNotification();

        /*
         * Name        : startForeground()
         * Description : 开启前台服务
         *
         * @param id 服务的唯一标识
         * @param notification 要显示的通知
         */
        startForeground(100, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myapp", "OnDestroy.");

        /*
         * Name        : stopForeground()
         * Description : 终止前台服务
         *
         * @param removeNotification 是否移除通知
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

        // 设置PendingIntent
        Intent nIntent = new Intent(this, DemoBaseUI.class);
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
