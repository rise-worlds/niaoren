package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* renamed from: com.alipay.android.phone.mrpc.core.y */
/* loaded from: classes.dex */
public final class C0619y implements InvocationHandler {

    /* renamed from: a */
    protected AbstractC0600g f207a;

    /* renamed from: b */
    protected Class<?> f208b;

    /* renamed from: c */
    protected C0620z f209c;

    public C0619y(AbstractC0600g gVar, Class<?> cls, C0620z zVar) {
        this.f207a = gVar;
        this.f208b = cls;
        this.f209c = zVar;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        return this.f209c.m25442a(method, objArr);
    }
}
