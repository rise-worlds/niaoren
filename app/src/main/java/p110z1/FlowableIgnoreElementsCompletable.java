package p110z1;

/* renamed from: z1.bbh */
/* loaded from: classes3.dex */
public final class FlowableIgnoreElementsCompletable<T> extends Completable implements FuseToFlowable<T> {

    /* renamed from: a */
    final Flowable<T> f18135a;

    public FlowableIgnoreElementsCompletable(Flowable<T> arvVar) {
        this.f18135a = arvVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f18135a.m11187a((FlowableSubscriber) new C4094a(arpVar));
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<T> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableIgnoreElements(this.f18135a));
    }

    /* compiled from: FlowableIgnoreElementsCompletable.java */
    /* renamed from: z1.bbh$a */
    /* loaded from: classes3.dex */
    static final class C4094a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final CompletableObserver f18136a;

        /* renamed from: b */
        dby f18137b;

        @Override // p110z1.Subscriber
        public void onNext(T t) {
        }

        C4094a(CompletableObserver arpVar) {
            this.f18136a = arpVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18137b, dbyVar)) {
                this.f18137b = dbyVar;
                this.f18136a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18137b = SubscriptionHelper.CANCELLED;
            this.f18136a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18137b = SubscriptionHelper.CANCELLED;
            this.f18136a.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18137b.cancel();
            this.f18137b = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18137b == SubscriptionHelper.CANCELLED;
        }
    }
}
