package p110z1;

/* renamed from: z1.bcl */
/* loaded from: classes3.dex */
public final class FlowableReduce<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final BiFunction<T, T, T> f18249c;

    public FlowableReduce(Flowable<T> arvVar, BiFunction<T, T, T> auiVar) {
        super(arvVar);
        this.f18249c = auiVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4151a(dbxVar, this.f18249c));
    }

    /* compiled from: FlowableReduce.java */
    /* renamed from: z1.bcl$a */
    /* loaded from: classes3.dex */
    static final class C4151a<T> extends DeferredScalarSubscription<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -4663883003264602070L;
        final BiFunction<T, T, T> reducer;
        dby upstream;

        C4151a(Subscriber<? super T> dbxVar, BiFunction<T, T, T> auiVar) {
            super(dbxVar);
            this.reducer = auiVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                this.downstream.onSubscribe(this);
                dbyVar.request(cjm.f20759b);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (this.upstream != SubscriptionHelper.CANCELLED) {
                T t2 = this.value;
                if (t2 == null) {
                    this.value = t;
                    return;
                }
                try {
                    this.value = (T) ObjectHelper.m9873a((Object) this.reducer.apply(t2, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.upstream.cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.upstream == SubscriptionHelper.CANCELLED) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.upstream = SubscriptionHelper.CANCELLED;
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.upstream != SubscriptionHelper.CANCELLED) {
                this.upstream = SubscriptionHelper.CANCELLED;
                T t = this.value;
                if (t != null) {
                    complete(t);
                } else {
                    this.downstream.onComplete();
                }
            }
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
            this.upstream = SubscriptionHelper.CANCELLED;
        }
    }
}
