package p110z1;

/* renamed from: z1.bdm */
/* loaded from: classes3.dex */
public final class FlowableSkipWhile<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Predicate<? super T> f18358c;

    public FlowableSkipWhile(Flowable<T> arvVar, Predicate<? super T> auxVar) {
        super(arvVar);
        this.f18358c = auxVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4202a(dbxVar, this.f18358c));
    }

    /* compiled from: FlowableSkipWhile.java */
    /* renamed from: z1.bdm$a */
    /* loaded from: classes3.dex */
    static final class C4202a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f18359a;

        /* renamed from: b */
        final Predicate<? super T> f18360b;

        /* renamed from: c */
        dby f18361c;

        /* renamed from: d */
        boolean f18362d;

        C4202a(Subscriber<? super T> dbxVar, Predicate<? super T> auxVar) {
            this.f18359a = dbxVar;
            this.f18360b = auxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18361c, dbyVar)) {
                this.f18361c = dbyVar;
                this.f18359a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (this.f18362d) {
                this.f18359a.onNext(t);
                return;
            }
            try {
                if (this.f18360b.test(t)) {
                    this.f18361c.request(1L);
                    return;
                }
                this.f18362d = true;
                this.f18359a.onNext(t);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18361c.cancel();
                this.f18359a.onError(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18359a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18359a.onComplete();
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18361c.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18361c.cancel();
        }
    }
}
