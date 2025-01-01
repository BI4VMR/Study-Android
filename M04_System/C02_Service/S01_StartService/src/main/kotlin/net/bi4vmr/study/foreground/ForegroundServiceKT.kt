package net.bi4vmr.study.foreground

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import android.util.Log
import net.bi4vmr.study.R
import net.bi4vmr.study.base.TestUIBase

/**
 * 示例服务：前台服务。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class ForegroundServiceKT : Service() {

    companion object {
        private val TAG: String = "TestApp-${ForegroundServiceKT::class.simpleName}"
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "OnCreate.")

        // 创建通知
        val notification: Notification = createNotification()

        /*
         * 后台服务转为前台模式，并绑定通知。
         *
         * @param[id]           通知的唯一标识。
         * @param[notification] 通知实例。
         */
        startForeground(102, notification)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "OnStartCommand.")
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "OnDestroy.")

        /*
         * 终止前台服务。
         *
         * 该方法只会使服务从前台状态转为后台状态，并不会终止整个服务，因此我们可以在其他业务逻辑中按需调用它。
         *
         * @param[removeNotification] 是否移除通知。
         */
        stopForeground(true)
    }

    // 创建持久通知
    private fun createNotification(): Notification {
        // 通知渠道的ID与名称
        val channelID = "net.bi4vmr.study"
        val channelName = "BI4VMR"

        // 构造通知Channel
        val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        // 设置PendingIntent（可选）
        val nIntent = Intent(this, TestUIBase::class.java)
        // 指定动作为打开TestUIBase
        val pIntent = PendingIntent.getActivity(this, 0, nIntent, PendingIntent.FLAG_IMMUTABLE)

        // 构造通知
        val builder = Notification.Builder(applicationContext, channelID)
        builder.setChannelId(channelID)
            .setContentIntent(pIntent)
            // 设置图标
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.ic_funny_256
                )
            )
            // 设置通知栏小图标
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            // 设置标题
            .setContentTitle("前台服务")
            // 设置描述文字
            .setContentText("测试前台服务与持久通知")
            // 设置显示时间
            .setWhen(System.currentTimeMillis())
        return builder.build()
    }
}
