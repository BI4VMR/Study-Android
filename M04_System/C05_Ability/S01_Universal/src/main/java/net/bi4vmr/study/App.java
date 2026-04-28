package net.bi4vmr.study;

import android.app.Application;
import android.content.Context;

import me.weishu.reflection.Reflection;

/**
 * 自定义Application。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 解除Android 10及以上版本系统对隐藏接口的访问限制。
        Reflection.unseal(base);
    }
}
