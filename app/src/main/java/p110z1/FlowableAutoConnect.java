package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.aza */
/* loaded from: classes3.dex */
public final class FlowableAutoConnect<T> extends Flowable<T> {

    /* renamed from: b */
    final ConnectableFlowable<? extends T> f17853b;

    /* renamed from: c */
    final int f17854c;

    /* renamed from: d */
    final Consumer<? super Disposable> f17855d;

    /* renamed from: e */
    final AtomicInteger f17856e = new AtomicInteger();

    public FlowableAutoConnect(ConnectableFlowable<? extends T> aueVar, int i, Consumer<? super Disposable> aumVar) {
        this.f17853b = aueVar;
        this.f17854c = i;
        this.f17855d = aumVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17853b.subscribe(dbxVar);
        if (this.f17856e.incrementAndGet() == this.f17854c) {
            this.f17853b.mo9740l(this.f17855d);
        }
    }
}
