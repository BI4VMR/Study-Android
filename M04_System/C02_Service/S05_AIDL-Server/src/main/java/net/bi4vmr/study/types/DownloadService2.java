package net.bi4vmr.study.types;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import net.bi4vmr.aidl.IDownloadService2;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例服务：文件下载服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
public class DownloadService2 extends Service {

    private static final String TAG = "TestApp-Server-" + DownloadService2.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return new DownloadImpl();
    }

    /**
     * AIDL接口的实现类。
     */
    private static class DownloadImpl extends IDownloadService2.Stub {

        // 任务集合
        private final List<DownloadItem> tasks = new ArrayList<>();

        /**
         * 添加下载任务。
         *
         * @param item 下载项实体类。
         */
        @Override
        public void addTask(DownloadItem item) {
            Log.d(TAG, "AddTask. Item:" + item);
            // 在此处实现下载业务，此处省略。
            tasks.add(item);
        }

        /**
         * 查询下载任务。
         *
         * @return 任务列表。
         */
        @Override
        public List<DownloadItem> getTasks() {
            Log.d(TAG, "GetTasks.");
            return tasks;
        }
    }
}
