package p110z1;

/* renamed from: z1.bdo */
/* loaded from: classes3.dex */
public final class FlowableSwitchIfEmpty<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Publisher<? extends T> f18367c;

    public FlowableSwitchIfEmpty(Flowable<T> arvVar, Publisher<? extends T> dbwVar) {
        super(arvVar);
        this.f18367c = dbwVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4205a aVar = new C4205a(dbxVar, this.f18367c);
        dbxVar.onSubscribe(aVar.f18370c);
        this.f17812b.m11187a((FlowableSubscriber) aVar);
    }

    /* compiled from: FlowableSwitchIfEmpty.java */
    /* renamed from: z1.bdo$a */
    /* loaded from: classes3.dex */
    static final class C4205a<T> implements FlowableSubscriber<T> {

        /* renamed from: a */
        final Subscriber<? super T> f18368a;

        /* renamed from: b */
        final Publisher<? extends T> f18369b;

        /* renamed from: d */
        boolean f18371d = true;

        /* renamed from: c */
        final SubscriptionArbiter f18370c = new SubscriptionArbiter(false);

        C4205a(Subscriber<? super T> dbxVar, Publisher<? extends T> dbwVar) {
            this.f18368a = dbxVar;
            this.f18369b = dbwVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            this.f18370c.setSubscription(dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (this.f18371d) {
                this.f18371d = false;
            }
            this.f18368a.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18368a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.f18371d) {
                this.f18371d = false;
                this.f18369b.subscribe(this);
                return;
            }
            this.f18368a.onComplete();
        }
    }
}
