package p110z1;

/* renamed from: z1.ayz */
/* loaded from: classes3.dex */
public final class FlowableAnySingle<T> extends Single<Boolean> implements FuseToFlowable<Boolean> {

    /* renamed from: a */
    final Flowable<T> f17847a;

    /* renamed from: b */
    final Predicate<? super T> f17848b;

    public FlowableAnySingle(Flowable<T> arvVar, Predicate<? super T> auxVar) {
        this.f17847a = arvVar;
        this.f17848b = auxVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super Boolean> asxVar) {
        this.f17847a.m11187a((FlowableSubscriber) new C3989a(asxVar, this.f17848b));
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<Boolean> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableAny(this.f17847a, this.f17848b));
    }

    /* compiled from: FlowableAnySingle.java */
    /* renamed from: z1.ayz$a */
    /* loaded from: classes3.dex */
    static final class C3989a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final SingleObserver<? super Boolean> f17849a;

        /* renamed from: b */
        final Predicate<? super T> f17850b;

        /* renamed from: c */
        dby f17851c;

        /* renamed from: d */
        boolean f17852d;

        C3989a(SingleObserver<? super Boolean> asxVar, Predicate<? super T> auxVar) {
            this.f17849a = asxVar;
            this.f17850b = auxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f17851c, dbyVar)) {
                this.f17851c = dbyVar;
                this.f17849a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f17852d) {
                try {
                    if (this.f17850b.test(t)) {
                        this.f17852d = true;
                        this.f17851c.cancel();
                        this.f17851c = SubscriptionHelper.CANCELLED;
                        this.f17849a.onSuccess(true);
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f17851c.cancel();
                    this.f17851c = SubscriptionHelper.CANCELLED;
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f17852d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f17852d = true;
            this.f17851c = SubscriptionHelper.CANCELLED;
            this.f17849a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f17852d) {
                this.f17852d = true;
                this.f17851c = SubscriptionHelper.CANCELLED;
                this.f17849a.onSuccess(false);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17851c.cancel();
            this.f17851c = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17851c == SubscriptionHelper.CANCELLED;
        }
    }
}
