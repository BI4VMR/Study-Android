package net.bi4vmr.study.textclock;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.widget.AnalogClock;
import android.widget.TextClock;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class TestUITextClock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_textclock);

        TextClock textClock = findViewById(R.id.textClock);
        textClock.setFormat24Hour("HH:mm:ss");
        SpannableString ss = new SpannableString("HH:mm:ss");
        ss.setSpan(new BackgroundColorSpan(Color.RED),0,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textClock.setFormat24Hour(ss);
    }
}
