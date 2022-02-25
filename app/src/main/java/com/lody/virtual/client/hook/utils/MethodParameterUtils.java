package com.lody.virtual.client.hook.utils;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.utils.ArrayUtils;
import java.util.Arrays;
import java.util.HashSet;

/* loaded from: classes.dex */
public class MethodParameterUtils {
    public static <T> T getFirstParam(Object[] objArr, Class<T> cls) {
        int indexOfFirst;
        if (objArr == null || (indexOfFirst = ArrayUtils.indexOfFirst(objArr, cls)) == -1) {
            return null;
        }
        return (T) objArr[indexOfFirst];
    }

    public static String replaceFirstAppPkg(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] instanceof String) {
                String str = (String) objArr[i];
                if (VirtualCore.get().isAppInstalled(str)) {
                    objArr[i] = VirtualCore.get().getHostPkg();
                    return str;
                }
            }
        }
        return null;
    }

    public static String replaceLastAppPkg(Object[] objArr) {
        int indexOfLast = ArrayUtils.indexOfLast(objArr, String.class);
        if (indexOfLast == -1) {
            return null;
        }
        String str = (String) objArr[indexOfLast];
        objArr[indexOfLast] = VirtualCore.get().getHostPkg();
        return str;
    }

    public static String replaceSequenceAppPkg(Object[] objArr, int i) {
        int indexOf = ArrayUtils.indexOf(objArr, String.class, i);
        if (indexOf == -1) {
            return null;
        }
        String str = (String) objArr[indexOf];
        objArr[indexOf] = VirtualCore.get().getHostPkg();
        return str;
    }

    public static int getParamsIndex(Class[] clsArr, Class<?> cls) {
        for (int i = 0; i < clsArr.length; i++) {
            if (clsArr[i].equals(cls)) {
                return i;
            }
        }
        return -1;
    }

    public static int getIndex(Object[] objArr, Class<?> cls) {
        int i = 0;
        while (i < objArr.length) {
            Object obj = objArr[i];
            if ((obj != null && obj.getClass() == cls) || cls.isInstance(obj)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static Class<?>[] getAllInterface(Class cls) {
        HashSet hashSet = new HashSet();
        getAllInterfaces(cls, hashSet);
        Class<?>[] clsArr = new Class[hashSet.size()];
        hashSet.toArray(clsArr);
        return clsArr;
    }

    public static void getAllInterfaces(Class cls, HashSet<Class<?>> hashSet) {
        Class<?>[] interfaces = cls.getInterfaces();
        if (interfaces.length != 0) {
            hashSet.addAll(Arrays.asList(interfaces));
        }
        if (cls.getSuperclass() != Object.class) {
            getAllInterfaces(cls.getSuperclass(), hashSet);
        }
    }
}
