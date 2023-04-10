package net.bi4vmr.study.media_lib_mediaprovider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btAudio = findViewById(R.id.btAudio);
        btAudio.setOnClickListener(v -> startActivity(new Intent(this, AudioActivity.class)));
    }
}