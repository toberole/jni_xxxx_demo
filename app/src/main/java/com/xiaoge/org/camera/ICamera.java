package com.xiaoge.org.camera;

/**
 * camera 接口
 * Created by Administrator on 2018/11/22.
 */

public interface ICamera {

    void openCamera();

    void closeCamera();

    void swtchCamera();

    void takePhoto();

    void startRecord();

    void stopRecord();

}
