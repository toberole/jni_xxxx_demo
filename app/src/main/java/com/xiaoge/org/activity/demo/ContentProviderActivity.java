package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.net.Uri;
import android.os.Bundle;

import com.xiaoge.org.R;

public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add)
    void btn_add() {
        // uri格式：content://authority/路径
        // authority必须与需要访问的 ContentProvider配置的authority匹配
        Uri uri = Uri.parse("content://com.xiaoge.org/test_add");
        ContentProviderActivity.this.getContentResolver().query(uri, null, null, null, null);
    }

    @OnClick(R.id.btn_delete)
    void btn_delete() {

    }

    @OnClick(R.id.btn_modify)
    void btn_modify() {

    }

    @OnClick(R.id.btn_read)
    void btn_read() {

    }
}
