package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {

        });

        Button btnTextClock = findViewById(R.id.btnTextClock);
        btnTextClock.setOnClickListener(v -> {
            // Intent intent = new Intent(this, TestUITextClock.class);
            // startActivity(intent);
        });

        Button btnView = findViewById(R.id.btnView);
        btnView.setOnClickListener(v -> {
            // Intent intent = new Intent(this, TestUIView.class);
            // startActivity(intent);
        });

        binding.ivDraw.setOnClickListener(v -> {
            View v2 = LayoutInflater.from(this).inflate(R.layout.popup_subjects, null);
            PopupWindow window = new PopupWindow(v2, 200, ViewGroup.LayoutParams.WRAP_CONTENT);
            // PopupWindow window = new PopupWindow(v2);
            // window.setContentView(v2);
            window.setFocusable(true);
            // window.update();
            // window.update(0,0,0,0);
            // window.setOverlapAnchor(true);
            // window.showAsDropDown(v);
            window.showAsDropDown(v);
            // window.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        });
        // binding.ivDraw.setElevation(32F);
        // binding.ivDraw.setImageDrawable(null);
        // binding.ivDraw.setOutlineSpotShadowColor(Color.GREEN);
        // binding.ivDraw.setOutlineAmbientShadowColor(Color.BLUE);
        // binding.ivDraw.setOutlineProvider(new ViewOutlineProvider() {
        //     @Override
        //     public void getOutline(View view, Outline outline) {
        //         outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 22232F);
        //     }
        // });
        // binding.ivDraw.setClipToOutline(true);

        // Log.i("Test123", "addOnActiveSessionsChangedListener: ");
        // MediaSessionManager manager = getSystemService(MediaSessionManager.class);
        // manager.addOnActiveSessionsChangedListener(new MediaSessionManager.OnActiveSessionsChangedListener() {
        //     @Override
        //     public void onActiveSessionsChanged(@Nullable List<MediaController> controllers) {
        //         if (controllers != null) {
        //             for (MediaController controller : controllers) {
        //                 Log.i("Test123", "onActiveSessionsChanged: " + controller.getPackageName());
        //
        //             }
        //         }
        //     }
        // }, null);


        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });
    }
}
