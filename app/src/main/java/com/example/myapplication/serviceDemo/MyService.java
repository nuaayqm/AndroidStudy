package com.example.myapplication.serviceDemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceMainActivity.showText("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceMainActivity.showText("onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ServiceMainActivity.showText("onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        ServiceMainActivity.showText("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        ServiceMainActivity.showText("Rebind");
    }
}