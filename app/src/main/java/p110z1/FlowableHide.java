package p110z1;

/* renamed from: z1.bbf */
/* loaded from: classes3.dex */
public final class FlowableHide<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableHide(Flowable<T> arvVar) {
        super(arvVar);
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4092a(dbxVar));
    }

    /* compiled from: FlowableHide.java */
    /* renamed from: z1.bbf$a */
    /* loaded from: classes3.dex */
    static final class C4092a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f18131a;

        /* renamed from: b */
        dby f18132b;

        C4092a(Subscriber<? super T> dbxVar) {
            this.f18131a = dbxVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f18132b.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f18132b.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f18132b, dbyVar)) {
                this.f18132b = dbyVar;
                this.f18131a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f18131a.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.f18131a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.f18131a.onComplete();
        }
    }
}
