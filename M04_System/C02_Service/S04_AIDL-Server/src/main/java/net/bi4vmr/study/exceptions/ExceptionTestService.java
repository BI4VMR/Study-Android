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

        // 计算除法
        @Override
        public int divide(int a, int b) {
            return a / b;
        }

        // 计算除法（已传递原始异常）
        @Override
        public int divide2(int a, int b) {
            try {
                return a / b;
            } catch (ArithmeticException e) {
                // 捕获AIDL不支持的异常，并重新抛出IllegalArgumentException。
                throw new IllegalArgumentException("除数不能为零！", e);
            }
        }
    }
}
