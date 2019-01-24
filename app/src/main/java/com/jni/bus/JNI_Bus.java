package com.jni.bus;

public class JNI_Bus {
    public native static boolean init(String name, String pwd);

    static {
        System.loadLibrary("native-lib");
    }
}
