package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.broadcastDemo.AlarmBroadcastActivity;
import com.example.myapplication.broadcastDemo.BroadcastStandardActivity;
import com.example.myapplication.intentDemo.A;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_ma).setOnClickListener(
                v -> startActivity(new Intent(this, A.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        );

        findViewById(R.id.btn_main_broadcast).setOnClickListener(
                v -> startActivity(new Intent(this, BroadcastStandardActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        );

        findViewById(R.id.btn_main_alarm_broadcast).setOnClickListener(
                v -> startActivity(new Intent(this, AlarmBroadcastActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        );

    }
}