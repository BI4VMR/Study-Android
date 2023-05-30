package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    private ConnectivityManager manager;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        Button btnQuery1 = findViewById(R.id.btnQuery1);
        Button btnQuery2 = findViewById(R.id.btnQuery2);
        TextView tvInfo = findViewById(R.id.tvInfo);

        // 初始化ConnectivityManager
        manager = getSystemService(ConnectivityManager.class);

        // 按钮被点击时，查询当前的网络状态。
        btnQuery1.setOnClickListener(v -> {
            // 该方法用于获取当前可用的网络信息，如果当前没有可用的网络连接，则返回空值。
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info == null) {
                Log.i("myapp", "当前无可用的网络连接！");
                tvInfo.setText("当前无可用的网络连接！");
                return;
            }

            /* 获取连接详情 */
            // 类型代码
            Log.i("myapp", "Type Code: " + info.getType());
            // 类型名称
            Log.i("myapp", "Type Name: " + info.getTypeName());
            // 子类型代码
            Log.i("myapp", "SubType Code: " + info.getSubtype());
            // 子类型名称
            Log.i("myapp", "SubType Name: " + info.getSubtypeName());

            // 更新控件显示
            String text = "Type Code: [" + info.getType() + "]" +
                    "\nType Name: [" + info.getTypeName() + "]" +
                    "\nSubType Code: [" + info.getSubtype() + "]" +
                    "\nSubType Name: [" + info.getSubtypeName() + "]";
            tvInfo.setText(text);

            /* 判断网络类型 */
            // 当前连接是否为WiFi
            boolean isWiFi = (info.getType() == ConnectivityManager.TYPE_WIFI);
            // 当前连接是否为移动网络
            boolean isMobile = (info.getType() == ConnectivityManager.TYPE_MOBILE);

            Log.i("myapp", "WiFi: " + isWiFi + "; Mobile: " + isMobile);
            tvInfo.append("\nWiFi: " + isWiFi + "; Mobile: " + isMobile);
        });

        // 按钮被点击时，查询当前的网络状态。
        btnQuery2.setOnClickListener(v -> {
            // 获取当前活跃的网络
            Network net = manager.getActiveNetwork();
            // 该对象为空时，说明无可用连接。
            if (net == null) {
                Log.i("myapp", "当前无可用的网络连接！");
                tvInfo.setText("当前无可用的网络连接！");
                return;
            }

            // 获取当前网络的能力集
            NetworkCapabilities capabilities = manager.getNetworkCapabilities(net);
            // 该对象为空时，说明连接就绪，但没有任何能力，这种情况也是无法正常通信的。
            if (capabilities == null) {
                Log.i("myapp", "当前的网络连接不可用！");
                tvInfo.setText("当前的网络连接不可用！");
                return;
            }

            /* 判断网络类型 */
            // 当前连接是否为WiFi
            boolean isWiFi = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
            // 当前连接是否为移动网络
            boolean isMobile = capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);

            Log.i("myapp", "WiFi: " + isWiFi + "; Mobile: " + isMobile);
            tvInfo.setText("WiFi: " + isWiFi + "; Mobile: " + isMobile);

            // 当前连接是否按量计费
            boolean isMetered = !capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
            Log.i("myapp", "连接是否按量计费：" + isMetered);
            tvInfo.append("\n连接是否按量计费：" + isMetered);

            // 当前连接是否拥有认证机制
            boolean isPortal = capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_CAPTIVE_PORTAL) ||
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            Log.i("myapp", "连接是否拥有认证机制：" + isPortal);
            tvInfo.append("\n连接是否拥有认证机制：" + isPortal);

            // 当前连接是否为VPN
            boolean isVPN = !capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN);
            Log.i("myapp", "连接是否为VPN：" + isVPN);
            tvInfo.append("\n连接是否为VPN：" + isVPN);
        });
    }
}
