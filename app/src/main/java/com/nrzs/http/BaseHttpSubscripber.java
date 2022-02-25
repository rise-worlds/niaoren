package com.nrzs.http;

import android.arch.lifecycle.MutableLiveData;
import p110z1.Subscriber;
import p110z1.dby;

/* renamed from: com.nrzs.http.d */
/* loaded from: classes2.dex */
public class BaseHttpSubscripber<T> implements Subscriber<T> {

    /* renamed from: a */
    private MutableLiveData<T> f11134a = new MutableLiveData<>();

    @Override // p110z1.Subscriber
    public void onComplete() {
    }

    /* renamed from: a */
    public MutableLiveData<T> m18576a() {
        return this.f11134a;
    }

    @Override // p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        dbyVar.request(1L);
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        this.f11134a.setValue(t);
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        this.f11134a.setValue(null);
    }
}
