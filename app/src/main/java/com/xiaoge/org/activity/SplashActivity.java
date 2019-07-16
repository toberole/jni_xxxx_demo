package com.xiaoge.org.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.xiaoge.org.Constant;
import com.xiaoge.org.R;

public class SplashActivity extends AppCompatActivity {
    public static Class[] clazzs = new Class[]{
            CustomSurfaceViewActivity.class,
            TestServiceActivity.class,
            DrawActivity.class,
            ConstraintlayoutActivity.class,
            SinLineViewActivity.class
    };

    private int test_page = 0;

    private void startNextPage() {
        Intent intent = new Intent(SplashActivity.this, clazzs[test_page]);
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
