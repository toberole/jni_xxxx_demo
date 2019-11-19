#include "test.h"
#include <jni.h>
#include <cstdlib>
#include "log.h"

/*
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test
 * Signature: ()V
 */
JNIEXPORT void JNICALL Data_test(JNIEnv *env, jobject jobj) {
    LOGI("Data_test 非静态native method");
}

/*
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test1
 * Signature: ()V
 */
JNIEXPORT void JNICALL Data_test1(JNIEnv *env, jclass jclazz) {
    LOGI("Data_test1 静态native method");
}

/*
 * 传递基础数据类型
 *
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test2
 * Signature: (BCSIJFDZ)V
 */
JNIEXPORT void JNICALL Data_test2(
        JNIEnv *env, jclass jclazz,
        jbyte jb, jchar jc,
        jshort js, jint ji,
        jlong jl, jfloat jf,
        jdouble jd, jboolean jbo) {
    int8_t c_jb = jb;
    LOGI("Data_test2 jbyte %d", c_jb);

    uint16_t c_jch = jc;
    LOGI("Data_test2 jchar %d", c_jch);

    int16_t c_js = js;
    LOGI("Data_test2 jshort %d", c_js);

    int32_t c_ji = ji;
    LOGI("Data_test2 jint %d", c_ji);

    int64_t c_jl = jl;
    LOGI("Data_test2 jlong %ld", c_jl);

    float c_jf = jf;
    LOGI("Data_test2 jfloat %f", c_jf);

    double c_jd = jd;
    LOGI("Data_test2 jdouble %f", c_jd);

    uint8_t c_jbo = jbo;
    LOGI("Data_test2 jboolean %d", c_jbo);

}

/*
 * 传递 类对象
 *
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test3
 * Signature: (Lcom/xiaoge/org/jni/Bean;)V
 */
JNIEXPORT void JNICALL
Data_test3(JNIEnv *env, jclass jclazz, jobject jbean) {
    // 获取bean的class
    const char *bean_name = "com/xiaoge/org/jni/Bean";
    jclass bean_clazz = env->FindClass(bean_name);

    // 获取bean的字段
    jfieldID tag_fieldID = env->GetFieldID(bean_clazz, "tag", "Ljava/lang/String;");

    // 获取tag字段的值
    jstring tag_str = static_cast<jstring>(env->GetObjectField(jbean, tag_fieldID));
    const char *tagv = env->GetStringUTFChars(tag_str, nullptr);
    LOGI("Data_test3 bean tag: %s", tagv);
    env->ReleaseStringUTFChars(tag_str, tagv);
}


/*
 * 传递 String
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test4
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Data_test4
        (JNIEnv *env, jclass jclazz, jstring jstr) {
    const char *str = env->GetStringUTFChars(jstr, nullptr);
    LOGI("Data_test4 str: %s", str);
    env->ReleaseStringUTFChars(jstr, str);
}

/*
 * 传递 数组
 *
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test5
 * Signature: ([II)V
 */
JNIEXPORT void JNICALL
Data_test5(JNIEnv *env, jclass jclazz, jintArray jiarr, jint jlen) {
    // 方法一
    //  获取到数组
    jint *arr = env->GetIntArrayElements(jiarr, nullptr);

    // 获取数组的长度
    int len = env->GetArrayLength(jiarr);
    int total = 0;
    for (int i = 0; i < len; ++i) {
        total += arr[i];
    }
    LOGI("Data_test5 sum arr: %d", total);

    // 方法二
    jint buf[jlen];
    env->GetIntArrayRegion(jiarr, 0, jlen, buf);
    total = 0;
    for (int i = 0; i < jlen; ++i) {
        total += buf[i];
    }
    LOGI("Data_test5 sum arr: %d", total);

    // 对数组赋值
    for (int i = 0; i < jlen; ++i) {
        buf[i] = buf[i] + 10;
    }
    env->SetIntArrayRegion(jiarr, 0, jlen, buf);
}

/*
 * 传递 String数组
 *
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test5_1
 * Signature: ([Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Data_test5_11
        (JNIEnv *env, jclass jclazz, jobjectArray jsarr) {
    jsize len = env->GetArrayLength(jsarr);
    for (int i = 0; i < len; ++i) {
        jstring jstr = static_cast<jstring>(env->GetObjectArrayElement(jsarr, i));
        const char *s = env->GetStringUTFChars(jstr, nullptr);
        LOGI("test5_1 jsarr[%d] = %s", i, s);
        env->ReleaseStringUTFChars(jstr, s);
    }
}

/*
 * 传递 byte数组
 *
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test5_2
 * Signature: ([B)V
 */
JNIEXPORT void JNICALL Data_test5_12
        (JNIEnv *env, jclass jclazz, jbyteArray jbarr) {
    jbyte *jbs = env->GetByteArrayElements(jbarr, nullptr);
    jsize len = env->GetArrayLength(jbarr);
    for (int i = 0; i < len; ++i) {
        LOGI("test5_2 jbarr[%d] = %d", i, jbs[i]);
    }

    env->ReleaseByteArrayElements(jbarr, jbs, 0);
}

char **stringArrToCharArr(JNIEnv *env, jclass jc, jobjectArray strArray) {
    jstring jstr;
    jsize len = env->GetArrayLength(strArray);
    char **pstr = (char **) malloc(len * sizeof(char *));
    int i = 0;

    for (i = 0; i < len; i++) {
        jstr = static_cast<jstring>(env->GetObjectArrayElement(strArray, i));
        pstr[i] = (char *) (env->GetStringUTFChars(jstr, 0));
    }
    return pstr;
}

/*
 * 传递 char 数组
 *
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test5_3
 * Signature: ([C)V
 */
JNIEXPORT void JNICALL Data_test5_13
        (JNIEnv *env, jclass jclazz, jcharArray jch_arr) {
    jchar *arr = env->GetCharArrayElements(jch_arr, nullptr);
    jsize len = env->GetArrayLength(jch_arr);

    char16_t buf[len + 1];
    for (int i = 0; i < len; ++i) {
        buf[i] = arr[i];
    }

    LOGI("test5_3 %s", buf);
    env->ReleaseCharArrayElements(jch_arr, arr, 0);
}

/*
 * 传递 callback
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test5_4
 * Signature: (Ljava/lang/Object;)V
 */
JNIEXPORT void JNICALL Data_test5_14
        (JNIEnv *env, jclass jclazz, jobject jcb) {

    jclass jcb_clazz = env->GetObjectClass(jcb);
    if (jcb_clazz != nullptr) {
        jmethodID onError_id = env->GetMethodID(jcb_clazz, "onError", "(ILjava/lang/String;)V");
        jstring errormsg = env->NewStringUTF("hello error");
        env->CallVoidMethod(jcb, onError_id, -1, errormsg);

        jmethodID onCallback_Short_id = env->GetMethodID(jcb_clazz, "onCallback_Short", "(I[S)V");
        short arr[] = {1, 2, 3};
        int len = sizeof(arr) / sizeof(arr[0]);
        jshortArray jshort_arr = env->NewShortArray(len);
        env->SetShortArrayRegion(jshort_arr, 0, len, arr);
        env->CallVoidMethod(jcb, onCallback_Short_id, 1, jshort_arr);

        jmethodID onCallback_byte_id = env->GetMethodID(jcb_clazz, "onCallback_byte", "(I[B)V");
        int8_t bytes[] = {1, 2, 3};
        len = sizeof(bytes) / sizeof(bytes[0]);
        jbyteArray jbytes = env->NewByteArray(len);
        env->SetByteArrayRegion(jbytes, 0, len, bytes);
        env->CallVoidMethod(jcb, onCallback_byte_id, 1, jbytes);
    }
}

/*
 * 传递 callback
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test5_4_1
 * Signature: (Lcom/xiaoge/org/jni/Data$Callback;)V
 */
JNIEXPORT void JNICALL Data_test5_14_11
        (JNIEnv *env, jclass jclazz, jobject jcb) {
    jclass jcb_clazz = env->FindClass("com/xiaoge/org/jni/Data$Callback");
    if (jcb_clazz != nullptr) {
        jmethodID onError_id = env->GetMethodID(jcb_clazz, "onError", "(ILjava/lang/String;)V");
        jstring errormsg = env->NewStringUTF("hello error");
        env->CallVoidMethod(jcb, onError_id, -1, errormsg);

        jmethodID onCallback_Short_id = env->GetMethodID(jcb_clazz, "onCallback_Short", "(I[S)V");
        short arr[] = {1, 2, 3};
        int len = sizeof(arr) / sizeof(arr[0]);
        jshortArray jshort_arr = env->NewShortArray(len);
        env->SetShortArrayRegion(jshort_arr, 0, len, arr);
        env->CallVoidMethod(jcb, onCallback_Short_id, 1, jshort_arr);

        jmethodID onCallback_byte_id = env->GetMethodID(jcb_clazz, "onCallback_byte", "(I[B)V");
        int8_t bytes[] = {1, 2, 3};
        len = sizeof(bytes) / sizeof(bytes[0]);
        jbyteArray jbytes = env->NewByteArray(len);
        env->SetByteArrayRegion(jbytes, 0, len, bytes);
        env->CallVoidMethod(jcb, onCallback_byte_id, 1, jbytes);
    }
}

/*
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test5_5
 * Signature: (Lcom/xiaoge/org/jni/Gender;)V
 */
JNIEXPORT void JNICALL Data_test5_15
        (JNIEnv *env, jclass jclazz, jobject jobj_enum) {
    jclass jobj_enum_clazz = env->GetObjectClass(jobj_enum);
    jmethodID getValue_id = env->GetMethodID(jobj_enum_clazz, "getValue", "()I");
    int v = env->CallIntMethod(jobj_enum, getValue_id);

    LOGI("jobj_enum v = %d", v);
}

/*
 * 返回值 byte
 *
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test6
 * Signature: ()B
 */
JNIEXPORT jbyte JNICALL Data_test6
        (JNIEnv *env, jclass jclazz) {
    LOGI("Data_test6");
    int8_t ret = 66;
    return ret;
}

/*
 * 返回值 char
 *
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test7
 * Signature: ()C
 */
JNIEXPORT jchar JNICALL Data_test7(JNIEnv *env, jclass jclazz) {
    LOGI("Data_test7");
    int16_t ret = 97;
    return ret;
}

/*
 * 返回值 int
 *
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test8
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Data_test8(JNIEnv *env, jclass jclazz) {
    LOGI("Data_test8");
    int32_t ret = 6666;
    return ret;
}

/*
 * 返回值 String
 *
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test9
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Data_test9(JNIEnv *env, jclass jclazz) {
    jstring jstr = env->NewStringUTF("hello data_test9");
    return jstr;
}

/*
 * Class:     com_xiaoge_org_jni_Data
 * Method:    test10
 * Signature: ()[I
 */
JNIEXPORT jintArray JNICALL Data_test10
        (JNIEnv *env, jclass jclazz) {
    int arr[] = {1, 2, 3, 4, 5};
    int len = sizeof(arr) / sizeof(arr[0]);
    jintArray jintArr = env->NewIntArray(len);
    env->SetIntArrayRegion(jintArr, 0, len, arr);
    return jintArr;
}