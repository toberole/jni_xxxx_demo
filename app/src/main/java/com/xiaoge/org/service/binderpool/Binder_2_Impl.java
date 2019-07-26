package com.xiaoge.org.service.binderpool;

import android.os.RemoteException;

import com.xiaoge.org.util.LogUtil;

public class Binder_2_Impl extends IBinder_2.Stub {
    public static final String TAG = Binder_2_Impl.class.getCanonicalName();

    @Override
    public void printHello(String msg) throws RemoteException {
        LogUtil.i(TAG, "Binder_2_Impl#printHello " + msg);
    }
}
