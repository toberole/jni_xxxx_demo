package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.xiaoge.org.R;

public class LifeActivity extends AppCompatActivity {
    public static final String TAG = LifeActivity.class.getSimpleName();

    @Override
    /**
     * 可见 但未获取焦点
     */
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "LifeActivity#onStart");
    }

    @Override
    /**
     * Activity重建[activity 的stop生命周期方法被调用了] 之后回调
     */
    protected void onRestart() {
        super.onRestart();

        Log.i(TAG, "LifeActivity#onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "LifeActivity#onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "LifeActivity#onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "LifeActivity#onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "LifeActivity#onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        Log.i(TAG, "LifeActivity#onCreate");

        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_click)
    void tv_click() {
        Intent intent = new Intent(LifeActivity.this, LifeActivity_B.class);
        LifeActivity.this.startActivity(intent);
    }
}