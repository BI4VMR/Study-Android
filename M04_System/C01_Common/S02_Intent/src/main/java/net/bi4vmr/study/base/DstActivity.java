package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.DestActivityBinding;

public class DstActivity extends AppCompatActivity {

    private static final String TAG = "TestApp-" + DstActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DestActivityBinding binding = DestActivityBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // 从Intent中取出Bundle对象
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // 使用Key取出对应的值
            String id = bundle.getString("KEY_ID");
            String name = bundle.getString("KEY_NAME");
            float price = bundle.getFloat("KEY_PRICE", -1F);
            boolean isSoldout = bundle.getBoolean("KEY_SOLDOUT", true);

            String bookInfo = "ID:" + id + "\n名称:" + name + "\n价格:" + price + "\n售空:" + isSoldout;
            Log.i(TAG, bookInfo);
            binding.tvInfo.setText(bookInfo);
        }
    }
}
