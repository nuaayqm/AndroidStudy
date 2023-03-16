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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Util.DataUtils;

public class AlarmBroadcastActivity extends AppCompatActivity {
    private final String ALARM_ACTION = "ALARM";
    private AlarmReceiver alarmReceiver;
    private long mDelay;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_broadcast);


        findViewById(R.id.btn_alarm).setOnClickListener(v -> {
            TextView tv = findViewById(R.id.tv_set);
            tv.setText(DataUtils.currentDateAndTime() + "  设置闹钟");
            mDelay = 5;
            sendAlarm();
        });
    }

    private void sendAlarm() {
        Intent intent = new Intent(ALARM_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long delayTime = System.currentTimeMillis() + 1000 * mDelay;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, delayTime, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, delayTime, pendingIntent);
        }


    }


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