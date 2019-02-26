package com.jni.org.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jni.org.R;
import com.jni.org.views.TestView;

public class TestViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);

        TestView testView = findViewById(R.id.testView);
        testView.setOnClickListener(v -> {
            Toast.makeText(TestViewActivity.this, "Lambda表达式", Toast.LENGTH_SHORT).show();
        });
    }
}
