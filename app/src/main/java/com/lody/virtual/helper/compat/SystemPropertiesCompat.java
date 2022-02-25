package com.lody.virtual.helper.compat;

import android.text.TextUtils;
import com.lody.virtual.helper.utils.Reflect;

/* loaded from: classes.dex */
public class SystemPropertiesCompat {
    public static String get(String str, String str2) {
        try {
            return (String) Reflect.m18997on("android.os.SystemProperties").call("get", str, str2).get();
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static String get(String str) {
        try {
            return (String) Reflect.m18997on("android.os.SystemProperties").call("get", str).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isExist(String str) {
        return !TextUtils.isEmpty(get(str));
    }

    public static int getInt(String str, int i) {
        try {
            return ((Integer) Reflect.m18997on("android.os.SystemProperties").call("getInt", str, Integer.valueOf(i)).get()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }
}
