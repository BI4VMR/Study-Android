package net.bi4vmr.study.paramsync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import net.bi4vmr.aidl.IParamSync;
import net.bi4vmr.study.types.DownloadItem;

/**
 * 示例服务：参数同步测试服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
public class ParamSyncService extends Service {

    private static final String TAG = "TestApp-Server-" + ParamSyncService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceImpl();
    }

    /**
     * AIDL接口的实现类。
     */
    private static class ServiceImpl extends IParamSync.Stub {

        @Override
        public void modifyParams(DownloadItem p1, DownloadItem p2, DownloadItem p3) {
            Log.d(TAG, "ModifyParams start.");
            Log.d(TAG, "In Param P1: " + p1);
            Log.d(TAG, "Out Param P2: " + p2);
            Log.d(TAG, "In/Out Param P3: " + p3);

            // new Thread(() -> {
                try {
                    Thread.sleep(2000L);
                    Log.d(TAG, "ModifyParams sleep end.");
                    Log.d(TAG, "In Param P1: " + p1);
                    Log.d(TAG, "Out Param P2: " + p2);
                    Log.d(TAG, "In/Out Param P3: " + p3);

                    // 修改参数
                    p1.setId(999);
                    p1.setUrl("Server");
                    p2.setId(999);
                    p2.setUrl("Server");
                    p3.setId(999);
                    p3.setUrl("Server");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "ModifyParams end.");
                Log.d(TAG, "In Param P1: " + p1);
                Log.d(TAG, "Out Param P2: " + p2);
                Log.d(TAG, "In/Out Param P3: " + p3);
            // }).start();
        }
    }
}
