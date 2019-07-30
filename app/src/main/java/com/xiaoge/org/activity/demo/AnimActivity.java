package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoge.org.R;

/**
 * view移动
 * 1、scroll
 * 2、属性动画
 * 3、改变参数
 * 2和3方法本质相同，都是通过改变其参数
 */
public class AnimActivity extends AppCompatActivity {
    public static final String TAG = AnimActivity.class.getSimpleName();

    @BindView(R.id.tv_test)
    TextView tv_test;

    @BindView(R.id.btn_scroll)
    Button btn_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_test)
    void tv_test() {
        Log.i(TAG, "tv_test clicked");
    }

    /**
     * scrollBy
     * scrollTo
     * 移动的只是影像，view真身并没有移动
     */
    @OnClick(R.id.btn_scroll)
    void btn_scroll() {
        tv_test.scrollBy(10, 10);
    }

    /**
     * 移动的是View的真身
     */
    @OnClick(R.id.btn_translationX)
    void btn_translationX() {
        float translationX = tv_test.getTranslationX();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv_test, "translationX", translationX, 200, 100);
        objectAnimator.setDuration(500);
        objectAnimator.start();
    }

    /**
     * 移动的是View的真身
     */
    @OnClick(R.id.btn_change_param)
    void btn_change_param() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv_test.getLayoutParams();
        params.leftMargin = params.leftMargin + 20;
        tv_test.requestLayout();
    }
}
