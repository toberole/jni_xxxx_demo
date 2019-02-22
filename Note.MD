一般情况下，同一个应用程序的Activity组件都是运行在同一个进程中，但是如果Activity配置了android:process这个属性，
那么，它就会运行在自己的进程中。
如果android:process属性的值以":"开头，则表示这个进程是私有的；
如果android:process属性的值以小写字母开头，则表示这是一个全局进程，允许其它应用程序组件也在这个进程中运行。

AIDL
aidl通信相当于是网络中RPC通信[客户端调用服务提供的方法]，aidl文件相当与是协议文件，ide会根据该
aidl文件生成对应的通信代码。
1、在app/src/main 下面创建一个aidl文件夹，该文件夹里面放置
aidl文件
2、构建工程，ide会自动的根据aidl文件生成对应的java文件

客户端通过aidl的方式调用服务端的方法是阻塞式的，且服务端的方法是在线程池中运行的[binder ipc底层的框架实现的]。