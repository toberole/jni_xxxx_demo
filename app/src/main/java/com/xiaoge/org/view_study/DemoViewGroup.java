package com.xiaoge.org.view_study;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;


public class DemoViewGroup extends LinearLayout {
    public static final String TAG = DemoViewGroup.class.getSimpleName();

    public DemoViewGroup(Context context) {
        this(context, null);
        Log.i(TAG, "DemoViewGroup 1");
    }

    public DemoViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.i(TAG, "DemoViewGroup 2");
    }

    public DemoViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "DemoViewGroup 3");
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = super.onInterceptTouchEvent(ev);
        Log.i(TAG, "DemoViewGroup onInterceptTouchEvent");
        return b;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.i(TAG, "DemoViewGroup dispatchTouchEvent");
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        Log.i(TAG, "DemoViewGroup onTouchEvent");
        return b;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "DemoViewGroup onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.i(TAG, "DemoViewGroup onLayout");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "DemoViewGroup onSizeChanged");
    }
}
