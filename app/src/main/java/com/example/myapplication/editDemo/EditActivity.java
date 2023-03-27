package com.example.myapplication.editDemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public class EditActivity extends AppCompatActivity implements View.OnFocusChangeListener{
    private EditText et_phone;
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);

        et_phone.addTextChangedListener(new MyTextWatcher(et_phone, 11));
        et_phone.setOnFocusChangeListener(this);
        et_pwd.addTextChangedListener(new MyTextWatcher(et_pwd, 6));
        et_pwd.setOnFocusChangeListener(this);

        findViewById(R.id.btn_backward).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("尊敬的用户");
            builder.setMessage("你真的要返回上一层吗?");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("留下来!", (a, b) -> {});
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.et_pwd && hasFocus) {
            if (et_phone.getText().toString().length() < 11) {
                et_phone.requestFocus();
                Toast.makeText(this, "请输入11位的手机号!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private class MyTextWatcher implements TextWatcher {
        private EditText et;
        private int mMaxLength;

        public MyTextWatcher(EditText et, int mMaxLength) {
            this.et = et;
            this.mMaxLength = mMaxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if ((s.length() == 11 && mMaxLength == 11) || (s.length() == 6 && mMaxLength == 6)) {
                InputMethodManager inputMethodManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
                inputMethodManager.hideSoftInputFromWindow(et.getWindowToken(), 0);
            }
        }
    }
}