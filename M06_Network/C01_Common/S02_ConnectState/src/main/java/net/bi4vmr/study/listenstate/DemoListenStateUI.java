package net.bi4vmr.study.listenstate;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

import java.util.Arrays;

public class DemoListenStateUI extends AppCompatActivity {

    private ConnectivityManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_listenstate);

        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnUnregister = findViewById(R.id.btnUnregister);

        // 初始化ConnectivityManager
        manager = getSystemService(ConnectivityManager.class);
        MyNetworkCallback callback = new MyNetworkCallback();

        // 注册网络状态回调
        btnRegister.setOnClickListener(v -> {
            // 构造NetworkRequest对象，设置感兴趣的网络。
            NetworkRequest request = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .build();
            // 注册回调
            manager.registerNetworkCallback(request, callback);
        });

        // 注销网络状态回调
        btnUnregister.setOnClickListener(v -> manager.unregisterNetworkCallback(callback));
    }

    /*
     * 自定义网络状态监听器，用于接收各网络状态事件。
     */
    private static class MyNetworkCallback extends ConnectivityManager.NetworkCallback {

        @Override
        public void onAvailable(@NonNull Network network) {
            Log.i("myapp", "OnAvailable.");
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            Log.i("myapp", "OnCapabilitiesChanged.");

            // 判断连接类型
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("myapp", "OnCapabilitiesChanged. Type: WiFi");
            } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("myapp", "OnCapabilitiesChanged. Type: Mobile");
            }

            // 获取当前协议协商的上行带宽
            String BWup = networkCapabilities.getLinkDownstreamBandwidthKbps() + " Kbps";
            String BWdown = networkCapabilities.getLinkDownstreamBandwidthKbps() + " Kbps";
            Log.i("myapp", "OnCapabilitiesChanged. BandwidthUP: " + BWup);
            Log.i("myapp", "OnCapabilitiesChanged. BandwidthDown: " + BWdown);

            // 获取当前网络的能力集
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Log.i("myapp", "OnCapabilitiesChanged. Capabilities: " +
                        Arrays.toString(networkCapabilities.getCapabilities()));
            }
        }

        @Override
        public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
            Log.i("myapp", "OnLinkPropertiesChanged.");
            Log.i("myapp", "OnLinkPropertiesChanged. Interface:" + linkProperties.getInterfaceName());
        }

        @Override
        public void onBlockedStatusChanged(@NonNull Network network, boolean blocked) {
            Log.i("myapp", "OnBlockedStatusChanged. IsBlocked:" + blocked);
        }

        @Override
        public void onLosing(@NonNull Network network, int maxMsToLive) {
            Log.i("myapp", "OnLosing.");
        }

        @Override
        public void onLost(@NonNull Network network) {
            Log.i("myapp", "OnLost.");
        }

        @Override
        public void onUnavailable() {
            Log.i("myapp", "OnUnavailable.");
        }
    }
}
