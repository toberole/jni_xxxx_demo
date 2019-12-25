package com.zw.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.concurrent.ConcurrentHashMap;

public class ZeusServiceConnectionManager {
    public static final String TAG = ZeusServiceConnectionManager.class.getSimpleName();

    private static ConcurrentHashMap<Class<? extends ZeusService>, IZeusService> services = new ConcurrentHashMap<>();

    public void bindService(Context context, String packageName, Class<? extends ZeusService> serviceClass) {
        ZeusServiceConn conn = new ZeusServiceConn(serviceClass);

    }

    private static class ZeusServiceConn implements ServiceConnection {
        private Class<? extends ZeusService> serviceClass;

        public ZeusServiceConn(Class<? extends ZeusService> serviceClass) {
            this.serviceClass = serviceClass;
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.i(TAG,"onServiceConnected");

            IZeusService zeusService = IZeusService.Stub.asInterface(iBinder);
            services.putIfAbsent(serviceClass, zeusService);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.i(TAG,"onServiceDisconnected");
        }
    }

    public static ZeusServiceConnectionManager getInstance() {
        return ZeusServiceConnectionManagerHolder.instance;
    }

    private static class ZeusServiceConnectionManagerHolder {
        public static ZeusServiceConnectionManager instance = new ZeusServiceConnectionManager();
    }
}
