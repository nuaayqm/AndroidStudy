package com.example.myapplication.intentDemo;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

import org.w3c.dom.Text;


public class A extends AppCompatActivity {
    private ActivityResultLauncher<Intent> mLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Bundle bundle = new Bundle();
        bundle.putString("A", "This is the message from A");

        mLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getData() != null && result.getResultCode() == Activity.RESULT_OK) {
                Bundle extras = result.getData().getExtras();
                ((TextView) findViewById(R.id.tv_a)).setText(extras.getString("B"));
            }
        });




        findViewById(R.id.btn_a).setOnClickListener(
                v -> mLauncher.launch(new Intent(this, B.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtras(bundle))
        );
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null && requestCode == 0 && resultCode == Activity.RESULT_OK) {
//            Bundle bundle = data.getExtras();
//            ((TextView) findViewById(R.id.tv_a)).setText(bundle.getString("B"));
//        }
//    }
}