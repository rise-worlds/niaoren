package com.kaopu.download.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ReflectionUtils {
    public static Method getDeclaredMethod(Object obj, String str, Class<?>... clsArr) {
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        Method declaredMethod = getDeclaredMethod(obj, str, clsArr);
        declaredMethod.setAccessible(true);
        if (declaredMethod == null) {
            return null;
        }
        try {
            return declaredMethod.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Field getDeclaredField(Object obj, String str) {
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            try {
                return cls.getDeclaredField(str);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static void setFieldValue(Object obj, String str, Object obj2) {
        Field declaredField = getDeclaredField(obj, str);
        declaredField.setAccessible(true);
        try {
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
    }

    public static Object getFieldValue(Object obj, String str) {
        Field declaredField = getDeclaredField(obj, str);
        declaredField.setAccessible(true);
        try {
            return declaredField.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Field[] getDeclaredFields(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields.length != 0) {
                for (Field field : declaredFields) {
                    arrayList.add(field);
                }
            }
        }
        return (Field[]) arrayList.toArray(new Field[arrayList.size()]);
    }
}
