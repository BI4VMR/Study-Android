package net.bi4vmr.study.base;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        // 获取NotificationManager实例
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // 通知渠道的ID与名称
        final String CHANNEL_ID = "net.bi4vmr.channel1";
        final String CHANNEL_NAME = "渠道1";

        final String CHANNEL_ID2 = "net.bi4vmr.channel2";
        final String CHANNEL_NAME2 = "渠道2";

        // 构造通知Channel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(CHANNEL_NAME);
        notificationManager.createNotificationChannel(channel);

        NotificationChannel channel2 = new NotificationChannel(CHANNEL_ID2, CHANNEL_NAME2, NotificationManager.IMPORTANCE_NONE);
        channel2.setDescription(CHANNEL_NAME2);
        notificationManager.createNotificationChannel(channel2);

        Notification n1 = new Notification.Builder(this, CHANNEL_ID)
                // 设置图标
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_funny_256))
                // 设置通知栏小图标
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                // 设置标题
                .setContentTitle("前台服务1")
                // 设置描述文字
                .setContentText("测试前台服务与持久通知1")
                // 设置显示时间
                .setWhen(System.currentTimeMillis())
                .build();

        Notification n2 = new Notification.Builder(this, CHANNEL_ID2)
                // 设置图标
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_funny_256))
                // 设置通知栏小图标
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                // 设置标题
                .setContentTitle("前台服务2")
                // 设置描述文字
                .setContentText("测试前台服务与持久通知2")
                // 设置显示时间
                .setWhen(System.currentTimeMillis())
                .build();

        notificationManager.notify(1001, n1);
        notificationManager.notify(1002, n2);
    }
}
