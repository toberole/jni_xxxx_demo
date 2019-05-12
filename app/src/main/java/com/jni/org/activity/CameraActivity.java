package com.jni.org.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.UiThread;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jni.org.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = CameraActivity.class.getSimpleName();
    public static final String ps[] = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private FrameLayout pre_view_container;
    private TextureView tv_camera_pre;
    private SurfaceTexture surfaceTexture;
    private boolean cameraPreViewInited = false;

    private Button btn_pre;
    private Button btn_record;

    private boolean isReccrding = false;

    private HandlerThread handlerThread;
    private Handler bg_handler;

    private CameraManager cameraManager;
    private CameraDevice camera;
    private CameraCaptureSession capture_session;
    private CaptureRequest.Builder captureRequest_builder;

    private int camera_id = CameraCharacteristics.LENS_FACING_FRONT;

    private CameraDevice.StateCallback cameraDevice_cb = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            Log.i(TAG, "cameraDevice_cb onOpened");

            CameraActivity.this.camera = camera;
            createCameraPreviewSession();
        }

        @Override
        public void onDisconnected(CameraDevice camera) {

        }

        @Override
        public void onError(CameraDevice camera, int error) {

        }
    };

    private CameraCaptureSession.StateCallback cameraCaptureSession_cb = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(CameraCaptureSession session) {
            Log.i(TAG, "cameraCaptureSession_cb onConfigured");
            CameraActivity.this.capture_session = session;
            setCameraCaptureSession();
        }

        @Override
        public void onConfigureFailed(CameraCaptureSession session) {

        }
    };

    private CameraCaptureSession.CaptureCallback capture_cb = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureStarted(CameraCaptureSession session, CaptureRequest request, long timestamp, long frameNumber) {
            super.onCaptureStarted(session, request, timestamp, frameNumber);
        }
    };
    private MediaRecorder mMediaRecorder;

    private void setCameraCaptureSession() {
        try {
            Log.i(TAG, "setCameraCaptureSession");
            capture_session.setRepeatingRequest(captureRequest_builder.build(), capture_cb, bg_handler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void createCameraPreviewSession() {
        try {
            Log.i(TAG, "createCameraPreviewSession");
            captureRequest_builder = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

            List<Surface> surfaces = new ArrayList();
            Surface previewSurface = new Surface(surfaceTexture);
            surfaces.add(previewSurface);
            captureRequest_builder.addTarget(previewSurface);

            initMediaRecorder();

            Surface recorderSurface = mMediaRecorder.getSurface();
            surfaces.add(recorderSurface);
            captureRequest_builder.addTarget(recorderSurface);

            camera.createCaptureSession(surfaces, cameraCaptureSession_cb, bg_handler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private TextureView.SurfaceTextureListener listener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            Log.i(TAG, "SurfaceTextureListener onSurfaceTextureAvailable");
            cameraPreViewInited = true;
            CameraActivity.this.surfaceTexture = surface;
            startPreView();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);
        ActivityCompat.requestPermissions(CameraActivity.this, ps, 110);
        init();
        initView();
    }

    private void init() {
        handlerThread = new HandlerThread("Camera Background");
        handlerThread.start();
        bg_handler = new Handler(handlerThread.getLooper());
    }

    private void initView() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) actionBar.hide();

        pre_view_container = findViewById(R.id.pre_view_container);
        btn_pre = findViewById(R.id.btn_pre);
        btn_pre.setOnClickListener(this);
        btn_record = findViewById(R.id.btn_record);
        btn_record.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick");
        switch (v.getId()) {
            case R.id.btn_pre:
                if (isReccrding) {
                    stopRecord();
                } else {
                    startPreView();
                }

                isReccrding = !isReccrding;
                break;
            case R.id.btn_record:
                startRecord();
                break;
            default:
                break;
        }

    }

    private void startRecord() {
        if (mMediaRecorder != null) {
            mMediaRecorder.start();
        }
    }

    private String videoAbsolutePath = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".mp4";

    private void initMediaRecorder() {
        try {
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mMediaRecorder.setOutputFile(videoAbsolutePath);
            mMediaRecorder.setVideoEncodingBitRate(5 * 1024 * 1024);
            mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mMediaRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingPermission")
    private void startPreView() {
        try {
            if (cameraPreViewInited) {
                cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                String[] ids = cameraManager.getCameraIdList();
                for (String id : ids) {
                    CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(id);
                    int face = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    Log.i(TAG, "face: " + face);
                }

                cameraManager.openCamera(String.valueOf(camera_id), cameraDevice_cb, bg_handler);
            } else {
                initCameraPreView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCameraPreView() {
        pre_view_container.removeAllViews();
        tv_camera_pre = new TextureView(CameraActivity.this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tv_camera_pre.setLayoutParams(params);
        tv_camera_pre.setVisibility(View.VISIBLE);
        pre_view_container.addView(tv_camera_pre);
        tv_camera_pre.setSurfaceTextureListener(listener);
    }

    private void stopRecord() {
        cameraPreViewInited = false;
        pre_view_container.removeAllViews();

        if (camera != null) {
            camera.close();
            camera = null;
        }

        if (capture_session != null) {
            capture_session.close();
            capture_session = null;
        }

        if (mMediaRecorder != null) {
            mMediaRecorder.stop();
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
    }
}
