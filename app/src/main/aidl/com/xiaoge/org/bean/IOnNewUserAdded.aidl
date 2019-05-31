package com.xiaoge.org.bean;

import com.xiaoge.org.bean.User;

interface IOnNewUserAdded{
    void onAddUser(in/*关键必须有*/ User user);
}