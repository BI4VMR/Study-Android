package net.bi4vmr.study.base;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

/**
 * 业务组件：HTTP接口管理。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class HTTPManager {

    private final String TAG = "HTTPManager";

    private final Context context;

    /**
     * 构造方法。
     * <p>
     * `@Inject` 注解表示该类以依赖注入方式管理，程序运行时Hilt会根据参数自动创建实例。
     *
     * @param context 上下文。 `@ApplicationContext` 表示使用应用级Context，即带有 `@HiltAndroidApp` 注解的Application实例。
     */
    @Inject
    public HTTPManager(
            @ApplicationContext Context context
    ) {
        this.context = context;
    }

    // 业务接口：登录
    public void login() {
        Log.i(TAG, "Login. App:[" + context.getPackageName() + "]");
    }
}
