package com.jni.org.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.jni.org.Constant;
import com.jni.org.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        ActivityCompat.requestPermissions(this, Constant.PS, 1000);
    }

    @OnClick(R.id.btn_next)
    void next() {
        startNextPage();
    }

    private void startNextPage() {
        Intent intent = new Intent(SplashActivity.this, SinLineViewActivity.class);
        startActivity(intent);
    }
}
