package com.jni.org;

import android.Manifest;
import android.os.Environment;

public class Constant {
    public static final String[] PS = new String[]{
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final String TEMP_DIR = Environment.getExternalStorageDirectory() + "/dbl_test";
}
