package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cat.zeus.annotation.ZeusAPTBindView;
import com.xiaoge.org.R;

public class ZeusBindViewProcessorActivity extends AppCompatActivity {

    @ZeusAPTBindView(R.id.btn_test)
    Button btn_test;

    @ZeusAPTBindView(R.id.tv_test)
    TextView tv_test;

    @ZeusAPTBindView(R.id.et_test)
    EditText et_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeus_bind_view_processor);
        ZeusBindViewProcessorActivityViewBinding.bind(this);
    }
}
