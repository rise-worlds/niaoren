package com.nrzs.http;

import p110z1.Flowable;
import p110z1.FlowableTransformer;
import p110z1.Function;
import p110z1.Publisher;

/* renamed from: com.nrzs.http.k */
/* loaded from: classes2.dex */
public class ResponseTransformer<T, D> implements FlowableTransformer<T, D> {

    /* renamed from: a */
    private Function<T, D> f11164a;

    /* renamed from: a */
    public void m18549a(Function<T, D> aunVar) {
        this.f11164a = aunVar;
    }

    @Override // p110z1.FlowableTransformer
    public Publisher<D> apply(Flowable<T> arvVar) {
        return arvVar.m10817v((Function<T, D>) this.f11164a);
    }
}
