package p110z1;

/* renamed from: z1.bac */
/* loaded from: classes3.dex */
public final class FlowableDetach<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableDetach(Flowable<T> arvVar) {
        super(arvVar);
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4048a(dbxVar));
    }

    /* compiled from: FlowableDetach.java */
    /* renamed from: z1.bac$a */
    /* loaded from: classes3.dex */
    static final class C4048a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        Subscriber<? super T> f18015a;

        /* renamed from: b */
        dby f18016b;

        C4048a(Subscriber<? super T> dbxVar) {
            this.f18015a = dbxVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18016b.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            dby dbyVar = this.f18016b;
            this.f18016b = EmptyComponent.INSTANCE;
            this.f18015a = EmptyComponent.asSubscriber();
            dbyVar.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18016b, dbyVar)) {
                this.f18016b = dbyVar;
                this.f18015a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f18015a.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            Subscriber<? super T> dbxVar = this.f18015a;
            this.f18016b = EmptyComponent.INSTANCE;
            this.f18015a = EmptyComponent.asSubscriber();
            dbxVar.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            Subscriber<? super T> dbxVar = this.f18015a;
            this.f18016b = EmptyComponent.INSTANCE;
            this.f18015a = EmptyComponent.asSubscriber();
            dbxVar.onComplete();
        }
    }
}
