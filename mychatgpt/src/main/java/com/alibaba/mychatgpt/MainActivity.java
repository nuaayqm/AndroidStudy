package com.alibaba.mychatgpt;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.mychatgpt.entity.common.Choice;
import com.alibaba.mychatgpt.entity.completions.CompletionResponse;
import com.alibaba.mychatgpt.interceptor.OpenAILogger;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = "com.alibaba.mychatgpt.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn_send);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        BackTask backTask = new BackTask();
        backTask.execute();
    }

    private class BackTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
            //日志输出可以不添加
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OpenAiClient openAiClient = OpenAiClient.builder()
                    .apiKey("")
                    .connectTimeout(50)
                    .writeTimeout(50)
                    .readTimeout(50)
                    .interceptor(Arrays.asList(httpLoggingInterceptor))
                    .proxy(proxy)
                    .apiHost("https://api.openai.com/")
                    .build();

            CompletionResponse completions = openAiClient.completions("你好");
            Arrays.stream(completions.getChoices()).forEach(System.out::println);

            StringBuilder streamOut = new StringBuilder();
            for (Choice choice: completions.getChoices()) {
                streamOut.append(choice.toString());
            }

            return streamOut.toString();
        }

        protected void onPostExecute(String result) {
            TextView tvAnswer = findViewById(R.id.tv_answer);
            tvAnswer.setText(result);
        }
    }
}