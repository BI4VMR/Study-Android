package net.bi4vmr.study.callback;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

import net.bi4vmr.aidl.IDownloadService3;
import net.bi4vmr.aidl.callback.TaskCallback;
import net.bi4vmr.study.types.DownloadItem;
import net.bi4vmr.study.types.ItemBean;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

        // 保存所有任务
        private final List<ItemBean> tasks = new CopyOnWriteArrayList<>();
        // 回调接口的实现类，用于向客户端反馈结果。
        private TaskCallback callback;

        /**
         * Name        : 添加下载任务
         * <p>
         * Description : 添加下载任务并开始执行。
         * <p>
         * 此方法为AIDL同步方法，执行时会阻塞客户端的调用线程，因此我们创建新线程进行耗时操作。
         *
         * @param item 下载任务Bean
         */
        @Override
        public void addTask(ItemBean item) {
            tasks.add(item);
            // 创建新线程模拟下载过程
            new Thread(() -> {
                final float TOTAL = 100.0F;
                int length = 0;

                try {
                    Log.i(TAG, "开始下载:" + item.getUrl());
                    while (length < TOTAL) {
                        length += 10;
                        // 设置进度并通过回调通知客户端
                        item.setPercent((length / TOTAL) * 100);
                        callback.onStateChanged(item);
                        // 休眠1秒，模拟耗时操作。
                        Thread.sleep(1000);
                    }
                    Log.i(TAG, "下载完成。");
                } catch (InterruptedException e) {
                    Log.w(TAG, "任务终止。");
                } catch (RemoteException e) {
                    Log.e(TAG, "发生远程错误！");
                }
            }).start();
        }

        @Override
        public void addTaskAsync(DownloadItem task) throws RemoteException {

        }

        @Override
        public void addTask(DownloadItem task) throws RemoteException {

        }

        /**
         * Name        : 添加下载任务
         * <p>
         * Description : 添加下载任务并开始执行。
         * <p>
         * 此方法为AIDL异步方法，Binder将会自动分配线程执行，不会阻塞客户端的调用线程，因此可以直接进行耗时操作。
         *
         * @param item 下载任务Bean
         */
        @Override
        public void addTaskAsync(ItemBean item) {
            tasks.add(item);
            final float TOTAL = 100.0F;
            int length = 0;

            try {
                Log.i(TAG, "开始下载:" + item.getUrl());
                while (length < TOTAL) {
                    length += 10;
                    // 设置进度并通过回调通知客户端
                    item.setPercent((length / TOTAL) * 100);
                    callback.onStateChanged(item);
                    // 休眠1秒，模拟耗时操作。
                    Thread.sleep(1000);
                }
                Log.i(TAG, "下载完成。");
            } catch (InterruptedException e) {
                Log.w(TAG, "任务终止。");
            } catch (RemoteException e) {
                Log.e(TAG, "发生远程错误！");
            }
        }

        @Override
        public void setTaskCallback(TaskCallback cb) {
            callback = cb;
        }
    }
}
