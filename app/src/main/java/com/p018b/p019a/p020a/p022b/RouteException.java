package com.p018b.p019a.p020a.p022b;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: com.b.a.a.b.e */
/* loaded from: classes.dex */
public final class RouteException extends RuntimeException {

    /* renamed from: a */
    private static final Method f5754a;

    /* renamed from: b */
    private IOException f5755b;

    static {
        Method method;
        try {
            method = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        } catch (Exception unused) {
            method = null;
        }
        f5754a = method;
    }

    public RouteException(IOException iOException) {
        super(iOException);
        this.f5755b = iOException;
    }

    /* renamed from: a */
    public final IOException m24793a() {
        return this.f5755b;
    }

    /* renamed from: a */
    public final void m24792a(IOException iOException) {
        IOException iOException2 = this.f5755b;
        Method method = f5754a;
        if (method != null) {
            try {
                method.invoke(iOException, iOException2);
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
        this.f5755b = iOException;
    }
}
