package com.example.myapplication.serviceDemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.Util.DataUtils;

public class ServiceMainActivity extends AppCompatActivity implements View.OnClickListener{

    private static TextView textView;
    private Intent intentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
        textView = findViewById(R.id.tv_context);
        intentService = new Intent(this, MyService.class);

        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public static void showText(String text) {
        if (textView != null) {
            String s = textView.getText().toString();
            textView.setText(s + "\n" + DataUtils.currentDateAndTime() + "    " + text);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            startService(intentService);
        }
        else if (v.getId() == R.id.btn_stop) {
            stopService(intentService);
        }
    }
}