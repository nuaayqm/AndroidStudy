package com.example.myapplication.serviceDemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.myapplication.Util.DataUtils;

public class MyBindService extends Service {
    private final IBinder mBinder = new LocalBinder();

    public MyBindService() {
    }


    public class LocalBinder extends Binder {
        public MyBindService getService() {
            return MyBindService.this;
        }

        public String getTime() {
            return DataUtils.currentDateAndTime();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        ServiceMainActivity.showText("onBind");
        return mBinder;
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
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        ServiceMainActivity.showText("Rebind");
    }
}