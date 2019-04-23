#include "com_jni_bus_JNI_Bus.h"

#include <jni.h>
#include <string>
#include <thread>
#include "log.h"

bool b = false;

JNIEXPORT jint JNI_OnLoad(JavaVM *jvm, void *reserved) {
    LOGI("%s","JNI_OnLoad");
    return JNI_VERSION_1_6;
}

JNIEXPORT jlong JNICALL Java_com_jni_bus_JNI_1Bus_init
        (JNIEnv *env, jobject jobj, jstring jstr_name, jstring jstr_pwd,jint jport,jobject jcb) {

    jclass cls = env->GetObjectClass(jcb);
    jmethodID jmid = env->GetMethodID(cls, "callback","(JLjava/lang/String;Ljava/lang/Object;)V");
    env->CallVoidMethod(jcb, jmid, NULL, NULL, NULL);
    if (1)return 0;



    const char *name = env->GetStringUTFChars(jstr_name, NULL);
    const char *pwd = env->GetStringUTFChars(jstr_pwd, NULL);

    std::string c_name = name;
    std::string c_pwd = pwd;

    std::thread task([c_name, c_pwd]() {
        if (c_name.compare("123") == 0 && c_pwd.compare("123") == 0) {
            b = true;
        }
    });

    task.join();

    return b;
}
