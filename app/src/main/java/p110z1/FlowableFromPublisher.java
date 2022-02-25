package p110z1;

/* renamed from: z1.bbb */
/* loaded from: classes3.dex */
public final class FlowableFromPublisher<T> extends Flowable<T> {

    /* renamed from: b */
    final Publisher<? extends T> f18116b;

    public FlowableFromPublisher(Publisher<? extends T> dbwVar) {
        this.f18116b = dbwVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f18116b.subscribe(dbxVar);
    }
}
