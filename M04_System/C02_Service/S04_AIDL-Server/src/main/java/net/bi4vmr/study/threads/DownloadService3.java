package net.bi4vmr.study.threads;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import net.bi4vmr.aidl.IDownloadService3;
import net.bi4vmr.aidl.callback.TaskCallback;
import net.bi4vmr.study.types.DownloadItem;

/**
 * 示例服务：文件下载服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
public class DownloadService3 extends Service {

    private static final String TAG = "TestApp-Server-" + DownloadService3.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceImpl();
    }

    private static class ServiceImpl extends IDownloadService3.Stub {

        // 回调接口实现，用于向客户端反馈结果。
        private TaskCallback callback;

        /**
         * 客户端注册回调接口。
         *
         * @param cb 回调接口实现。
         */
        @Override
        public void setTaskCallback(TaskCallback cb) {
            Log.d(TAG, "SetTaskCallback.");
            callback = cb;
        }

        /**
         * 添加下载任务。
         * <p>
         * 此方法为AIDL同步方法，执行时会阻塞客户端的调用线程，因此我们创建新线程进行耗时操作。
         *
         * @param item 下载任务。
         */
        @Override
        public void addTask(DownloadItem item) {
            Log.d(TAG, "AddTask.");

            // 创建新线程模拟下载过程
            new Thread(() -> {
                final float TOTAL = 100.0F;
                int length = 0;

                try {
                    Log.i(TAG, "开始下载:" + item.getUrl());
                    while (length < TOTAL) {
                        length += 10;
                        // 更新进度
                        item.setPercent((length / TOTAL) * 100);
                        // 并通过回调通知客户端
                        if (callback != null) {
                            callback.onStateChanged(item);
                        }
                        // 休眠1秒，模拟耗时操作。
                        Thread.sleep(1000);
                    }
                    Log.i(TAG, "下载完成。");
                } catch (InterruptedException e) {
                    Log.w(TAG, "任务终止。");
                } catch (RemoteException e) {
                    Log.e(TAG, "发生远程调用错误！");
                }
            }).start();
        }

        /**
         * 添加下载任务。
         * <p>
         * 此方法为AIDL异步方法，Binder将会自动分配线程执行，不会阻塞客户端的调用线程，因此可以直接进行耗时操作。
         *
         * @param item 下载任务。
         */
        @Override
        public void addTaskOneway(DownloadItem item) {
            Log.d(TAG, "AddTaskOneway.");

            final float TOTAL = 100.0F;
            int length = 0;

            try {
                Log.d(TAG, "开始下载:" + item.getUrl());
                while (length < TOTAL) {
                    length += 10;
                    // 设置进度并通过回调通知客户端
                    item.setPercent((length / TOTAL) * 100);
                    callback.onStateChanged(item);
                    // 休眠1秒，模拟耗时操作。
                    Thread.sleep(1000);
                }
                Log.d(TAG, "下载完成。");
            } catch (InterruptedException e) {
                Log.w(TAG, "任务终止。");
            } catch (RemoteException e) {
                Log.e(TAG, "发生远程错误！");
            }
        }
    }
}
