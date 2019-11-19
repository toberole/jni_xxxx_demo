#include <jni.h>
#include "log.h"
#include "Global.h"

jint RegisterNativeMethods(JNIEnv *env) {
    jclass cla = env->FindClass(Test_Clazz);
    if (cla == nullptr) {
        LOGI("找不到类 %s", Test_Clazz);
        return JNI_ERR;
    }

    int method_count = sizeof(Test_Methods) / sizeof(Test_Methods[0]);
    LOGI("%s类 method_count=%d", Test_Clazz, method_count);
    int ret = env->RegisterNatives(cla, Test_Methods, method_count);
    if (ret == JNI_ERR) {
        LOGI("Register类 %s失败", Test_Clazz);
        return JNI_ERR;
    }

    cla = env->FindClass(Test1_Clazz);
    if (cla == nullptr) {
        LOGI("找不到类 %s", Test1_Clazz);
        return JNI_ERR;
    }

    method_count = sizeof(Test1_Methods) / sizeof(Test1_Methods[0]);
    LOGI("%s类 method_count=%d", Test1_Clazz, method_count);
    ret = env->RegisterNatives(cla, Test1_Methods, method_count);
    if (ret == JNI_ERR) {
        LOGI("Register类 %s失败", Test1_Clazz);
        return JNI_ERR;
    }

    cla = env->FindClass(Data_Clazz);
    if (cla == nullptr) {
        LOGI("找不到类 %s", Data_Clazz);
        return JNI_ERR;
    }

    method_count = sizeof(Data_Methods) / sizeof(Data_Methods[0]);
    LOGI("%s类 method_count=%d", Data_Clazz, method_count);
    ret = env->RegisterNatives(cla, Data_Methods, method_count);
    if (ret == JNI_ERR) {
        LOGI("Register类 %s失败", Data_Clazz);
        return JNI_ERR;
    }

    return ret;
}

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("... JNI_OnLoad ...");
    JNIEnv *env = NULL;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }
    jint result = RegisterNativeMethods(env);
    LOGD("RegisterNatives result: %d", result);
    return JNI_VERSION_1_6;
}



