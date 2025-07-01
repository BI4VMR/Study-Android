package net.bi4vmr.study.exceptions;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import net.bi4vmr.aidl.IExceptions;

/**
 * 示例服务：异常传递测试服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
public class ExceptionTestService extends Service {

    private static final String TAG = "TestApp-Server-" + ExceptionTestService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceImpl();
    }

    /**
     * AIDL接口的实现类。
     */
    private static class ServiceImpl extends IExceptions.Stub {

        @Override
        public int divide(int a, int b) {
            return a / b;
        }
    }
}
