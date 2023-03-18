package com.alibaba.mychatgpt.interceptor;

import android.util.Log;

import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;



public class OpenAILogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Log.d("OkHttp-------->:{}", message);
    }
}