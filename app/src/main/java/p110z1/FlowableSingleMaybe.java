package p110z1;

/* renamed from: z1.bdg */
/* loaded from: classes3.dex */
public final class FlowableSingleMaybe<T> extends Maybe<T> implements FuseToFlowable<T> {

    /* renamed from: a */
    final Flowable<T> f18335a;

    public FlowableSingleMaybe(Flowable<T> arvVar) {
        this.f18335a = arvVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18335a.m11187a((FlowableSubscriber) new C4195a(asfVar));
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<T> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableSingle(this.f18335a, null, false));
    }

    /* compiled from: FlowableSingleMaybe.java */
    /* renamed from: z1.bdg$a */
    /* loaded from: classes3.dex */
    static final class C4195a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18336a;

        /* renamed from: b */
        dby f18337b;

        /* renamed from: c */
        boolean f18338c;

        /* renamed from: d */
        T f18339d;

        C4195a(MaybeObserver<? super T> asfVar) {
            this.f18336a = asfVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18337b, dbyVar)) {
                this.f18337b = dbyVar;
                this.f18336a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18338c) {
                if (this.f18339d != null) {
                    this.f18338c = true;
                    this.f18337b.cancel();
                    this.f18337b = SubscriptionHelper.CANCELLED;
                    this.f18336a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.f18339d = t;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18338c) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18338c = true;
            this.f18337b = SubscriptionHelper.CANCELLED;
            this.f18336a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18338c) {
                this.f18338c = true;
                this.f18337b = SubscriptionHelper.CANCELLED;
                T t = this.f18339d;
                this.f18339d = null;
                if (t == null) {
                    this.f18336a.onComplete();
                } else {
                    this.f18336a.onSuccess(t);
                }
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18337b.cancel();
            this.f18337b = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18337b == SubscriptionHelper.CANCELLED;
        }
    }
}
