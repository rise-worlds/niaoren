package com.tencent.smtt.utils;

import android.text.TextUtils;
import java.lang.reflect.Method;

/* renamed from: com.tencent.smtt.utils.j */
/* loaded from: classes2.dex */
public class PropertyUtils {

    /* renamed from: a */
    private static Class f13382a;

    /* renamed from: b */
    private static Method f13383b;

    static {
        try {
            f13382a = Class.forName("android.os.SystemProperties");
            f13383b = f13382a.getDeclaredMethod("get", String.class, String.class);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public static String m16409a(String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : m16408b(str, str2);
    }

    /* renamed from: b */
    private static String m16408b(String str, String str2) {
        Method method;
        Class cls = f13382a;
        if (cls == null || (method = f13383b) == null) {
            return str2;
        }
        try {
            return (String) method.invoke(cls, str, str2);
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }
}
