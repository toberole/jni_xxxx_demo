package com.xiaoge.org.camera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Size;
import android.view.View;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Camera2 工具类
 * Created by Administrator on 2018/10/30.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CameraUtil {

    private final static String TAG = "camera_util";
    public final static String FRONT = "CAMERA_FRONT";
    public final static String BACK = "CAMERA_BACK";
    private boolean isCameraBack = true;

    private CameraManager cameraManager;
    private Handler handler;
    private HandlerThread handlerThread;

    private ArrayMap<String, CameraConfig> cameraConfig = new ArrayMap<>();
    private int capacity = -1;

    private View frontTextureView = null;
    private View backTextureView =null;

    public CameraUtil(Context context) {
        this(context, null, null);
    }

    /**
     * 传入需预览的 TextureView 控件，如不需要预览可传 null
     */
    public CameraUtil(Context context, @Nullable View frontTextureView, @Nullable View backTextureView) {
        this.frontTextureView = frontTextureView;
        this.backTextureView = backTextureView;
        init(context);
    }

    private void init(Context context) {

        //检查权限
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)) {
            throw new SecurityException("without camera permission!");
        }

        initHandlerThread();

        cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        int displayRotation = wm.getDefaultDisplay().getRotation();
//        int degree = 0;
//        switch (displayRotation) {
//            case Surface.ROTATION_0:
//                degree = 0;
//                break;
//            case Surface.ROTATION_180:
//                degree = 180;
//                break;
//            case Surface.ROTATION_90:
//                degree = 90;
//                break;
//            case Surface.ROTATION_270:
//                degree = 270;
//                break;
//        }
//        Log.e(TAG, "displayRotation:" + displayRotation + " ,degree:" + degree);
        try {
            String[] ids = cameraManager.getCameraIdList();
            Log.i(TAG, "camera count = " + ids.length);
            for (String id : ids) {
                CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(id);
                Integer cameraOrientation = characteristics.get(CameraCharacteristics.LENS_FACING);
                Integer sensorOrientation = characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                if (sensorOrientation == null) {
                    sensorOrientation = 0;
                }
                Log.e(TAG, "SensorOrientation = " + sensorOrientation);
                if (cameraOrientation == null)
                    continue;
                StreamConfigurationMap size = characteristics.get(
                        CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                if (cameraOrientation == CameraCharacteristics.LENS_FACING_FRONT) {
                    CameraConfig config = new CameraConfig(id, calculationSize(size), frontTextureView, frontAvailableListener, handler);
                    config.setSurfaceCallback(surfaceCallback);
                    frontAvailableListener.setOrientation(sensorOrientation);
                    cameraConfig.put(FRONT, config);
                } else if (cameraOrientation == CameraCharacteristics.LENS_FACING_BACK) {
                    CameraConfig config = new CameraConfig(id, calculationSize(size), backTextureView, backAvailableListener, handler);
                    config.setSurfaceCallback(surfaceCallback);
                    backAvailableListener.setOrientation(sensorOrientation);
                    cameraConfig.put(BACK, config);
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void initHandlerThread() {
        handlerThread = new HandlerThread("CameraThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    private void stopHandlerThread() {
        handlerThread.quitSafely();
        try {
            handlerThread.join();
            handlerThread = null;
            handler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置预览编码
     */
    public void setPreviewImageFormat(int imageFormat) {
        if (imageFormat == ImageFormat.JPEG) {
            Log.e(TAG, "JPEG 格式占用缓存过多，会导致预览卡顿，不建议使用该编码进行预览，最好使用其他编码格式");
        }
        Collection<CameraConfig> configs = cameraConfig.values();
        for (CameraConfig config : configs) {
            config.setImageFormat(imageFormat);
        }
    }

    /**
     * 释放<br/>
     * 最好在 onPause 中调用而不是在 onDestroy 中
     */
    public void release() {
        Log.d(TAG, "camera release.");
        previewFrameCallback = null;
        CameraConfig config = cameraConfig.get(isCameraBack ? BACK : FRONT);
        if (cameraConfig != null)
            config.release();
        stopHandlerThread();
    }

    public void startPreview(Context context) {
        Log.d(TAG, "camera start preview.");
        if (cameraManager == null) {
            cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        }
        capacity = -1;
        CameraConfig config = cameraConfig.get(isCameraBack ? BACK : FRONT);
        if (config != null) {
            config.startPreview();
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresPermission(Manifest.permission.CAMERA)
    private void openCamera(CameraManager cameraManager, CameraConfig config) {
        try {
            cameraManager.openCamera(config.cameraId, config.cameraStateCallback, handler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void switchCamera() {
        CameraConfig config = cameraConfig.get(isCameraBack ? BACK : FRONT);
        if (cameraConfig != null) {
            config.onPause();
        }
        isCameraBack = !isCameraBack;
        startPreview(null);
    }

    public void setDefauleCamera(boolean useBackCamera) {
        isCameraBack = useBackCamera;
    }

    public Size getSize() {
        Size size = null;
        CameraConfig config = cameraConfig.get(isCameraBack ? BACK : FRONT);
        if (config != null) {
            size = config.getSize();
        }
        return size;
    }

    private Size calculationSize(StreamConfigurationMap map) {
        if (map != null) {
            return Collections.max(Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)),
                    new Comparator<Size>() {
                        @Override
                        public int compare(Size lhs, Size rhs) {
                            return Long.signum((long) rhs.getWidth() * rhs.getHeight() -
                                    (long) lhs.getWidth() * lhs.getHeight());
//                            return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
//                                    (long) rhs.getWidth() * rhs.getHeight());
                        }
                    });
        }
        return null;
    }

    private Size size = null;

    /**
     * 获取流
     *
     * @param image
     * @param orientation 旋转方向
     */
    private byte[][] getBytes(Image image, int orientation) {
        if (capacity == -1) {
            size = getSize();//Size
            capacity = (int) (size.getWidth() * size.getHeight() * 1.5);
        }
        int len = image.getPlanes().length;
        byte[][] bytes = new byte[len][];
        int count = 0;
        for (int i = 0; i < len; i++) {
            ByteBuffer buffer = image.getPlanes()[i].getBuffer();
            int remaining = buffer.remaining();
            byte[] data = new byte[remaining];
            buffer.get(data);
            bytes[i] = data;
//            bytes[i] = fixOrientation(data, size, orientation);
            count += remaining;
        }
        Log.d(TAG, "bytes = " + count);
        return bytes;
    }

    boolean error = false;

    private byte[] fixOrientation(byte[] src, Size size, int orientation) {
        if (error)
            return src;
        if (size == null)
            return src;
        if (orientation == 0 || orientation == 180)
            return src;
        int index = 0;
        int column = size.getWidth();
        int row = size.getWidth() * size.getHeight() > src.length ? size.getHeight() / 4 : size.getHeight();
        try {
            byte[] dest = new byte[src.length];
            int len = src.length;
//            if (orientation == 90) {
            for (int i = 0; i < len; i++) {
                // x = i % raw; y = i/raw;  index = x + raw * y;
                // x` = column - i / raw; y` = i % raw;
                index = (column - i / row) + (i % row) * column - 1;
                dest[i] = src[index];
            }
//            } else {
//
//            }
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "index = " + index + " < " + row + " , " + column + " >");
            error = true;
            return src;
        }
    }

    //预览处理
    private OnImageAvailableListener frontAvailableListener = new OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader reader) {
            Image image = reader.acquireLatestImage();
            if (previewFrameCallback != null) {
                byte[][] bytes = getBytes(image, getOrientation());
                byte[] _bytes = new byte[capacity];
                int count = 0;
                for (byte[] b : bytes) {
                    System.arraycopy(b, 0, _bytes, count, b.length);
                    count += b.length;
                }
//                int orientation = getOrientation();
//                if (orientation == 90 || orientation == 270) {
//                    byte[][] tmp = new byte[3][];
//                    tmp[0] = new byte[bytes[0].length];
//                    tmp[1] = new byte[bytes[1].length];
//                    tmp[2] = new byte[bytes[2].length];
//
//                    YuvUtil.i420RotatePlane(bytes[0], bytes[1], bytes[2], tmp[0], tmp[1], tmp[2],
//                            getSize().getHeight(), getSize().getWidth(), orientation);
//                    previewFrameCallback.onCameraFront(tmp, getOrientation());
//                } else {
//
//                }
                previewFrameCallback.onCameraFront(bytes, getOrientation());
                previewFrameCallback.onCameraFront(_bytes, getOrientation());
            }
            image.close();
        }
    };

    private OnImageAvailableListener backAvailableListener = new OnImageAvailableListener() {
        @Override
        public void onImageAvailable(ImageReader reader) {
            Image image = reader.acquireLatestImage();
            if (previewFrameCallback != null) {
                byte[][] bytes = getBytes(image, getOrientation());
                byte[] _bytes = new byte[capacity];
                int count = 0;
                for (byte[] b : bytes) {
                    System.arraycopy(b, 0, _bytes, count, b.length);
                    count += b.length;
                }
                previewFrameCallback.onCameraBack(bytes, getOrientation());
                previewFrameCallback.onCameraBack(_bytes, getOrientation());
            }
            image.close();
        }
    };

    private CameraConfig.SurfaceCallback surfaceCallback = new CameraConfig.SurfaceCallback() {
        @SuppressLint("MissingPermission")
        @Override
        public void onSurfaceTextureAvailable(CameraConfig config) {
            openCamera(cameraManager, config);
        }
    };

    private OnPreviewFrameCallback previewFrameCallback = null;

    /**
     * 设置预览回调，均在子线程
     */
    public void setPreviewFrameCallback(OnPreviewFrameCallback previewFrameCallback) {
        this.previewFrameCallback = previewFrameCallback;
    }

    public interface OnPreviewFrameCallback {
        void onCameraFront(byte[][] bytes, int orientation);

        void onCameraBack(byte[][] bytes, int orientation);

        void onCameraFront(byte[] bytes, int orientation);

        void onCameraBack(byte[] bytes, int orientation);
    }

}
