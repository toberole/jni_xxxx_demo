package com.xiaoge.org.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;

import com.xiaoge.org.Constant;
import com.xiaoge.org.R;
import com.xiaoge.org.activity.demo.LifeActivity;

public class A_SplashActivity extends AppCompatActivity {
    private int test_page = 0;

    public static Class[] clazzs = new Class[]{
            LifeActivity.class,
            CustomSurfaceViewActivity.class,
            TestServiceActivity.class,
            DrawActivity.class,
            ConstraintlayoutActivity.class,
            SinLineViewActivity.class
    };


    private void startNextPage() {
        Intent intent = new Intent(A_SplashActivity.this, clazzs[test_page]);
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
