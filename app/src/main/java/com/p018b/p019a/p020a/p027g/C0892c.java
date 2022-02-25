package com.p018b.p019a.p020a.p027g;

import java.lang.reflect.Method;

/* compiled from: AndroidPlatform.java */
/* renamed from: com.b.a.a.g.c */
/* loaded from: classes.dex */
final class C0892c {

    /* renamed from: a */
    private final Method f6027a;

    /* renamed from: b */
    private final Method f6028b;

    /* renamed from: c */
    private final Method f6029c;

    private C0892c(Method method, Method method2, Method method3) {
        this.f6027a = method;
        this.f6028b = method2;
        this.f6029c = method3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final Object m24594a(String str) {
        Method method = this.f6027a;
        if (method != null) {
            try {
                Object invoke = method.invoke(null, new Object[0]);
                this.f6028b.invoke(invoke, str);
                return invoke;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m24595a(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            this.f6029c.invoke(obj, new Object[0]);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C0892c m24596a() {
        Method method;
        Method method2;
        Method method3 = null;
        try {
            Class<?> cls = Class.forName("dalvik.system.CloseGuard");
            method3 = cls.getMethod("get", new Class[0]);
            method = cls.getMethod("open", String.class);
            method2 = cls.getMethod("warnIfOpen", new Class[0]);
        } catch (Exception unused) {
            method2 = null;
            method = null;
        }
        return new C0892c(method3, method, method2);
    }
}
