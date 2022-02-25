package p110z1;

/* renamed from: z1.bak */
/* loaded from: classes3.dex */
public final class FlowableElementAtMaybe<T> extends Maybe<T> implements FuseToFlowable<T> {

    /* renamed from: a */
    final Flowable<T> f18060a;

    /* renamed from: b */
    final long f18061b;

    public FlowableElementAtMaybe(Flowable<T> arvVar, long j) {
        this.f18060a = arvVar;
        this.f18061b = j;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18060a.m11187a((FlowableSubscriber) new C4060a(asfVar, this.f18061b));
    }

    @Override // p110z1.FuseToFlowable
    /* renamed from: r_ */
    public Flowable<T> mo9727r_() {
        return RxJavaPlugins.m9207a(new FlowableElementAt(this.f18060a, this.f18061b, null, false));
    }

    /* compiled from: FlowableElementAtMaybe.java */
    /* renamed from: z1.bak$a */
    /* loaded from: classes3.dex */
    static final class C4060a<T> implements FlowableSubscriber<T>, Disposable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18062a;

        /* renamed from: b */
        final long f18063b;

        /* renamed from: c */
        dby f18064c;

        /* renamed from: d */
        long f18065d;

        /* renamed from: e */
        boolean f18066e;

        C4060a(MaybeObserver<? super T> asfVar, long j) {
            this.f18062a = asfVar;
            this.f18063b = j;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18064c, dbyVar)) {
                this.f18064c = dbyVar;
                this.f18062a.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18066e) {
                long j = this.f18065d;
                if (j == this.f18063b) {
                    this.f18066e = true;
                    this.f18064c.cancel();
                    this.f18064c = SubscriptionHelper.CANCELLED;
                    this.f18062a.onSuccess(t);
                    return;
                }
                this.f18065d = j + 1;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18066e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18066e = true;
            this.f18064c = SubscriptionHelper.CANCELLED;
            this.f18062a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18064c = SubscriptionHelper.CANCELLED;
            if (!this.f18066e) {
                this.f18066e = true;
                this.f18062a.onComplete();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18064c.cancel();
            this.f18064c = SubscriptionHelper.CANCELLED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18064c == SubscriptionHelper.CANCELLED;
        }
    }
}
