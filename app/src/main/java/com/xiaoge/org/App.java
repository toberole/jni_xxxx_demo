package com.xiaoge.org;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.xiaoge.org.util.AppUtil;
import com.xiaoge.org.util.CrashHandler;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class App extends Application implements Application.ActivityLifecycleCallbacks {
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

        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

        CrashHandler.getInstance().init(this);
        //CrashReport.initCrashReport(getApplicationContext(), "ff76ba0cbd", true);

        // 反射做埋点
        registerActivityLifecycleCallbacks(this);
    }

    private void init() {
        File file = new File(Constant.TEMP_DIR);
        if (!file.exists()) file.mkdirs();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        // 当动态添加view的时候也可以做到点击代理的替换
        ViewTreeObserver viewTreeObserver = rootView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                setViewProxy(rootView);
            }
        });

        try {
            setViewProxy(rootView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    private void setViewProxy(ViewGroup rootView) {
        if (null != rootView) {
            int count = rootView.getChildCount();
            for (int i = 0; i < count; i++) {
                View v = rootView.getChildAt(i);
                if (v instanceof ViewGroup) {
                    setViewProxy((ViewGroup) v);
                } else {
                    hook(v);
                }
            }
        }
    }

    private void hook(View view) {
        try {
            Method getListenerInfo = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfo.setAccessible(true);
            Object listenereInfo = getListenerInfo.invoke(view);
            Class<?> listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");
            Field mOnClickListener = listenerInfoClazz.getDeclaredField("mOnClickListener");
            mOnClickListener.setAccessible(true);
            View.OnClickListener originOnClickListener = (View.OnClickListener) mOnClickListener.get(listenereInfo);
            if (originOnClickListener == null || originOnClickListener instanceof OnClickListenererProxy) {
                return;
            } else {
                OnClickListenererProxy proxyOnClick = new OnClickListenererProxy(originOnClickListener);
                mOnClickListener.set(listenereInfo, proxyOnClick);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class OnClickListenererProxy implements View.OnClickListener {
        private View.OnClickListener onClickListener;

        public OnClickListenererProxy(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View v) {
            preClicked();
            onClickListener.onClick(v);
            afterClicked();
        }

        private void afterClicked() {
            Log.i(TAG, "OnClickListenererProxy#afterClicked");
        }

        private void preClicked() {
            Log.i(TAG, "OnClickListenererProxy#preClicked");
        }
    }
}
