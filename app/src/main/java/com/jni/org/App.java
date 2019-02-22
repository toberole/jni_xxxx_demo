package com.jni.org;

import android.app.Application;
import android.util.Log;

public class App extends Application {
    public static final String TAG = App.class.getSimpleName();
    private static int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        count++;
        Log.i(TAG, "App#onCreate " + getApplicationContext().getApplicationInfo().packageName + " count = " + count);
    }
}
