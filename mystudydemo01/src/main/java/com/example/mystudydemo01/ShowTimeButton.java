package com.example.mystudydemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mystudydemo01.Util.DataUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ShowTimeButton extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_time_button);
        tv = findViewById(R.id.setClock);
        Button button = findViewById(R.id.timeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setBackgroundColor(Color.WHITE);
                tv.setText(DataUtils.currentDateAndTime());
            }
        });

    }
}