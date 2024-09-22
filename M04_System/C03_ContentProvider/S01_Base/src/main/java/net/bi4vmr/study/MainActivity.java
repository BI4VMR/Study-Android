package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.MainActivityBinding;
import net.bi4vmr.study.uri.TestUIURI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = MainActivityBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        // URI
        Button btnBase = findViewById(R.id.btnURI);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIURI.class);
            startActivity(intent);
        });
    }
}
