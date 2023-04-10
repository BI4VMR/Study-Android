package net.bi4vmr.study.media_demo_musicplayer.manager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import net.bi4vmr.study.media_demo_musicplayer.common.MusicApp;
import net.bi4vmr.study.media_demo_musicplayer.ui.MainActivity;

/**
 * Name          : NotificationManager
 * Author        : 詹屹罡
 * Email         : yigangzhan@pateo.com.cn
 * Date          : 2022-04-19 11:04
 * Description   : 通知管理器
 */
public class MediaNotificationManager {

    private static final String TAG_APP = MusicApp.TAG;

    private static MediaNotificationManager instance;
    private final Context mContext;

    // 单例模式，对外部禁用构造方法。
    private MediaNotificationManager(Context context) {
        // 获取应用级别的Context，其它实例可能会导致内存泄漏。
        mContext = context.getApplicationContext();
    }

    // 获取MusicManager实例
    public static MediaNotificationManager getInstance(Context context) {
        if (instance == null) {
            instance = new MediaNotificationManager(context);
        }
        return instance;
    }

    // 创建持久通知
    public Notification createNotification() {
        // 通知渠道的ID与名称
        final String CHANNEL_ID = "net.bi4vmr.study";
        final String CHANNEL_NAME = "BI4VMR";

        // 构造通知Channel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        android.app.NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

        // 设置PendingIntent
        Intent nIntent = new Intent(mContext, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(mContext, 0, nIntent, PendingIntent.FLAG_IMMUTABLE);
        // 构造通知
        Notification.Builder builder = new Notification.Builder(mContext, CHANNEL_ID);
        builder.setChannelId(CHANNEL_ID)
                .setContentIntent(pIntent)
                // 设置图标
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), mContext.getResources().getIdentifier("ic_funny_256", "mipmap", mContext.getPackageName())))
                // 设置通知栏小图标
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                // 设置标题
                .setContentTitle("音乐播放器")
                // 设置描述文字
                .setContentText("播放器前台服务，防止界面关闭播放停止。")
                // 设置显示时间
                .setWhen(System.currentTimeMillis());
        return builder.build();
    }
}
