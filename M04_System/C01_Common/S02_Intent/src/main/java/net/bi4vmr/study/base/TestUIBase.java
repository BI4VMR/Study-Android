package net.bi4vmr.study.base;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> testSendData());
    }

    // 启动界面并传递数据
    private void testSendData() {
        binding.tvLog.append("\n--- 启动界面并传递数据 ---\n");
        Log.i(TAG, "--- 启动界面并传递数据 ---");

        // 获取NotificationManager实例
        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        // 通知渠道的ID与名称
        final String CHANNEL_ID = "net.bi4vmr.channel1";
        final String CHANNEL_NAME = "渠道1";

        // 构造通知Channel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(CHANNEL_NAME);
        notificationManager.createNotificationChannel(channel);

        Notification notification = new Notification.Builder(this, CHANNEL_ID)
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

        notificationManager.notify(1001, notification);
    }
}
