package com.xiaoge.org.service.binderpool;

import android.os.RemoteException;

public class Binder_1_Impl extends IBinder_1.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
