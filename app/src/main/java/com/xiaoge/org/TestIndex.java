package com.xiaoge.org;

import com.xiaoge.org.activity.demo.AnimActivity;
import com.xiaoge.org.activity.demo.BinderPoolActivity;
import com.xiaoge.org.activity.demo.ContentProviderActivity;
import com.xiaoge.org.activity.demo.DemoViewActivity;
import com.xiaoge.org.activity.demo.DemoViewGroupActivity;
import com.xiaoge.org.activity.demo.LifeActivity;
import com.xiaoge.org.activity.demo.ScrollConflictActivity;
import com.xiaoge.org.activity.demo0.ConstraintlayoutActivity;
import com.xiaoge.org.activity.demo0.CustomSurfaceViewActivity;
import com.xiaoge.org.activity.demo0.DrawActivity;
import com.xiaoge.org.activity.demo0.SinLineViewActivity;
import com.xiaoge.org.activity.demo0.TestServiceActivity;
import com.xiaoge.org.activity.kotlin.HelloKotlinActivity;

public class TestIndex {
    public static int test_page = 0;

    public static Class[] clazzs = new Class[]{
            HelloKotlinActivity.class,
            ScrollConflictActivity.class,
            DemoViewGroupActivity.class,
            AnimActivity.class,
            DemoViewActivity.class,
            BinderPoolActivity.class,
            ContentProviderActivity.class,
            LifeActivity.class,
            CustomSurfaceViewActivity.class,
            TestServiceActivity.class,
            DrawActivity.class,
            ConstraintlayoutActivity.class,
            SinLineViewActivity.class
    };
}
