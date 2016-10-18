package com.dnkilic.notifyservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class LoggerService extends IntentService {


    public LoggerService() {
        super("LoggerService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("Service", "Ben başlatıldım.");
    }
}
