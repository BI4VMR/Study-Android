package net.bi4vmr.study.listenstate;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoListenStateUI extends AppCompatActivity {

    private ConnectivityManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        // Button btn = findViewById(R.id.btn01);

        // 初始化ConnectivityManager
        manager = getSystemService(ConnectivityManager.class);
        //
        // btn.setOnClickListener(v -> {
        //     // 该方法可以获取当前可用的网络信息，如果当前没有可用的网络连接，则返回空值。
        //     NetworkInfo info = manager.getActiveNetworkInfo();
        //     if (info == null) {
        //         Log.i("myapp", "当前无可用的网络连接！");
        //         return;
        //     }
        //
        //     // 获取连接详情
        //     Log.i("myapp", "Type Code: " + info.getType());
        //     Log.i("myapp", "Type Name: " + info.getTypeName());
        //     Log.i("myapp", "SubType Code: " + info.getSubtype());
        //     Log.i("myapp", "SubType Name: " + info.getSubtypeName());
        //     Log.i("myapp", "Reason: " + info.getReason());
        //
        //     // Network[] networks=manager.getAllNetworks();
        //     // for (Network net:networks){
        //     //     NetworkCapabilities s =manager.getNetworkCapabilities(net);
        //     //     // s.getTransportInfo();
        //     //     // TransportInfo t =s.getTransportInfo();
        //     // }
        // });

        // btn.setOnClickListener(v -> {
        // 该方法可以获取当前可用的网络信息，如果当前没有可用的网络连接，则返回空值。
        // NetworkInfo info = manager.getNetworkCapabilities()
        // if (info == null) {
        //     Log.i("myapp", "当前无可用的网络连接！");
        //     return;
        // }
        //
        // // 获取连接详情
        // Log.i("myapp", "getState: " + info.getState().name());
        // Log.i("myapp", "getTypeName: " + info.getTypeName());
        // Log.i("myapp", "getSubtypeName: " + info.getSubtypeName());
        // Log.i("myapp", "getReason: " + info.getReason());
        // });

        // manager.registerNetworkCallback();
        //
        // manager.registerDefaultNetworkCallback();
    }
}
