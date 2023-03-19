package com.example.myapplication.serviceDemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyBindService extends Service {
    public MyBindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}