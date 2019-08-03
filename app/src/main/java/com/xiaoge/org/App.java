package com.xiaoge.org;

import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.xiaoge.org.util.AppUtil;
import com.xiaoge.org.util.CrashHandler;

import java.io.File;

public class App extends Application {
    public static final String TAG = "AppXXX";
    public static int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.getInstance().init(this);
        count++;

        Log.i(TAG, "App#onCreate " + getApplicationContext().getApplicationInfo().packageName +
                " pid: " + Process.myPid() +
                " uid: " + Process.myUid());

        init();

        CrashHandler.getInstance().init(this);
        //CrashReport.initCrashReport(getApplicationContext(), "ff76ba0cbd", true);
    }

    private void init() {
        File file = new File(Constant.TEMP_DIR);
        if (!file.exists()) file.mkdirs();
    }
}
