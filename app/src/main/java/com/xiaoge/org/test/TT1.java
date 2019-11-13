package com.xiaoge.org.test;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class TT1 {
    public void test() {
        HandlerThread handlerThread = new HandlerThread("");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        Looper.prepare();
        Looper.loop();

        AsyncTask asyncTask = null;
        asyncTask.execute(null);
    }
}
