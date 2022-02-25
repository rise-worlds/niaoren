package p110z1;

import p110z1.FlowableTake;

/* renamed from: z1.bdu */
/* loaded from: classes3.dex */
public final class FlowableTakePublisher<T> extends Flowable<T> {

    /* renamed from: b */
    final Publisher<T> f18383b;

    /* renamed from: c */
    final long f18384c;

    public FlowableTakePublisher(Publisher<T> dbwVar, long j) {
        this.f18383b = dbwVar;
        this.f18384c = j;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f18383b.subscribe(new FlowableTake.C4208a(dbxVar, this.f18384c));
    }
}
