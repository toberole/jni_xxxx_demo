package com.jni.org.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jni.org.R;
import com.jni.org.views.M_View;

public class TestViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);

        M_View mView = findViewById(R.id.testView);
        mView.setOnClickListener(v -> {
            Toast.makeText(TestViewActivity.this, "Lambda表达式", Toast.LENGTH_SHORT).show();
        });
    }
}
