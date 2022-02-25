package p110z1;

import p110z1.FlowableMap;

/* renamed from: z1.bbt */
/* loaded from: classes3.dex */
public final class FlowableMapPublisher<T, U> extends Flowable<U> {

    /* renamed from: b */
    final Publisher<T> f18197b;

    /* renamed from: c */
    final Function<? super T, ? extends U> f18198c;

    public FlowableMapPublisher(Publisher<T> dbwVar, Function<? super T, ? extends U> aunVar) {
        this.f18197b = dbwVar;
        this.f18198c = aunVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super U> dbxVar) {
        this.f18197b.subscribe(new FlowableMap.C4118b(dbxVar, this.f18198c));
    }
}
