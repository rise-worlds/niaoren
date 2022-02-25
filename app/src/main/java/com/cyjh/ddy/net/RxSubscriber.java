package com.cyjh.ddy.net;

import p110z1.Subscriber;
import p110z1.dby;

/* renamed from: com.cyjh.ddy.net.b */
/* loaded from: classes.dex */
public abstract class RxSubscriber<T> implements Subscriber<T> {
    /* renamed from: a */
    public void m21459a() {
    }

    /* renamed from: a */
    public abstract void m21458a(T t);

    /* renamed from: a */
    public abstract void m21457a(String str);

    /* renamed from: a */
    public void m21455a(dby dbyVar) {
    }

    /* renamed from: b */
    public void m21454b(T t) {
        m21458a((RxSubscriber<T>) t);
    }

    /* renamed from: a */
    public void m21456a(Throwable th) {
        m21457a(th.getMessage());
    }
}
