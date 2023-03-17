package com.unfbx.chatgpt;

import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import com.unfbx.chatgpt.interceptor.OpenAILogger;
import okhttp3.logging.HttpLoggingInterceptor;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;

/**
 * 描述： 测试类
 *
 * @author https:www.unfbx.com
 * 2023-02-28
 */
public class OpenAiStreamClientTest {


    public static void main(String[] args) {
        //配置api keys
        //代理可以为null
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 4780));
        //日志输出可以不添加
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new OpenAILogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey("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")
                .connectTimeout(50)
                .writeTimeout(50)
                .readTimeout(50)
                .interceptor(Arrays.asList(httpLoggingInterceptor))
                .proxy(proxy)
                .apiHost("https://api.openai.com/")
                .build();
        CompletionResponse completions = openAiClient.completions("你会写正则表达式吗");
        Arrays.stream(completions.getChoices()).forEach(System.out::println);
    }






//    public void completions() {
//        ConsoleEventSourceListener eventSourceListener = new ConsoleEventSourceListener();
//        Completion q = Completion.builder()
//                .prompt("一句话描述下开心的心情")
//                .stream(true)
//                .build();
//        client.streamCompletions(q, eventSourceListener);
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}