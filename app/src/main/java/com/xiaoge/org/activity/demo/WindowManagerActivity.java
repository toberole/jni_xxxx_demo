package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xiaoge.org.R;

import static android.view.WindowManager.LayoutParams.TYPE_TOAST;

public class WindowManagerActivity extends AppCompatActivity {
    @BindView(R.id.ll_container)
    LinearLayout ll_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_manager);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(WindowManagerActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 10);
            }
        }
    }

    @OnClick(R.id.btn_wm)
    void btn_wm() {
        WindowManager windowManager = getWindowManager();
        Button view = new Button(WindowManagerActivity.this);
        view.setText("hello float");
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0, 0, PixelFormat.TRANSPARENT
        );
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        params.gravity = Gravity.CENTER;
        params.x = 300;
        params.y = 200;
        params.type = TYPE_TOAST;
        windowManager.addView(view, params);
    }
}
