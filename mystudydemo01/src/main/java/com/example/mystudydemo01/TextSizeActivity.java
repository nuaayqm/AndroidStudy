package com.example.mystudydemo01;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TextSizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_size);
        TextView textView = findViewById(R.id.tv_hello);
        textView.setTextSize(50);
        textView.setTextColor(getColor(R.color.white));
        textView.setBackgroundColor(getColor(R.color.black));

        textView.setHeight(800);
        textView.setWidth(800);

        TextView tv2 = findViewById(R.id.tv_demo2);
        tv2.setBackgroundColor(Color.GREEN);
        ViewGroup.LayoutParams layoutParams = tv2.getLayoutParams();
        layoutParams.width = 800;
        tv2.setLayoutParams(layoutParams);

    }
}