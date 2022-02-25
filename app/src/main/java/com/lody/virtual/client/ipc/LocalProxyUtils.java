package com.lody.virtual.client.ipc;

import android.os.Binder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class LocalProxyUtils {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T genProxy(Class<T> cls, Object obj) {
        return obj;
    }

    /* renamed from: com.lody.virtual.client.ipc.LocalProxyUtils$1 */
    /* loaded from: classes.dex */
    static class C17961 implements InvocationHandler {
        final /* synthetic */ Object val$base;

        C17961(Object obj) {
            this.val$base = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return method.invoke(this.val$base, objArr);
            } catch (Throwable th) {
                try {
                    if (th.getCause() == null) {
                        throw th;
                    }
                    throw th.getCause();
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            }
        }
    }
}
