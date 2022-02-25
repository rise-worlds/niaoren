package p110z1;

/* renamed from: z1.bdx */
/* loaded from: classes3.dex */
public final class FlowableTakeWhile<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Predicate<? super T> f18391c;

    public FlowableTakeWhile(Flowable<T> arvVar, Predicate<? super T> auxVar) {
        super(arvVar);
        this.f18391c = auxVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4215a(dbxVar, this.f18391c));
    }

    /* compiled from: FlowableTakeWhile.java */
    /* renamed from: z1.bdx$a */
    /* loaded from: classes3.dex */
    static final class C4215a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f18392a;

        /* renamed from: b */
        final Predicate<? super T> f18393b;

        /* renamed from: c */
        dby f18394c;

        /* renamed from: d */
        boolean f18395d;

        C4215a(Subscriber<? super T> dbxVar, Predicate<? super T> auxVar) {
            this.f18392a = dbxVar;
            this.f18393b = auxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18394c, dbyVar)) {
                this.f18394c = dbyVar;
                this.f18392a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f18395d) {
                try {
                    if (!this.f18393b.test(t)) {
                        this.f18395d = true;
                        this.f18394c.cancel();
                        this.f18392a.onComplete();
                        return;
                    }
                    this.f18392a.onNext(t);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f18394c.cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18395d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f18395d = true;
            this.f18392a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f18395d) {
                this.f18395d = true;
                this.f18392a.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18394c.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18394c.cancel();
        }
    }
}
