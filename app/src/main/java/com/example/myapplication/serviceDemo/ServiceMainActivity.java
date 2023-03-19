package com.example.myapplication.serviceDemo;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.Util.DataUtils;

public class ServiceMainActivity extends AppCompatActivity implements View.OnClickListener{

    private static TextView textView;
    private Intent intentService;
    private Intent intentBindService;
    private MyBindService myBindService;
    private MyBindService.LocalBinder mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
        intentService = new Intent(this, MyService.class);
        intentBindService = new Intent(this, MyBindService.class);

        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
        findViewById(R.id.btn_bind).setOnClickListener(this);
        findViewById(R.id.btn_unbind).setOnClickListener(this);
        findViewById(R.id.btn_get_service_time).setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    public static void showText(String text) {
        if (textView != null) {
            String s = textView.getText().toString();
            textView.setText(s + "\n" + DataUtils.currentDateAndTime() + "    " + text);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            if (textView == null || textView.getId() != R.id.btn_start)
                textView = findViewById(R.id.tv_context);
            startService(intentBindService);
        }
        else if (v.getId() == R.id.btn_stop) {
            stopService(intentBindService);
        } else if (v.getId() == R.id.btn_bind) {
            if (textView == null || textView.getId() != R.id.btn_bind)
                textView = findViewById(R.id.tv_bind);
            bindService(intentBindService, mConn, Context.BIND_AUTO_CREATE);
        } else if (v.getId() == R.id.btn_unbind) {
            unbindService(mConn);
            myBindService = null;
        }else if (v.getId() == R.id.btn_get_service_time) {
            ((TextView) findViewById(R.id.tv_3)).setText(mBinder.getTime() + ":  从后台服务获取");
        }
    }

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MyBindService.LocalBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBinder = null;
            Log.d("mBinder", String.valueOf(mBinder));
        }
    };
}