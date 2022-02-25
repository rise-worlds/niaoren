package com.goldcoast.sdk.p052c;

import java.lang.reflect.Method;

/* compiled from: SystemProperties.java */
/* renamed from: com.goldcoast.sdk.c.i */
/* loaded from: classes.dex */
public final class C1393i {

    /* renamed from: a */
    private static Class<?> f9019a;

    /* renamed from: b */
    private static Method f9020b;

    /* renamed from: c */
    private static Method f9021c;

    /* renamed from: d */
    private static Method f9022d;

    /* renamed from: e */
    private static Method f9023e;

    /* renamed from: f */
    private static Method f9024f;

    /* renamed from: g */
    private static Method f9025g;

    /* renamed from: a */
    public static String m20313a(String str) {
        try {
            return (String) f9020b.invoke(null, str);
        } catch (Exception unused) {
            return null;
        }
    }

    static {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            f9019a = cls;
            Method declaredMethod = cls.getDeclaredMethod("get", String.class);
            f9020b = declaredMethod;
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = f9019a.getDeclaredMethod("get", String.class, String.class);
            f9021c = declaredMethod2;
            declaredMethod2.setAccessible(true);
            Method declaredMethod3 = f9019a.getDeclaredMethod("getInt", String.class, Integer.TYPE);
            f9022d = declaredMethod3;
            declaredMethod3.setAccessible(true);
            Method declaredMethod4 = f9019a.getDeclaredMethod("getLong", String.class, Long.TYPE);
            f9023e = declaredMethod4;
            declaredMethod4.setAccessible(true);
            Method declaredMethod5 = f9019a.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
            f9024f = declaredMethod5;
            declaredMethod5.setAccessible(true);
            Method declaredMethod6 = f9019a.getDeclaredMethod("set", String.class, String.class);
            f9025g = declaredMethod6;
            declaredMethod6.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
