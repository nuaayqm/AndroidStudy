package com.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CalculatorActivity";
    private String operator = "";
    private String firstNum = "0";
    private String secondNum = "0";
    private String result = "";
    private String showText = "";

    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.tv_result);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_sqrt).setOnClickListener(this);
        findViewById(R.id.btn_inverse).setOnClickListener(this);
        findViewById(R.id.btn_point).setOnClickListener(this);


    }

    private boolean verify(View v) {
        boolean ret = true;
        if (v.getId() == R.id.btn_cancel) {
            if (operator.isEmpty() && (firstNum.isEmpty() || "0".equals(firstNum)) || (!operator.isEmpty() && secondNum.isEmpty())) {
                Toast.makeText(this, "没有数字可以删除了", Toast.LENGTH_SHORT).show();
                ret = false;
            }
        } else if (v.getId() == R.id.btn_equal) {
            if (operator.isEmpty()) {
                Toast.makeText(this, "请输入运算符", Toast.LENGTH_SHORT).show();
                ret = false;
            }
            if ("/".equals(operator) && "0".equals(secondNum)) {
                Toast.makeText(this, "除数不能为0", Toast.LENGTH_SHORT).show();
                ret = false;
            }
            if (firstNum.isEmpty() || secondNum.isEmpty()) {
                Toast.makeText(this, "请输入运算数", Toast.LENGTH_SHORT).show();
                ret = false;
            }
        } else if (v.getId() == R.id.btn_sqrt) {
            if (firstNum.isEmpty()) {
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                ret = false;
            }
            if (Double.parseDouble(firstNum) < 0) {
                Toast.makeText(this, "负数不能开方", Toast.LENGTH_SHORT).show();
                ret = false;
            }
        } else if (v.getId() == R.id.btn_add || v.getId() == R.id.btn_sub || v.getId() == R.id.btn_mul || v.getId() == R.id.btn_divide) {
            if (firstNum.isEmpty()) {
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                ret = false;
            }
            if (!operator.isEmpty()) { // 已有运算符
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                ret = false;
            }
        } else if (v.getId() == R.id.btn_inverse) { // 点击了求倒数按钮
            if (firstNum.isEmpty()) { // 缺少底数
                Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();
                ret = false;
            }
            if (Double.parseDouble(firstNum) == 0) { // 不能对零求倒数
                Toast.makeText(this, "不能对零求倒数", Toast.LENGTH_SHORT).show();
                ret = false;
            }
        } else if (v.getId() == R.id.btn_point) { // 点击了小数点
            if (operator.isEmpty() && firstNum.contains(".")) { // 无运算符，则检查第一个操作数是否已有小数点
                Toast.makeText(this, "一个数字不能有两个小数点", Toast.LENGTH_SHORT).show();
                ret = false;
            }
            if (!operator.isEmpty() && secondNum.contains(".")) { // 有运算符，则检查第二个操作数是否已有小数点
                Toast.makeText(this, "一个数字不能有两个小数点", Toast.LENGTH_SHORT).show();
                ret = false;
            }
        }

        if (!ret) {
            Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vb.vibrate(500);
        }
        return ret;
    }

    @Override
    public void onClick(View v) {
        if (!verify(v)) {
            return;
        }

        String textInput;
        if (v.getId() == R.id.btn_sqrt) {
            textInput = "√";
        } else {
            textInput = ((TextView) v).getText().toString();
        }
        Log.d(TAG, "textInput: " + textInput);

        if (v.getId() == R.id.btn_clear) {
            clear();
        } else if (v.getId() == R.id.btn_cancel) {
            if (operator.isEmpty()) {
                firstNum = firstNum.substring(0, firstNum.length() - 1);
                refreshText(firstNum);
                Log.d("Calculator", "firstNum: " + firstNum);
            } else {
                secondNum = secondNum.substring(0, secondNum.length() - 1);
                refreshText(secondNum);
            }
        } else if (v.getId() == R.id.btn_sqrt) {
            double sqrtValue = Math.sqrt(Double.parseDouble(firstNum));
            refreshText(textInput + firstNum + "=" + sqrtValue);
        } else if (v.getId() == R.id.btn_add || v.getId() == R.id.btn_sub || v.getId() == R.id.btn_mul || v.getId() == R.id.btn_divide) {
            operator = textInput;
            refreshText(showText + operator);
        } else if (v.getId() == R.id.btn_equal) {
            double calculate_result = calculate();
            refreshOperate(String.valueOf(calculate_result));
            refreshText(showText + "=" + result);
        } else if (v.getId() == R.id.btn_inverse) {
            double calculate_result = 1 / Double.parseDouble(firstNum);
            refreshOperate(String.valueOf(calculate_result));
            refreshText("1/" + showText + "=" + result);
        } else {
            if (result.length() > 0 && operator.isEmpty()) {
                clear();
            }
            if (operator.isEmpty()) {
                if ("0".equals(firstNum)) {
                    firstNum = textInput;
                } else {
                    firstNum += textInput;
                }
            } else {
                if ("0".equals(secondNum)) {
                    secondNum = textInput;
                } else {
                    secondNum += textInput;
                }
            }
            if ("0".equals(showText) && !".".equals(textInput)) {
                refreshText(textInput);
            } else {
                refreshText(showText + textInput);
            }
        }

    }

    private double calculate() {
        double calculate_result = 0;
        if ("+".equals(operator)) {
            calculate_result = Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
        } else if ("－".equals(operator)) {
            calculate_result = Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
        } else if ("×".equals(operator)) {
            calculate_result = Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
        } else if ("÷".equals(operator)) {
            calculate_result = Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
        Log.d(TAG, "calculate result = " + calculate_result);
        return calculate_result;
    }

    private void clear() {
        refreshOperate("");
        refreshText("");
    }

    private void refreshText(String text) {
        showText = text;
        textViewResult.setText(showText);
    }

    private void refreshOperate(String new_result) {
        result = new_result;
        firstNum = result;
        secondNum = "";
        operator = "";

    }


}