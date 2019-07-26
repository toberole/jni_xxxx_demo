package com.xiaoge.org.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.xiaoge.org.Constant;
import com.xiaoge.org.R;
import com.xiaoge.org.TestIndex;

public class A_SplashActivity extends AppCompatActivity {
    private void startNextPage() {
        Intent intent = new Intent(A_SplashActivity.this, TestIndex.clazzs[TestIndex.test_page]);
        startActivity(intent);
    }

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


}
