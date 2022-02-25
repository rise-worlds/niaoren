package com.alipay.android.phone.mrpc.core;

import android.os.Looper;
import com.alipay.android.phone.mrpc.core.p010a.C0586d;
import com.alipay.android.phone.mrpc.core.p010a.C0587e;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.alipay.android.phone.mrpc.core.z */
/* loaded from: classes.dex */
public final class C0620z {

    /* renamed from: a */
    private static final ThreadLocal<Object> f210a = new ThreadLocal<>();

    /* renamed from: b */
    private static final ThreadLocal<Map<String, Object>> f211b = new ThreadLocal<>();

    /* renamed from: c */
    private byte f212c = 0;

    /* renamed from: d */
    private AtomicInteger f213d = new AtomicInteger();

    /* renamed from: e */
    private C0618x f214e;

    public C0620z(C0618x xVar) {
        this.f214e = xVar;
    }

    /* renamed from: a */
    public final Object m25442a(Method method, Object[] objArr) {
        if (!(Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper())) {
            OperationType operationType = (OperationType) method.getAnnotation(OperationType.class);
            boolean z = method.getAnnotation(ResetCookie.class) != null;
            Type genericReturnType = method.getGenericReturnType();
            method.getAnnotations();
            f210a.set(null);
            f211b.set(null);
            if (operationType != null) {
                String value = operationType.value();
                int incrementAndGet = this.f213d.incrementAndGet();
                try {
                    if (this.f212c == 0) {
                        C0587e eVar = new C0587e(incrementAndGet, value, objArr);
                        if (f211b.get() != null) {
                            eVar.mo25518a(f211b.get());
                        }
                        byte[] a = eVar.mo25519a();
                        f211b.set(null);
                        Object a2 = new C0586d(genericReturnType, (byte[]) new C0603j(this.f214e.m25444a(), method, incrementAndGet, value, a, z).mo25446a()).mo25520a();
                        if (genericReturnType != Void.TYPE) {
                            f210a.set(a2);
                        }
                    }
                    return f210a.get();
                } catch (RpcException e) {
                    e.setOperationType(value);
                    throw e;
                }
            } else {
                throw new IllegalStateException("OperationType must be set.");
            }
        } else {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
    }
}
