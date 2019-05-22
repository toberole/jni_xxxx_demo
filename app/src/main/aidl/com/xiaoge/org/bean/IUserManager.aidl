// aidl文件所在包名 生成的java文件会在此包中
package com.jni.org.bean;

import com.jni.org.bean.User;
import com.jni.org.bean.IOnNewUserAdded;

interface IUserManager{
    void addUser(in/*关键必须有*/ User user);
    List<User> getUsers();

    void registerListener(IOnNewUserAdded listener);
    void unRegisterListener(IOnNewUserAdded listener);
}