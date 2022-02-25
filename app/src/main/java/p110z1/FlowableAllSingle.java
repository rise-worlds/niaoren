package p110z1;

/* renamed from: z1.ayw */
/* loaded from: classes3.dex */
public final class FlowableAllSingle<T> extends Single<Boolean> implements FuseToFlowable<Boolean> {

    /* renamed from: a */
    final Flowable<T> f17835a;

    /* renamed from: b */
    final Predicate<? super T> f17836b;

    public FlowableAllSingle(Flowable<T> arvVar, Predicate<? super T> auxVar) {
        this.f17835a = arvVar;
        this.f17836b = auxVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Boolean> asxVar) {
        this.f17835a.m11187a((FlowableSubscriber) new C3985a(asxVar, this.f17836b));
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<Boolean> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableAll(this.f17835a, this.f17836b));
    }

    /* compiled from: FlowableAllSingle.java */
    /* renamed from: z1.ayw$a */
    /* loaded from: classes3.dex */
    static final class C3985a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super Boolean> f17837a;

        /* renamed from: b */
        final Predicate<? super T> f17838b;

        /* renamed from: c */
        dby f17839c;

        /* renamed from: d */
        boolean f17840d;

        C3985a(SingleObserver<? super Boolean> asxVar, Predicate<? super T> auxVar) {
            this.f17837a = asxVar;
            this.f17838b = auxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17839c, dbyVar)) {
                this.f17839c = dbyVar;
                this.f17837a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f17840d) {
                try {
                    if (!this.f17838b.test(t)) {
                        this.f17840d = true;
                        this.f17839c.cancel();
                        this.f17839c = SubscriptionHelper.CANCELLED;
                        this.f17837a.onSuccess(false);
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17839c.cancel();
                    this.f17839c = SubscriptionHelper.CANCELLED;
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f17840d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f17840d = true;
            this.f17839c = SubscriptionHelper.CANCELLED;
            this.f17837a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f17840d) {
                this.f17840d = true;
                this.f17839c = SubscriptionHelper.CANCELLED;
                this.f17837a.onSuccess(true);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17839c.cancel();
            this.f17839c = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17839c == SubscriptionHelper.CANCELLED;
        }
    }
}
