package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

/* renamed from: com.alipay.android.phone.mrpc.core.x */
/* loaded from: classes.dex */
public final class C0618x {

    /* renamed from: a */
    private AbstractC0600g f205a;

    /* renamed from: b */
    private C0620z f206b = new C0620z(this);

    public C0618x(AbstractC0600g gVar) {
        this.f205a = gVar;
    }

    /* renamed from: a */
    public final AbstractC0600g m25444a() {
        return this.f205a;
    }

    /* renamed from: a */
    public final <T> T m25443a(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new C0619y(this.f205a, cls, this.f206b));
    }
}
