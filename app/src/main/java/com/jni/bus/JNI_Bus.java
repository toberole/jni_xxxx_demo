package com.jni.bus;

public class JNI_Bus {
    public native long init(String landmark_path, String server_ip, int port, Callback cb);

    static {
        System.loadLibrary("native-lib");
    }
}
