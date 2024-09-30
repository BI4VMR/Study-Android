package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.uri.TestUIURI;
import net.bi4vmr.study.uri.TestUIURIKT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // URI
        binding.btnURI.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIURI.class);
            startActivity(intent);
        });

        // URI(KT)
        binding.btnURIKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIURIKT.class);
            startActivity(intent);
        });
    }
}
