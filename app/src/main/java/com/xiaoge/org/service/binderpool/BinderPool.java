package com.xiaoge.org.service.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class BinderPool {
    private IBinderPool binderPool_impl;

    private Context context;
    private MyServiceConnection conn;

    public BinderPool(Context ctx) {
        this.context = ctx;
    }

    public void binderServiceBinderPool() {
        Intent intent = new Intent(context, BinderPoolService.class);
        conn = new MyServiceConnection();
        context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    public IBinder queryBinder(int binderTag) {
        try {
            return binderPool_impl.queryBinder(binderTag);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    private class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderPool_impl = IBinderPool.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
