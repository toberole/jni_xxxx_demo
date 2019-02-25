#include "com_jni_bus_JNI_Bus.h"

#include <jni.h>
#include <string>
#include <thread>

bool b = false;

JNIEXPORT jboolean JNICALL Java_com_jni_bus_JNI_1Bus_init
        (JNIEnv *env, jclass jclzz, jstring jstr_name, jstring jstr_pwd) {
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
