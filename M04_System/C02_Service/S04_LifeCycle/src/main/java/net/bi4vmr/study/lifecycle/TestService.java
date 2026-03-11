package net.bi4vmr.study.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Name        : TestService
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2022-04-18 11:23
 * <p>
 * Description : 生命周期测试服务。
 */
public class TestService extends Service {

    private static final String TAG = "TestApp-" + TestService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.i(TAG, "OnCreate.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "OnStartCommand.");
        return START_NOT_STICKY;
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
        return new Binder();
    }

    /**
     * 当所有与Service绑定的外部组件都解除绑定关系后，该方法被触发。
     *
     * @param intent 绑定服务的外部组件创建的Intent。
     * @return 默认值为 `false` ，参考 `onRebind()` 方法的注释。
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "OnUnbind.");
        return false;
    }

    /**
     * 如果 `onUnbind()` 方法返回 `true` ，且Service已经被所有外部组件解绑；当Service再次被外部组件绑定时将会触发此方法。
     * <p>
     * 如果 `onUnbind()` 返回 `false` ，且Service已经被所有外部组件解绑；当Service再次被外部组件绑定时则不会触发此方法。
     *
     * @param intent 绑定服务的外部组件创建的Intent。
     */
    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "OnRebind.");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "OnDestroy.");
    }
}
