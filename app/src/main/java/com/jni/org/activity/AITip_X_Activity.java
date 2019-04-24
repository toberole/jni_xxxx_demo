package com.jni.org.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jni.org.R;
import com.jni.org.views.AITipView;
import com.jni.org.views.AITipView_X;

public class AITip_X_Activity extends AppCompatActivity implements View.OnClickListener {

    private AITipView_X aiTipView;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aitip_x);

        aiTipView = findViewById(R.id.ai_tip);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                aiTipView.start();
                break;
            default:
                break;
        }
    }
}
