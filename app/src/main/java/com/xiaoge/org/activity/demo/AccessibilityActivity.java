package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xiaoge.org.R;

public class AccessibilityActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = AccessibilityActivity.class.getSimpleName();

    private AppCompatButton btn_accessibility;

    private View.AccessibilityDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibility);
        btn_accessibility = findViewById(R.id.btn_accessibility);
        btn_accessibility.setOnClickListener(this);
        delegate = new MyAccessibilityDelegate();
        btn_accessibility.setAccessibilityDelegate(delegate);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick");
    }

    private class MyAccessibilityDelegate extends View.AccessibilityDelegate {
        @Override
        public void sendAccessibilityEvent(View host, int eventType) {
            super.sendAccessibilityEvent(host, eventType);
            Log.i(TAG, "sendAccessibilityEvent");
        }

        @Override
        public boolean performAccessibilityAction(View host, int action, Bundle args) {
            Log.i(TAG, "performAccessibilityAction");
            return super.performAccessibilityAction(host, action, args);
        }
    }
}
