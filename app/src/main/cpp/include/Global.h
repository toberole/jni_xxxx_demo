#ifndef JNI_XXXX_DEMO_GLOBAL_H
#define JNI_XXXX_DEMO_GLOBAL_H

#include "test.h"

/**
 * 动态注册的java的类
 */
const char *Test_Clazz = "com/xiaoge/org/jni/Test";
const char *Test1_Clazz = "com/xiaoge/org/jni/Test1";
const char *Data_Clazz = "com/xiaoge/org/jni/Data";

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
        }
};

static const JNINativeMethod Data_Methods[] = {
        {
                "test",// java层的方法
                "()V",// java层方法对应的签名
                (void *) Data_test// native 层方法
        },
        {
                "test1",// java层的方法
                "()V",// java层方法对应的签名
                (void *) Data_test1// native 层方法
        },
        {
                "test2",// java层的方法
                "(BCSIJFDZ)V",// java层方法对应的签名
                (void *) Data_test2// native 层方法
        },
        {
                "test3",// java层的方法
                "(Lcom/xiaoge/org/jni/Bean;)V",// java层方法对应的签名
                (void *) Data_test3// native 层方法
        },
        {
                "test4",// java层的方法
                "(Ljava/lang/String;)V",// java层方法对应的签名
                (void *) Data_test4// native 层方法
        },
        {
                "test5",// java层的方法
                "([II)V",// java层方法对应的签名
                (void *) Data_test5// native 层方法
        },
        {
                "test5_1",// java层的方法
                "([Ljava/lang/String;)V",// java层方法对应的签名
                (void *) Data_test5_11// native 层方法
        },
        {
                "test5_2",// java层的方法
                "([B)V",// java层方法对应的签名
                (void *) Data_test5_12// native 层方法
        },
        {
                "test5_3",// java层的方法
                "([C)V",// java层方法对应的签名
                (void *) Data_test5_13// native 层方法
        },
        {
                "test5_4",// java层的方法
                "(Ljava/lang/Object;)V",// java层方法对应的签名
                (void *) Data_test5_14// native 层方法
        },
        {
                "test5_4_1",// java层的方法
                "(Lcom/xiaoge/org/jni/Data$Callback;)V",// 注意内部类和内部接口的签名
                (void *) Data_test5_14_11// native 层方法
        },
        {
                "test5_5",// java层的方法
                "(Lcom/xiaoge/org/jni/Gender;)V",
                (void *) Data_test5_15// native 层方法
        },
        {
                "test6",// java层的方法
                "()B",
                (void *) Data_test6// native 层方法
        },
        {
                "test7",// java层的方法
                "()C",
                (void *) Data_test7// native 层方法
        },
        {
                "test8",// java层的方法
                "()I",
                (void *) Data_test8// native 层方法
        },
        {
                "test9",// java层的方法
                "()Ljava/lang/String;",
                (void *) Data_test9// native 层方法
        },
        {
                "test10",// java层的方法
                "()[I",
                (void *) Data_test10// native 层方法
        },
        {
                "test11",// java层的方法
                "()Lcom/xiaoge/org/jni/Bean;",
                (void *) Data_test11// native 层方法
        },
        {
                "test12",// java层的方法
                "()Lcom/xiaoge/org/jni/Bean;",
                (void *) Data_test12// native 层方法
        }
};


#endif //JNI_XXXX_DEMO_GLOBAL_H
