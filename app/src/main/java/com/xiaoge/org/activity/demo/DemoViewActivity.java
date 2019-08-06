package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.xiaoge.org.R;
import com.xiaoge.org.view_study.DemoView;

public class DemoViewActivity extends AppCompatActivity {
    public static final String TAG = DemoViewActivity.class.getSimpleName();

    @BindView(R.id.btn_test)
    Button btn_test;

    @BindView(R.id.demoView)
    DemoView demoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_view);
        ButterKnife.bind(this);

        Log.i(TAG, "------------: " + String.valueOf(findViewById(android.R.id.content)));
    }

    @OnClick(R.id.demoView)
    void demoView() {
        Log.i(TAG, "demoView clicked");
        btn_test.scrollBy(0, 20);
    }

    @OnClick(R.id.btn_test)
    void btn_test() {
        Log.i(TAG, "btn_test clicked");
        demoView.scrollBy(0, 20);
    }
}
