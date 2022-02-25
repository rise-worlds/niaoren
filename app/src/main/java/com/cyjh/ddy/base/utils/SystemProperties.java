package com.cyjh.ddy.base.utils;

import java.lang.reflect.Method;

/* renamed from: com.cyjh.ddy.base.utils.t */
/* loaded from: classes.dex */
public class SystemProperties {

    /* renamed from: a */
    private static final String f7145a = "t";

    /* renamed from: b */
    private static Method f7146b;

    /* renamed from: c */
    private static SystemProperties f7147c;

    /* renamed from: a */
    public static SystemProperties m21736a() {
        if (f7147c == null) {
            synchronized (SystemProperties.class) {
                if (f7147c == null) {
                    f7147c = new SystemProperties();
                }
            }
        }
        return f7147c;
    }

    private SystemProperties() {
        f7146b = m21735a(m21733b("android.os.SystemProperties"));
    }

    /* renamed from: b */
    private Class m21733b(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            CLog.m21883e(f7145a, e.getMessage());
            try {
                return ClassLoader.getSystemClassLoader().loadClass(str);
            } catch (ClassNotFoundException e2) {
                CLog.m21883e(f7145a, e2.getMessage());
                return null;
            }
        }
    }

    /* renamed from: a */
    private Method m21735a(Class cls) {
        if (cls == null) {
            return null;
        }
        try {
            return cls.getMethod("get", String.class);
        } catch (Exception e) {
            CLog.m21883e(f7145a, e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public final String m21734a(String str) {
        if (str == null) {
            return "";
        }
        try {
            Object obj = null;
            if (f7146b != null) {
                obj = f7146b.invoke(null, str);
            }
            String str2 = (String) obj;
            return str2 != null ? str2.trim() : "";
        } catch (Exception unused) {
            return "";
        }
    }
}
