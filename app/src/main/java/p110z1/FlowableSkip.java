package p110z1;

/* renamed from: z1.bdi */
/* loaded from: classes3.dex */
public final class FlowableSkip<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18347c;

    public FlowableSkip(Flowable<T> arvVar, long j) {
        super(arvVar);
        this.f18347c = j;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4197a(dbxVar, this.f18347c));
    }

    /* compiled from: FlowableSkip.java */
    /* renamed from: z1.bdi$a */
    /* loaded from: classes3.dex */
    static final class C4197a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f18348a;

        /* renamed from: b */
        long f18349b;

        /* renamed from: c */
        dby f18350c;

        C4197a(Subscriber<? super T> dbxVar, long j) {
            this.f18348a = dbxVar;
            this.f18349b = j;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18350c, dbyVar)) {
                long j = this.f18349b;
                this.f18350c = dbyVar;
                this.f18348a.onSubscribe(this);
                dbyVar.request(j);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            long j = this.f18349b;
            if (j != 0) {
                this.f18349b = j - 1;
            } else {
                this.f18348a.onNext(t);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18348a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18348a.onComplete();
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18350c.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18350c.cancel();
        }
    }
}
