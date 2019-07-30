package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Log;

import com.xiaoge.org.R;

public class DemoViewActivity extends AppCompatActivity {
    public static final String TAG = DemoViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.demoView)
    void demoView() {
        Log.i(TAG, "demoView clicked");
    }
}
