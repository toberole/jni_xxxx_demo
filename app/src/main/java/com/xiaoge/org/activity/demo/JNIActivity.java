package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;

import com.xiaoge.org.R;
import com.xiaoge.org.jni.Test;
import com.xiaoge.org.jni.Test1;

public class JNIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test)
    void btn_test() {
        Test test = new Test();
        test.test((byte) 0, (char) 1, (short) 1, 1, 1f, 1, "", null);
    }

    @OnClick(R.id.btn_test1)
    void btn_test1() {
        Test test = new Test();
        byte b = 0;
        test.test1(b);
    }

    @OnClick(R.id.btn_test2)
    void btn_test2() {
        Test test = new Test();
        byte b = 0;
        test.test1(b);
    }

    @OnClick(R.id.btn_test3)
    void btn_test3() {
        Test1.sysHello("hello");
    }
}
