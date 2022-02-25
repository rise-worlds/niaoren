package com.king.sdk.p053a;

import android.content.Intent;
import android.os.IBinder;
import java.lang.reflect.Method;

/* renamed from: com.king.sdk.a.a */
/* loaded from: classes.dex */
public final class BinderIntent {

    /* renamed from: a */
    private static Method f9407a;

    /* renamed from: b */
    private static Method f9408b;

    static {
        try {
            Method declaredMethod = Intent.class.getDeclaredMethod("getIBinderExtra", String.class);
            f9407a = declaredMethod;
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = Intent.class.getDeclaredMethod("putExtra", String.class, IBinder.class);
            f9408b = declaredMethod2;
            declaredMethod2.setAccessible(true);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static IBinder m19824a(Intent intent, String str) {
        try {
            return (IBinder) f9407a.invoke(intent, str);
        } catch (Exception unused) {
            return null;
        }
    }
}
