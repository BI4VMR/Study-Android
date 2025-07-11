package net.bi4vmr.study;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * 自定义Application。
 * <p>
 * 在Application添加 `@HiltAndroidApp` 注解后，开启Hilt，并提供Application\Context等。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@HiltAndroidApp
public class MyApplication extends Application {
}
