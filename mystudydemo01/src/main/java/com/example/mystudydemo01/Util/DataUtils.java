package com.example.mystudydemo01.Util;

import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DataUtils {

    static public String currentDateAndTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String time = simpleDateFormat.format(System.currentTimeMillis() + 8 * 60 * 60 * 1000);
        return time;
    }
}
