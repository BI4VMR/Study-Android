package net.bi4vmr.study.listenstate;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

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
            NetworkRequest request = new NetworkRequest.Builder()
                    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                    .build();
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
            super.onAvailable(network);
            Log.i("myapp", "MyNetworkCallback:OnAvailable.");

        }

        @Override
        public void onLosing(@NonNull Network network, int maxMsToLive) {
            super.onLosing(network, maxMsToLive);
            Log.i("myapp", "MyNetworkCallback:OnLosing.");
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            Log.i("myapp", "MyNetworkCallback:OnLost.");
        }

        @Override
        public void onUnavailable() {
            super.onUnavailable();
            Log.i("myapp", "MyNetworkCallback:OnUnavailable.");
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            Log.i("myapp", "MyNetworkCallback:OnCapabilitiesChanged.");
        }

        @Override
        public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
            super.onLinkPropertiesChanged(network, linkProperties);
            Log.i("myapp", "MyNetworkCallback:OnLinkPropertiesChanged.");
        }

        @Override
        public void onBlockedStatusChanged(@NonNull Network network, boolean blocked) {
            super.onBlockedStatusChanged(network, blocked);
            Log.i("myapp", "MyNetworkCallback:OnBlockedStatusChanged.");
        }
    }
}
