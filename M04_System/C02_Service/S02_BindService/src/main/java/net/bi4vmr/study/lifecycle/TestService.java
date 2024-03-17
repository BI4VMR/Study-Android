package net.bi4vmr.study.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Name          : TestService
 * Author        : BI4VMR
 * Date          : 2022-04-18 11:23
 * Description   : 测试服务。
 */
public class TestService extends Service {

    @Override
    public void onCreate() {
        Log.i("myapp", "OnCreate.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("myapp", "OnStartCommand.");
        return START_NOT_STICKY;
    }

    /**
     * Name        : onBind()
     * <p>
     * Description : 外部组件执行"bindService()"绑定服务时，本方法被调用。
     * 服务运行过程中，如果同一个组件多次请求绑定，"onBind()"方法只会在初次绑定时执行，后续系统直接向调用者返回之前
     * 创建的Binder实例。
     * <p>
     * 系统判断绑定服务的请求是否来自“同一个组件”的依据是：Intent属性是否相同，包括Action和Type，但不包括Extra。
     * 即使是不同APK中的组件，若使用具有相同属性的Intent绑定某个Service，系统也会返回同一个Binder实例。
     * 如果我们需要获取不同的Binder实例，可以使用"setAction()"和"setType()"方法为请求Intent设置不同的属性。
     *
     * @param intent 绑定服务的外部组件创建的Intent
     * @return 自定义Binder类的实例
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("myapp", "OnBind.");
        return new Binder();
    }

    /**
     * Name        : onUnbind()
     * <p>
     * Description : 当所有绑定服务的组件都调用"unbindService()"解绑后，此方法被调用。
     *
     * @param intent 绑定服务的外部组件创建的Intent
     * @return 默认值为"false"，参考"onRebind()"方法的注释。
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("myapp", "OnUnbind.");
        return false;
    }

    /**
     * Name        : onRebind()
     * <p>
     * Description : 如果"onUnbind()"返回"true"，且服务已经被所有组件解绑，再次被绑定时将会执行此方法。
     * 如果"onUnbind()"返回"false"，且服务已经被所有组件解绑，再次被绑定时不会调用任何生命周期方法。
     *
     * @param intent 绑定服务的外部组件创建的Intent
     */
    @Override
    public void onRebind(Intent intent) {
        Log.i("myapp", "OnRebind.");
    }

    @Override
    public void onDestroy() {
        Log.i("myapp", "OnDestroy.");
    }
}
