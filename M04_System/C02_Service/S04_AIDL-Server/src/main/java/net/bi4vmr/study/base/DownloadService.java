package net.bi4vmr.study.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import net.bi4vmr.aidl.IDownloadService;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例服务：文件下载服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
public class DownloadService extends Service {

    private static final String TAG = "TestApp-Server-" + DownloadService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return new DownloadImpl();
    }

    /**
     * AIDL接口的实现类。
     */
    private static class DownloadImpl extends IDownloadService.Stub {

        // 任务集合
        private final List<String> tasks = new ArrayList<>();

        /**
         * 获取服务端进程ID。
         *
         * @return 服务端的进程ID
         */
        @Override
        public int getPID() {
            int pid = Process.myPid();
            Log.d(TAG, "GetPID. PID:[" + pid + "]");
            return pid;
        }

        /**
         * 添加下载任务。
         *
         * @param url 目标地址。
         */
        @Override
        public void addTask(String url) {
            Log.d(TAG, "AddTask. URL:[" + url + "]");
            // 在此处实现下载业务，此处省略。
            tasks.add(url);
        }

        /**
         * 查询下载任务。
         *
         * @return 任务列表。
         */
        @Override
        public List<String> getTasks() {
            Log.d(TAG, "GetTasks.");
            return tasks;
        }
    }
}
