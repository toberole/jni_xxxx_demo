package com.xiaoge.org;

import android.app.Application;
import android.util.Log;

import com.tencent.bugly.crashreport.CrashReport;
import com.xiaoge.org.util.AppUtil;
import com.xiaoge.org.util.CrashHandler;

import java.io.File;

public class App extends Application {
    public static final String TAG = App.class.getSimpleName();
    private static int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.getInstance().init(this);
        count++;
        Log.i(TAG, "App#onCreate " + getApplicationContext().getApplicationInfo().packageName + " count = " + count);

        init();

        CrashHandler.getInstance().init(this);
        CrashReport.initCrashReport(getApplicationContext(), "ff76ba0cbd", true);
    }

    private void init() {
        File file = new File(Constant.TEMP_DIR);
        if (!file.exists()) file.mkdirs();
    }
}
