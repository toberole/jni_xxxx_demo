#include <jni.h>

#ifndef _Included_com_xiaoge_org_jni_Test
#define _Included_com_xiaoge_org_jni_Test
/**
 * 按照C语言的方式生成方法签名
 */
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_xiaoge_org_jni_Test
 * Method:    test
 * Signature: (BCSIFDLjava/lang/String;Ljava/lang/Object;)V
 */
void Test_test
        (JNIEnv *, jobject, jbyte, jchar, jshort, jint, jfloat, jdouble, jstring, jobject);

/*
 * Class:     com_xiaoge_org_jni_Test
 * Method:    test1
 * Signature: (B)V
 */
void Test_test1
        (JNIEnv *, jobject, jbyte);

/*
 * Class:     com_xiaoge_org_jni_Test
 * Method:    test2
 * Signature: (ILjava/lang/Object;)V
 */
void Test_test2
        (JNIEnv *, jobject, jint, jobject);


JNIEXPORT jint JNICALL Test1_sysHello(JNIEnv *env, jclass jclazz, jstring jstr);


#ifdef __cplusplus
}
#endif

#endif
