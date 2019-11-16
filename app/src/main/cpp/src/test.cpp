#include "com_xiaoge_org_jni_OpenSLES.h"

extern "C"
JNIEXPORT void JNICALL
Java_com_xiaoge_org_jni_Test_test(JNIEnv *env, jobject instance,
                                  jbyte b, jchar ch,
                                  jshort s, jint i,
                                  jfloat f, jdouble d,
                                  jstring str_, jobject o) {
    const char *str = env->GetStringUTFChars(str_, 0);

    // TODO

    env->ReleaseStringUTFChars(str_, str);
}

