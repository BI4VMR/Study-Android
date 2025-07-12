package net.bi4vmr.study.base;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

/**
 * TODO 添加简述。
 * <p>
 * TODO 添加详情。
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
     * `@Inject` 注解表示该类可以Hilt管理。
     * @param context
     */
    @Inject
    public HTTPManager(
            @ApplicationContext Context context
    ) {
        this.context = context;
    }

    // 业务接口：登录
    public void login() {
        context.getApplicationInfo();
        Log.i(TAG, "Login." + context.getPackageName());
    }
}
