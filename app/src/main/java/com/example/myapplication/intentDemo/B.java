package com.example.myapplication.intentDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.R;


public class B extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        TextView textView = findViewById(R.id.tv_b);
        try {
            textView.setText(getIntent().getExtras().getString("A"));
        }catch (RuntimeException e) {
            Log.d("MainActivity", e.toString());
        }


        findViewById(R.id.btn_b).setOnClickListener(
                v -> {
                    Bundle bundle = new Bundle();
                    bundle.putString("B", "Here is the message from B");
                    setResult(Activity.RESULT_OK, new Intent().putExtras(bundle));
                    finish();
                }
        );
    }
}