#include <jni.h>
#include "log.h"

#include "test.h"

/**
 * 动态注册的java的类
 */
const char *Test_Clazz = "com/xiaoge/org/jni/Test";
const char *Test1_Clazz = "com/xiaoge/org/jni/Test1";

/**
 * 动态注册的参数
 */
static const JNINativeMethod Test_Methods[] = {
        {
                "test",// java层的方法
                "(BCSIFDLjava/lang/String;Ljava/lang/Object;)V",// java层方法对应的签名
                (void *) Test_test// native 层方法
        },
        {
                "test1",// java层的方法
                "(B)V",// java层方法对应的签名
                (void *) Test_test1// native 层方法
        },
        {
                "test2",// java层的方法
                "(ILjava/lang/Object;)V",// java层方法对应的签名
                (void *) Test_test2// native 层方法
        }
};

static const JNINativeMethod Test1_Methods[] = {
        {
                "sysHello",// java层的方法
                "(Ljava/lang/String;)I",// java层方法对应的签名
                (void *) Test1_sysHello// native 层方法
        },
};

jint RegisterNativeMethods(JNIEnv *env) {
    jclass cla = env->FindClass(Test_Clazz);
    if (cla == nullptr) {
        LOGI("找不到类 %s", Test_Clazz);
        return JNI_ERR;
    }

    int method_count = sizeof(Test_Methods) / sizeof(Test_Methods[0]);
    int ret = env->RegisterNatives(cla, Test_Methods, method_count);
    if (ret == JNI_ERR) {
        return JNI_ERR;
    }

    cla = env->FindClass(Test1_Clazz);
    if (cla == nullptr) {
        LOGI("找不到类 %s", Test1_Clazz);
        return JNI_ERR;
    }

    method_count = sizeof(Test1_Methods) / sizeof(Test1_Methods[0]);
    ret = env->RegisterNatives(cla, Test1_Methods, method_count);

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



