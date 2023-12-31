package net.bi4vmr.study.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    private static final int MSG_01 = 1;
    private static final int MSG_02 = 2;
    // private final Handler handler = new Handler();
    private Handler handler =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        //
        // handler.post()
        // handler.obtainMessage();

        new Thread() {
            @Override
            public void run() {
                super.run();
                handler=new Handler(Looper.myLooper());
            }
        }.start();

        Button btn=findViewById(R.id.btn01);
        btn.setOnClickListener(v -> {
            handler.sendEmptyMessage(100);
        });
    }

    class MyHandler extends Handler {

        public MyHandler(@NonNull Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_01:
                    Log.i("myapp", "MyHandler处理消息MSG_01");
                    break;
                case MSG_02:
                    Log.i("myapp", "MyHandler处理消息MSG_02");
                    break;
            }
        }
    }
}
