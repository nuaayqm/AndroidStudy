package com.alibaba.mystudydemo02;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.example.mystudydemo01.R;

import org.w3c.dom.Text;

public class ImplicitIntentTest extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent_test);

        ExampleFragment fg = (ExampleFragment) getSupportFragmentManager().findFragmentByTag("my_frag");

        TextView tv = (TextView) fg.getView().findViewById(R.id.f1_tv1);

        tv.setText("new text");


        Button button = findViewById(R.id.buttonToApp);
        button.setOnClickListener(this);

        Button buttonAlarm = findViewById(R.id.buttonAlarm);
        buttonAlarm.setOnClickListener(this);

        textView = findViewById(R.id.tv_intent);

    }

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonToApp:
                Intent intentJump = new Intent();
                intentJump.setAction(Intent.ACTION_EDIT);
                intentJump.setAction(Intent.ACTION_VIEW);

                Intent chooser = Intent.createChooser(intentJump, "Choose other app to open");

                if (intentJump.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
                break;
            case R.id.buttonAlarm:
                Intent intentAlarm = new Intent(AlarmClock.ACTION_SET_TIMER);
                intentAlarm.putExtra(AlarmClock.EXTRA_MESSAGE, "Alarm 10 seconds")
                        .putExtra(AlarmClock.EXTRA_LENGTH, 10)
                        .putExtra(AlarmClock.EXTRA_SKIP_UI, false);

                Intent chooser2 = Intent.createChooser(intentAlarm, "Choose other app to open");

//                startActivity(chooser2);
//                PackageManager pm = getPackageManager();

                if ((intentAlarm.resolveActivity(getPackageManager())) != null) {
                    textView.setText("成功打开计时器");
                    startActivity(intentAlarm);
                } else {
                    textView.setText("未打开计时器");
                }

                break;
        }

    }
}