package com.jni.org;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jni.bus.JNI_Bus;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button btn;

    private BusHandler busHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_test);

        busHandler = new BusHandler();
        busHandler.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        busHandler.post();

        boolean b = JNI_Bus.init("123", "123");
        Log.i(TAG, "b = " + b);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG,"MainActivity#onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG,"MainActivity#onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i(TAG,"MainActivity#onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG,"MainActivity#onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG,"MainActivity#onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG,"MainActivity#onDestroy");
    }

    private class BusHandler extends Thread {
        private Handler handler;

        @Override
        public void run() {
            Log.i(TAG, "BusHandler run");

            Looper.prepare();
            handler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Log.i(TAG, "BusHandler handleMessage");
                    return false;
                }
            });
            Looper.loop();
        }

        void post() {
            if (handler != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "BusHandler handler.post");
                    }
                });

                handler.sendEmptyMessage(1);
            }
        }
    }
}
