package com.example.myapplication.broadcastDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Util.DataUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AlarmBroadcastActivity extends AppCompatActivity {
    private final String TAG = "AlarmBroadcastActivity";
    private final String ALARM_ACTION = "ALARM";
    private AlarmReceiver alarmReceiver;
    private long mDelay;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_broadcast);
        initDelaySpinner();

        findViewById(R.id.btn_alarm).setOnClickListener(v -> sendAlarm());
    }

    private void sendAlarm() {
        TextView tv = findViewById(R.id.tv_set);
        tv.setText(DataUtils.currentDateAndTime() + "  设置闹钟");
        Log.d(TAG, "sendAlarm: Here is ok?");
        Intent intent = new Intent(ALARM_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long delayTime = System.currentTimeMillis() + 1000 * mDelay;
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, delayTime, pendingIntent);
    }
    ArrayAdapter<String> delayAdapter;

    // 初始化闹钟延迟的下拉框
    private void initDelaySpinner() {
        Collections.addAll(delayDescArray, "5秒", "10秒", "15秒", "20秒", "25秒", "30秒");
        delayAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, delayDescArray);
        Spinner sp_delay = findViewById(R.id.sp_delay);
        sp_delay.setPrompt("请选择闹钟延迟");
        sp_delay.setAdapter(delayAdapter);
        sp_delay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "go position: " + position);
                mDelay = delayArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sp_delay.setSelection(1);
    }

    private int[] delayArray = {5, 10, 15, 20, 25, 30};
    private ArrayList<String> delayDescArray = new ArrayList<>();



    private class AlarmReceiver extends BroadcastReceiver {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                ((TextView) findViewById(R.id.tv_arrive)).setText(DataUtils.currentDateAndTime() + "  闹钟广播到达");

                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);
            }

            if (((CheckBox) findViewById(R.id.cb_alarm)).isChecked()) {
                sendAlarm();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        alarmReceiver = new AlarmReceiver();
        IntentFilter intentFilter = new IntentFilter(ALARM_ACTION);
        registerReceiver(alarmReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(alarmReceiver);
    }
}