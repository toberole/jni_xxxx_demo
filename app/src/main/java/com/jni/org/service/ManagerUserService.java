package com.jni.org.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.jni.org.bean.IOnNewUserAdded;
import com.jni.org.bean.IUserManager;
import com.jni.org.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * aidl通信 提供服务
 */
public class ManagerUserService extends Service {
    public static final String TAG = ManagerUserService.class.getSimpleName();

    // private CopyOnWriteArrayList<IOnNewUserAdded> listeners = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IOnNewUserAdded> listeners = new RemoteCallbackList<>();

    /**
     * 实现服务接口
     */
    private IUserManager.Stub userManager = new IUserManager.Stub() {
        @Override
        public void addUser(User user) throws RemoteException {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            addUser_notify();

            Log.i(TAG, "ManagerUserService#addUser thread_id: " + Thread.currentThread().getId() + " age: " + user.age + " name: " + user.name);
        }

        @Override
        public List<User> getUsers() throws RemoteException {
            List<User> users = new ArrayList<>();
            User user = new User();
            user.age = 11;
            user.name = "xiaogon";
            users.add(user);

            user = new User();
            user.age = 22;
            user.name = "xiaofang";
            users.add(user);
            return users;
        }

        @Override
        public void registerListener(IOnNewUserAdded listener) throws RemoteException {
//            if (!listeners.contains(listener)) {
//                listeners.add(listener);
//
//                Log.i(TAG, "ManagerUserService#registerListener");
//            }

            listeners.register(listener);
            Log.i(TAG, "ManagerUserService#registerListener");
        }

        @Override
        public void unRegisterListener(IOnNewUserAdded listener) throws RemoteException {
//            if (!listeners.contains(listener)) {
//                listeners.remove(listener);
//
//                Log.i(TAG, "ManagerUserService#unRegisterListener");
//            }

            listeners.unregister(listener);
            Log.i(TAG, "ManagerUserService#unRegisterListener");
        }
    };

    public ManagerUserService() {
        Log.i(TAG, "ManagerUserService#ManagerUserService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "ManagerUserService#onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "ManagerUserService#onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "ManagerUserService#onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "ManagerUserService#onUnbind");

        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "ManagerUserService#onDestroy");

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "ManagerUserService#onBind");

        return userManager;
    }

    private void addUser_notify() {
//        for (IOnNewUserAdded l : listeners) {
//            try {
//                User user = new User();
//                user.age = 100;
//                user.name = "hello world";
//                l.onAddUser(user);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }

        // 注意：beginBroadcast 和 finishBroadcast 必须配对使用
        int n = listeners.beginBroadcast();
        User user = new User();
        user.age = 100;
        user.name = "hello world";

        for (int i = 0; i < n; i++) {
            IOnNewUserAdded iOnNewUserAdded = listeners.getBroadcastItem(i);
            if (null != iOnNewUserAdded) {
                try {
                    iOnNewUserAdded.onAddUser(user);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        listeners.finishBroadcast();


    }
}
