package com.xiaoge.org.activity.demo;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.xiaoge.org.R;
import com.xiaoge.org.fragment.Fragment1;
import com.xiaoge.org.util.LogUtil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentActivity2 extends AppCompatActivity {
    public static final String TAG = "Fragment1";

    private LinearLayout ll_container;

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(TAG, "FragmentActivity2#onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(TAG, "FragmentActivity2#onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(TAG, "FragmentActivity2#onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(TAG, "FragmentActivity2#onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "FragmentActivity2#onDestroy");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment1);
        ll_container = findViewById(R.id.ll_container);
        LogUtil.i(TAG, "FragmentActivity2#onCreate");
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_1)
    void btn1() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment f = new Fragment1();
        fragmentManager.
                beginTransaction()
                .replace(R.id.ll_container, f, "f1")
                .commit();
    }

    @OnClick(R.id.btn_1)
    void btn2() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment f = new Fragment1();
        fragmentManager.
                beginTransaction()
                .replace(R.id.ll_container, f, "f1")
                .commit();
    }

}
