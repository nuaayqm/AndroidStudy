package com.example.mystudydemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mystudydemo01.Util.DataUtils;

public class TestButtonActivity extends AppCompatActivity{

    private Button b1, b2, b3;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_button);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);


        tv1 = findViewById(R.id.tv1);
        b1.setOnClickListener((View v) -> {
            b3.setEnabled(true);
        });
        b2.setOnClickListener((View v) -> {
            b3.setEnabled(false);
        });
        b3.setOnClickListener((View v) -> {
            tv1.setText(DataUtils.currentDateAndTime());
        });

    }
}