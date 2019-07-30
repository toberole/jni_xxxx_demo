package com.xiaoge.org.view_study;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DemoView extends View {
    public static final String TAG = DemoView.class.getSimpleName();

    // private Scroller scroller = new Scroller()

    public DemoView(Context context) {
        super(context);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 相对于当前View左上角的x和y坐标
        float x = event.getX();
        float y = event.getY();

        // 相对手机屏幕左上角的x和y
        float rawX = event.getRawX();
        float rawY = event.getRawY();

        Log.i(TAG, "x: " + x + " y: " + y);
        Log.i(TAG, "rawX: " + rawX + " rawY: " + rawY);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "ACTION_CANCEL");
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }
}