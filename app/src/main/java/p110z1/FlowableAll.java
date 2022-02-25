package p110z1;

/* renamed from: z1.ayv */
/* loaded from: classes3.dex */
public final class FlowableAll<T> extends AbstractFlowableWithUpstream<T, Boolean> {

    /* renamed from: c */
    final Predicate<? super T> f17834c;

    public FlowableAll(Flowable<T> arvVar, Predicate<? super T> auxVar) {
        super(arvVar);
        this.f17834c = auxVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super Boolean> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C3984a(dbxVar, this.f17834c));
    }

    /* compiled from: FlowableAll.java */
    /* renamed from: z1.ayv$a */
    /* loaded from: classes3.dex */
    static final class C3984a<T> extends DeferredScalarSubscription<Boolean> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -3521127104134758517L;
        boolean done;
        final Predicate<? super T> predicate;
        dby upstream;

        C3984a(Subscriber<? super Boolean> dbxVar, Predicate<? super T> auxVar) {
            super(dbxVar);
            this.predicate = auxVar;
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
            if (!this.done) {
                try {
                    if (!this.predicate.test(t)) {
                        this.done = true;
                        this.upstream.cancel();
                        complete(false);
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.upstream.cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                complete(true);
            }
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
