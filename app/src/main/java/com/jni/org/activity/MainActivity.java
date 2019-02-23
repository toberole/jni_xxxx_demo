package com.jni.org.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jni.bus.JNI_Bus;
import com.jni.org.R;
import com.jni.org.bean.IOnNewUserAdded;
import com.jni.org.bean.IUserManager;
import com.jni.org.bean.User;
import com.jni.org.service.ManagerUserService;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button btn;

    private BusHandler busHandler;
    private Button btn_bind_serice;

    private IUserManager userManager;
    private Button btn_add_user;
    private Button btn_get_users;

    // 注册给aidl服务端回调
    private IOnNewUserAdded listener = new IOnNewUserAdded.Stub() {

        @Override
        public void onAddUser(User u) throws RemoteException {
            Log.i(TAG, "IOnNewUserAdded#onAddUser user name: " + u.name + " age: " + u.age);
        }
    };
    private Button btn_unregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_test);
        btn_bind_serice = findViewById(R.id.btn_bind_serice);
        btn_add_user = findViewById(R.id.btn_add_user);
        btn_get_users = findViewById(R.id.btn_get_users);
        btn_unregister = findViewById(R.id.btn_unregister);


        btn.setOnClickListener(this);
        btn_bind_serice.setOnClickListener(this);
        btn_add_user.setOnClickListener(this);
        btn_get_users.setOnClickListener(this);
        btn_unregister.setOnClickListener(this);


        busHandler = new BusHandler();
        busHandler.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        busHandler.post();

        boolean b = JNI_Bus.init("123", "123");
        Log.i(TAG, "b = " + b);
    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.i(TAG, "MainActivity#onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(TAG, "MainActivity#onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i(TAG, "MainActivity#onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(TAG, "MainActivity#onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(TAG, "MainActivity#onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /**
         * 注意 此种方法需要使用RemoteCallbackList，系统提供的专门用于删除跨进程的listener的接口
         */
        if (userManager != null && userManager.asBinder().isBinderAlive()) {
            try {
                userManager.unRegisterListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        Log.i(TAG, "MainActivity#onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_unregister:
                if (userManager != null && userManager.asBinder().isBinderAlive()) {
                    try {
                        userManager.unRegisterListener(listener);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_add_user:
                if (userManager != null) {

                    try {
                        User user = new User();
                        user.age = 33;
                        user.name = "client_name";
                        userManager.addUser(user);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_get_users:
                if (userManager != null) {
                    try {
                        List<User> users = userManager.getUsers();
                        for (User u : users) {
                            Log.i(TAG, "user name: " + u.name + " age: " + u.age);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_bind_serice:
                Intent bind_service = new Intent(MainActivity.this, ManagerUserService.class);
                MainActivity.this.bindService(bind_service, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        userManager = IUserManager.Stub.asInterface(service);

                        try {
                            userManager.registerListener(listener);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        userManager = null;
                    }
                }, BIND_AUTO_CREATE);
                break;
            default:
                break;
        }

    }

    private class BusHandler extends Thread {
        private Handler handler;

        @Override
        public void run() {
            Log.i(TAG, "BusHandler run");

            Looper.prepare();
            handler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    Log.i(TAG, "BusHandler handleMessage");
                    return false;
                }
            });
            Looper.loop();
        }

        void post() {
            if (handler != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "BusHandler handler.post");
                    }
                });

                handler.sendEmptyMessage(1);
            }
        }
    }
}
