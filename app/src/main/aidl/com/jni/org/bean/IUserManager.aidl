// aidl文件所在包名 对应生成的java文件会在此包中
package com.jni.org.bean;

import com.jni.org.bean.User;

interface IUserManager{
    void addUser(in/*关键必须有*/ User user);
    List<User> getUsers();
}