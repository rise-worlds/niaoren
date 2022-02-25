package p110z1;

/* renamed from: z1.axr */
/* loaded from: classes3.dex */
public final class CompletableFromPublisher<T> extends Completable {

    /* renamed from: a */
    final Publisher<T> f17726a;

    public CompletableFromPublisher(Publisher<T> dbwVar) {
        this.f17726a = dbwVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17726a.subscribe(new C3955a(arpVar));
    }

    /* compiled from: CompletableFromPublisher.java */
    /* renamed from: z1.axr$a */
    /* loaded from: classes3.dex */
    static final class C3955a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final CompletableObserver f17727a;

        /* renamed from: b */
        dby f17728b;

        @Override // p110z1.Subscriber
        public void onNext(T t) {
        }

        C3955a(CompletableObserver arpVar) {
            this.f17727a = arpVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17728b, dbyVar)) {
                this.f17728b = dbyVar;
                this.f17727a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f17727a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f17727a.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17728b.cancel();
            this.f17728b = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17728b == SubscriptionHelper.CANCELLED;
        }
    }
}
