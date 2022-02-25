package p110z1;

import p110z1.FlowableConcatMapEager;

/* renamed from: z1.azo */
/* loaded from: classes3.dex */
public final class FlowableConcatMapEagerPublisher<T, R> extends Flowable<R> {

    /* renamed from: b */
    final Publisher<T> f17964b;

    /* renamed from: c */
    final Function<? super T, ? extends Publisher<? extends R>> f17965c;

    /* renamed from: d */
    final int f17966d;

    /* renamed from: e */
    final int f17967e;

    /* renamed from: f */
    final ErrorMode f17968f;

    public FlowableConcatMapEagerPublisher(Publisher<T> dbwVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, int i2, ErrorMode bsuVar) {
        this.f17964b = dbwVar;
        this.f17965c = aunVar;
        this.f17966d = i;
        this.f17967e = i2;
        this.f17968f = bsuVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f17964b.subscribe(new FlowableConcatMapEager.C4020a(dbxVar, this.f17965c, this.f17966d, this.f17967e, this.f17968f));
    }
}
