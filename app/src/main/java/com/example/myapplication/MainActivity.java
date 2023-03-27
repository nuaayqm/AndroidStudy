package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activityDemo.RegisterActivity;
import com.example.myapplication.broadcastDemo.AlarmBroadcastActivity;
import com.example.myapplication.broadcastDemo.BroadcastStandardActivity;
import com.example.myapplication.editDemo.EditActivity;
import com.example.myapplication.intentDemo.A;
import com.example.myapplication.serviceDemo.ServiceMainActivity;

import lombok.Data;

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

        findViewById(R.id.btn_main_service).setOnClickListener(
                v -> startActivity(new Intent(this, ServiceMainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        );


        findViewById(R.id.btn_main_register).setOnClickListener(
                v -> startActivity(new Intent(this, RegisterActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        );

        findViewById(R.id.btn_to_editDemo).setOnClickListener(
                v -> startActivity(new Intent(this, EditActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        );

    }
}