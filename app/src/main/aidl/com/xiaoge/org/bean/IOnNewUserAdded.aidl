package com.jni.org.bean;

import com.jni.org.bean.User;

interface IOnNewUserAdded{
    void onAddUser(in/*关键必须有*/ User user);
}