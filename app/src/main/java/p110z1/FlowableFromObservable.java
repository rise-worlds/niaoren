package p110z1;

/* renamed from: z1.bba */
/* loaded from: classes3.dex */
public final class FlowableFromObservable<T> extends Flowable<T> {

    /* renamed from: b */
    private final Observable<T> f18113b;

    public FlowableFromObservable(Observable<T> aslVar) {
        this.f18113b = aslVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f18113b.subscribe(new C4082a(dbxVar));
    }

    /* compiled from: FlowableFromObservable.java */
    /* renamed from: z1.bba$a */
    /* loaded from: classes3.dex */
    static final class C4082a<T> implements Observer<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f18114a;

        /* renamed from: b */
        Disposable f18115b;

        @Override // p110z1.dby
        public void request(long j) {
        }

        C4082a(Subscriber<? super T> dbxVar) {
            this.f18114a = dbxVar;
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.f18114a.onComplete();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f18114a.onError(th);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f18114a.onNext(t);
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f18115b = atrVar;
            this.f18114a.onSubscribe(this);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18115b.dispose();
        }
    }
}
