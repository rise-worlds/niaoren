package p110z1;

/* renamed from: z1.bbm */
/* loaded from: classes3.dex */
public final class FlowableJust<T> extends Flowable<T> implements ScalarCallable<T> {

    /* renamed from: b */
    private final T f18178b;

    public FlowableJust(T t) {
        this.f18178b = t;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        dbxVar.onSubscribe(new ScalarSubscription(dbxVar, this.f18178b));
    }

    @Override // p110z1.ScalarCallable, java.util.concurrent.Callable
    public T call() {
        return this.f18178b;
    }
}
