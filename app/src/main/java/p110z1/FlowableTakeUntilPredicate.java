package p110z1;

/* renamed from: z1.bdw */
/* loaded from: classes3.dex */
public final class FlowableTakeUntilPredicate<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Predicate<? super T> f18386c;

    public FlowableTakeUntilPredicate(Flowable<T> arvVar, Predicate<? super T> auxVar) {
        super(arvVar);
        this.f18386c = auxVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4214a(dbxVar, this.f18386c));
    }

    /* compiled from: FlowableTakeUntilPredicate.java */
    /* renamed from: z1.bdw$a */
    /* loaded from: classes3.dex */
    static final class C4214a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f18387a;

        /* renamed from: b */
        final Predicate<? super T> f18388b;

        /* renamed from: c */
        dby f18389c;

        /* renamed from: d */
        boolean f18390d;

        C4214a(Subscriber<? super T> dbxVar, Predicate<? super T> auxVar) {
            this.f18387a = dbxVar;
            this.f18388b = auxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18389c, dbyVar)) {
                this.f18389c = dbyVar;
                this.f18387a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18390d) {
                this.f18387a.onNext(t);
                try {
                    if (this.f18388b.test(t)) {
                        this.f18390d = true;
                        this.f18389c.cancel();
                        this.f18387a.onComplete();
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f18389c.cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (!this.f18390d) {
                this.f18390d = true;
                this.f18387a.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18390d) {
                this.f18390d = true;
                this.f18387a.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18389c.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18389c.cancel();
        }
    }
}
