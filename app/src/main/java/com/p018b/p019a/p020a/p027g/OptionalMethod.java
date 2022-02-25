package com.p018b.p019a.p020a.p027g;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: com.b.a.a.g.g */
/* loaded from: classes.dex */
final class OptionalMethod<T> {

    /* renamed from: a */
    private final Class<?> f6040a;

    /* renamed from: b */
    private final String f6041b;

    /* renamed from: c */
    private final Class[] f6042c;

    public OptionalMethod(Class<?> cls, String str, Class... clsArr) {
        this.f6040a = cls;
        this.f6041b = str;
        this.f6042c = clsArr;
    }

    /* renamed from: a */
    public final boolean m24589a(T t) {
        return m24591a(t.getClass()) != null;
    }

    /* renamed from: c */
    private Object m24586c(T t, Object... objArr) {
        Method a = m24591a(t.getClass());
        if (a == null) {
            return null;
        }
        try {
            return a.invoke(t, objArr);
        } catch (IllegalAccessException unused) {
            return null;
        }
    }

    /* renamed from: a */
    public final Object m24588a(T t, Object... objArr) {
        try {
            return m24586c(t, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    /* renamed from: d */
    private Object m24585d(T t, Object... objArr) {
        Method a = m24591a(t.getClass());
        if (a != null) {
            try {
                return a.invoke(t, objArr);
            } catch (IllegalAccessException e) {
                AssertionError assertionError = new AssertionError("Unexpectedly could not call: " + a);
                assertionError.initCause(e);
                throw assertionError;
            }
        } else {
            throw new AssertionError("Method " + this.f6041b + " not supported for object " + t);
        }
    }

    /* renamed from: b */
    public final Object m24587b(T t, Object... objArr) {
        try {
            return m24585d(t, objArr);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            }
            AssertionError assertionError = new AssertionError("Unexpected exception");
            assertionError.initCause(targetException);
            throw assertionError;
        }
    }

    /* renamed from: a */
    private Method m24591a(Class<?> cls) {
        Class<?> cls2;
        String str = this.f6041b;
        if (str == null) {
            return null;
        }
        Method a = m24590a(cls, str, this.f6042c);
        if (a == null || (cls2 = this.f6040a) == null || cls2.isAssignableFrom(a.getReturnType())) {
            return a;
        }
        return null;
    }

    /* renamed from: a */
    private static Method m24590a(Class<?> cls, String str, Class[] clsArr) {
        try {
            Method method = cls.getMethod(str, clsArr);
            try {
                if ((method.getModifiers() & 1) == 0) {
                    return null;
                }
                return method;
            } catch (NoSuchMethodException unused) {
                return method;
            }
        } catch (NoSuchMethodException unused2) {
            return null;
        }
    }
}
