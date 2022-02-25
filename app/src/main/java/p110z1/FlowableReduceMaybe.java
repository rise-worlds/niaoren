package p110z1;

/* renamed from: z1.bcm */
/* loaded from: classes3.dex */
public final class FlowableReduceMaybe<T> extends Maybe<T> implements FuseToFlowable<T>, HasUpstreamPublisher<T> {

    /* renamed from: a */
    final Flowable<T> f18250a;

    /* renamed from: b */
    final BiFunction<T, T, T> f18251b;

    public FlowableReduceMaybe(Flowable<T> arvVar, BiFunction<T, T, T> auiVar) {
        this.f18250a = arvVar;
        this.f18251b = auiVar;
    }

    @Override // p110z1.HasUpstreamPublisher
    /* renamed from: p_ */
    public Publisher<T> mo9741p_() {
        return this.f18250a;
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<T> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableReduce(this.f18250a, this.f18251b));
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18250a.m11187a((FlowableSubscriber) new C4152a(asfVar, this.f18251b));
    }

    /* compiled from: FlowableReduceMaybe.java */
    /* renamed from: z1.bcm$a */
    /* loaded from: classes3.dex */
    static final class C4152a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18252a;

        /* renamed from: b */
        final BiFunction<T, T, T> f18253b;

        /* renamed from: c */
        T f18254c;

        /* renamed from: d */
        dby f18255d;

        /* renamed from: e */
        boolean f18256e;

        C4152a(MaybeObserver<? super T> asfVar, BiFunction<T, T, T> auiVar) {
            this.f18252a = asfVar;
            this.f18253b = auiVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18255d.cancel();
            this.f18256e = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18256e;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18255d, dbyVar)) {
                this.f18255d = dbyVar;
                this.f18252a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18256e) {
                T t2 = this.f18254c;
                if (t2 == null) {
                    this.f18254c = t;
                    return;
                }
                try {
                    this.f18254c = (T) ObjectHelper.m9873a((Object) this.f18253b.apply(t2, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f18255d.cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18256e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18256e = true;
            this.f18252a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18256e) {
                this.f18256e = true;
                T t = this.f18254c;
                if (t != null) {
                    this.f18252a.onSuccess(t);
                } else {
                    this.f18252a.onComplete();
                }
            }
        }
    }
}
