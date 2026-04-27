package net.bi4vmr.study;

import android.app.Application;
import android.content.Context;

import me.weishu.reflection.Reflection;

/**
 * TODO 添加简述。
 * <p>
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Reflection.unseal(base);
    }
}
