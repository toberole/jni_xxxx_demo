package com.xiaoge.org;

import android.Manifest;
import android.media.AudioFormat;
import android.os.Environment;

public class Constant {
    public static final String[] PS = new String[]{
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            Manifest.permission.CAMERA,
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final int MIN_BUFFER = 2000;

    public static final int DEFAULT_SAMPLE_RATE = 16000;

    public static final int DEFAULT_CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;

    public static final int DEFAULT_ENCODE_FORMAT = AudioFormat.ENCODING_PCM_16BIT;

    public static final String TEMP_DIR = Environment.getExternalStorageDirectory() + "/dbl_test";
}
