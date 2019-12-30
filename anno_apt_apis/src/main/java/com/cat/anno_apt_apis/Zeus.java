package com.cat.anno_apt_apis;

import android.util.Log;

import java.lang.reflect.Constructor;

import androidx.annotation.NonNull;

public class Zeus {
    public static void bind(@NonNull Object target) {
        try {
            Class clazz = target.getClass();
            String clazzname = clazz.getName();
            String targetClazzName = clazzname + "ViewBinding";
            Class targetClazz = Class.forName(targetClazzName);
            Log.i("anno-xxxx", "clazzname: " + clazzname + " targetClazzName: " + targetClazzName);
            Constructor constructor = targetClazz.getConstructor(clazz);
            constructor.newInstance(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
