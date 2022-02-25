package p110z1;

/* renamed from: z1.azu */
/* loaded from: classes3.dex */
public final class FlowableCountSingle<T> extends Single<Long> implements FuseToFlowable<Long> {

    /* renamed from: a */
    final Flowable<T> f17976a;

    public FlowableCountSingle(Flowable<T> arvVar) {
        this.f17976a = arvVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Long> asxVar) {
        this.f17976a.m11187a((FlowableSubscriber) new C4025a(asxVar));
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<Long> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableCount(this.f17976a));
    }

    /* compiled from: FlowableCountSingle.java */
    /* renamed from: z1.azu$a */
    /* loaded from: classes3.dex */
    static final class C4025a implements FlowableSubscriber<Object>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super Long> f17977a;

        /* renamed from: b */
        dby f17978b;

        /* renamed from: c */
        long f17979c;

        C4025a(SingleObserver<? super Long> asxVar) {
            this.f17977a = asxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17978b, dbyVar)) {
                this.f17978b = dbyVar;
                this.f17977a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(Object obj) {
            this.f17979c++;
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f17978b = SubscriptionHelper.CANCELLED;
            this.f17977a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f17978b = SubscriptionHelper.CANCELLED;
            this.f17977a.onSuccess(Long.valueOf(this.f17979c));
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17978b.cancel();
            this.f17978b = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17978b == SubscriptionHelper.CANCELLED;
        }
    }
}
