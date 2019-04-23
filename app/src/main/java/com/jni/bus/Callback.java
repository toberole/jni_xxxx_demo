package com.jni.bus;

import android.util.Log;

public class Callback {
    public void callback(long errorCode, String errorMsg, Object obj) {
        // Log.i("jni-log", "errorCode: " + errorCode + " errorMsg: " + String.valueOf(errorMsg) + " obj: " + String.valueOf(obj));
        Log.i("jni-log", "errorCode: ");
    }
}
