package com.lody.virtual.helper.utils;

/* loaded from: classes.dex */
public class ClassUtils {
    public static boolean isClassExist(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static void fixArgs(Class<?>[] clsArr, Object[] objArr) {
        for (int i = 0; i < clsArr.length; i++) {
            if (clsArr[i] == Integer.TYPE && objArr[i] == null) {
                objArr[i] = 0;
            } else if (clsArr[i] == Boolean.TYPE && objArr[i] == null) {
                objArr[i] = false;
            }
        }
    }
}
