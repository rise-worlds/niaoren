package p110z1;

/* renamed from: z1.aym */
/* loaded from: classes3.dex */
public final class CompletableToFlowable<T> extends Flowable<T> {

    /* renamed from: b */
    final CompletableSource f17799b;

    public CompletableToFlowable(CompletableSource arsVar) {
        this.f17799b = arsVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17799b.mo11309a(new SubscriberCompletableObserver(dbxVar));
    }
}
