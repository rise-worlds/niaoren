package com.tendcloud.tenddata;

import android.view.View;
import java.lang.reflect.Method;
import java.util.Arrays;
import p110z1.Consts;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.bi */
/* loaded from: classes2.dex */
class C2712bi {

    /* renamed from: a */
    private final String f13576a;

    /* renamed from: b */
    private final Object[] f13577b;

    /* renamed from: c */
    private final Class f13578c;

    /* renamed from: d */
    private final Class f13579d;

    /* renamed from: e */
    private final Method f13580e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2712bi(Class cls, String str, Object[] objArr, Class cls2) {
        this.f13576a = str;
        this.f13577b = objArr;
        this.f13578c = cls2;
        this.f13580e = m16253b(cls);
        Method method = this.f13580e;
        if (method != null) {
            this.f13579d = method.getDeclaringClass();
            return;
        }
        throw new NoSuchMethodException("Method " + cls.getName() + Consts.f23430h + this.f13576a + " doesn't exit");
    }

    public String toString() {
        return "[Caller " + this.f13576a + "(" + Arrays.toString(this.f13577b) + ")]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Object[] m16258a() {
        return this.f13577b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Object m16257a(View view) {
        return m16256a(view, this.f13577b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Object m16256a(View view, Object[] objArr) {
        if (!this.f13579d.isAssignableFrom(view.getClass())) {
            return null;
        }
        try {
            return this.f13580e.invoke(view, objArr);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m16254a(Object[] objArr) {
        Class<?>[] parameterTypes = this.f13580e.getParameterTypes();
        if (objArr.length != parameterTypes.length) {
            return false;
        }
        for (int i = 0; i < objArr.length; i++) {
            Class a = m16255a(parameterTypes[i]);
            if (objArr[i] == null) {
                if (a == Byte.TYPE || a == Short.TYPE || a == Integer.TYPE || a == Long.TYPE || a == Float.TYPE || a == Double.TYPE || a == Boolean.TYPE || a == Character.TYPE) {
                    return false;
                }
            } else if (!a.isAssignableFrom(m16255a(objArr[i].getClass()))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private static Class m16255a(Class cls) {
        if (cls == Byte.class) {
            return Byte.TYPE;
        }
        if (cls == Short.class) {
            return Short.TYPE;
        }
        if (cls == Integer.class) {
            return Integer.TYPE;
        }
        if (cls == Long.class) {
            return Long.TYPE;
        }
        if (cls == Float.class) {
            return Float.TYPE;
        }
        if (cls == Double.class) {
            return Double.TYPE;
        }
        if (cls == Boolean.class) {
            return Boolean.TYPE;
        }
        return cls == Character.class ? Character.TYPE : cls;
    }

    /* renamed from: b */
    private Method m16253b(Class cls) {
        Method[] methods;
        Class[] clsArr = new Class[this.f13577b.length];
        int i = 0;
        while (true) {
            Object[] objArr = this.f13577b;
            if (i >= objArr.length) {
                break;
            }
            clsArr[i] = objArr[i].getClass();
            i++;
        }
        for (Method method : cls.getMethods()) {
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (name.equals(this.f13576a) && parameterTypes.length == this.f13577b.length && m16255a(this.f13578c).isAssignableFrom(m16255a(method.getReturnType()))) {
                boolean z = true;
                for (int i2 = 0; i2 < parameterTypes.length && z; i2++) {
                    z = m16255a(parameterTypes[i2]).isAssignableFrom(m16255a(clsArr[i2]));
                }
                if (z) {
                    return method;
                }
            }
        }
        return null;
    }
}
