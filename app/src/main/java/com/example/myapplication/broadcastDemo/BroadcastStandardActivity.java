package com.example.myapplication.broadcastDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import

import com.example.myapplication.R;
import com.example.myapplication.Util.DataUtils;

import java.text.SimpleDateFormat;


public class BroadcastStandardActivity extends AppCompatActivity {
    private final static String STANDARD_ACTION =
            "com.example.standard";
    private final static String ORDERED_ACTION =
            "com.example.ordered";
    CheckBox ck_abort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_standard);
        ck_abort = findViewById(R.id.check_box);

        Intent intent = new Intent();
        intent.setAction(ORDERED_ACTION);

        findViewById(R.id.btn_bc).setOnClickListener(v -> sendOrderedBroadcast(
                    intent, null
                )
        );

    }

    private class StandardReceiver extends BroadcastReceiver {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(STANDARD_ACTION)) {
                TextView textView = findViewById(R.id.tv_result_A);
                textView.setText(DataUtils.currentDateAndTime() + " :收到一个标准广播");
            }
        }
    }

    private class OrderedReceiverA extends BroadcastReceiver {

        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ORDERED_ACTION)) {
                TextView textView = findViewById(R.id.tv_result_A);
                textView.setText(DataUtils.currentDateAndTime() + " :收到一个标准广播");
                if (ck_abort.isChecked()) {
                    abortBroadcast();
                }
            }
        }
    }
    private class OrderedReceiverB extends BroadcastReceiver {

        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ORDERED_ACTION)) {
                TextView textView = findViewById(R.id.tv_result_B);
                textView.setText(DataUtils.currentDateAndTime() + " :收到一个标准广播");
                if (ck_abort.isChecked()) {
                    abortBroadcast();
                }
            }
        }
    }

    private StandardReceiver standardReceiver;

    @Override
    protected void onStart() {
        super.onStart();

        OrderedReceiverA orderedReceiverA = new OrderedReceiverA();
        IntentFilter intentFilterA = new IntentFilter(ORDERED_ACTION);
        intentFilterA.setPriority(8);
        registerReceiver(orderedReceiverA, intentFilterA);

        OrderedReceiverB orderedReceiverB = new OrderedReceiverB();
        IntentFilter intentFilterB = new IntentFilter(ORDERED_ACTION);
        intentFilterB.setPriority(10);
        registerReceiver(orderedReceiverB, intentFilterB);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}

