package com.xiaoge.org.bean;

import com.xiaoge.org.bean.User;
// 非基础数据类型注意一定要加 in out
interface IOnNewUserAdded{
    void onAddUser(in/*关键必须有*/ User user);
}