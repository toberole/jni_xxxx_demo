package com.xiaoge.org.activity.demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.xiaoge.org.R;
import com.xiaoge.org.service.binderpool.BinderPool;
import com.xiaoge.org.service.binderpool.BinderTAG;
import com.xiaoge.org.service.binderpool.Binder_1_Impl;
import com.xiaoge.org.service.binderpool.Binder_2_Impl;
import com.xiaoge.org.service.binderpool.IBinder_1;
import com.xiaoge.org.service.binderpool.IBinder_2;

public class BinderPoolActivity extends AppCompatActivity {
    private BinderPool binderPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        ButterKnife.bind(this);

        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                binderPool = new BinderPool(BinderPoolActivity.this);
                binderPool.binderServiceBinderPool();
            }
        }, 500);
    }

    @OnClick(R.id.binder_1)
    void binder_1() {
        IBinder iBinder1 = binderPool.queryBinder(BinderTAG.BINDER_1);
        IBinder_1 binder_1_impl = (IBinder_1) Binder_1_Impl.asInterface(iBinder1);
        try {
            int c = binder_1_impl.add(1, 2);
            Toast.makeText(BinderPoolActivity.this, "" + c, Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.binder_2)
    void binder_2() {
        IBinder iBinder2 = binderPool.queryBinder(BinderTAG.BINDER_2);
        IBinder_2 binder_2_impl = Binder_2_Impl.asInterface(iBinder2);
        try {
         binder_2_impl.printHello("hello binder pool");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
