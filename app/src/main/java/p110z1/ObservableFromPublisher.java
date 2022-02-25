package p110z1;

/* renamed from: z1.bkl */
/* loaded from: classes3.dex */
public final class ObservableFromPublisher<T> extends Observable<T> {

    /* renamed from: a */
    final Publisher<? extends T> f19142a;

    public ObservableFromPublisher(Publisher<? extends T> dbwVar) {
        this.f19142a = dbwVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f19142a.subscribe(new C4457a(assVar));
    }

    /* compiled from: ObservableFromPublisher.java */
    /* renamed from: z1.bkl$a */
    /* loaded from: classes3.dex */
    static final class C4457a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final Observer<? super T> f19143a;

        /* renamed from: b */
        dby f19144b;

        C4457a(Observer<? super T> assVar) {
            this.f19143a = assVar;
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f19143a.onComplete();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f19143a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f19143a.onNext(t);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19144b, dbyVar)) {
                this.f19144b = dbyVar;
                this.f19143a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19144b.cancel();
            this.f19144b = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19144b == SubscriptionHelper.CANCELLED;
        }
    }
}
