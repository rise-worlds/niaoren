package p110z1;

/* renamed from: z1.bcf */
/* loaded from: classes3.dex */
public final class FlowableOnErrorNext<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Function<? super Throwable, ? extends Publisher<? extends T>> f18216c;

    /* renamed from: d */
    final boolean f18217d;

    public FlowableOnErrorNext(Flowable<T> arvVar, Function<? super Throwable, ? extends Publisher<? extends T>> aunVar, boolean z) {
        super(arvVar);
        this.f18216c = aunVar;
        this.f18217d = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4137a aVar = new C4137a(dbxVar, this.f18216c, this.f18217d);
        dbxVar.onSubscribe(aVar);
        this.f17812b.m11187a((FlowableSubscriber) aVar);
    }

    /* compiled from: FlowableOnErrorNext.java */
    /* renamed from: z1.bcf$a */
    /* loaded from: classes3.dex */
    static final class C4137a<T> extends SubscriptionArbiter implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 4063763155303814625L;
        final boolean allowFatal;
        boolean done;
        final Subscriber<? super T> downstream;
        final Function<? super Throwable, ? extends Publisher<? extends T>> nextSupplier;
        boolean once;
        long produced;

        C4137a(Subscriber<? super T> dbxVar, Function<? super Throwable, ? extends Publisher<? extends T>> aunVar, boolean z) {
            super(false);
            this.downstream = dbxVar;
            this.nextSupplier = aunVar;
            this.allowFatal = z;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            setSubscription(dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                if (!this.once) {
                    this.produced++;
                }
                this.downstream.onNext(t);
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (!this.once) {
                this.once = true;
                if (!this.allowFatal || (th instanceof Exception)) {
                    try {
                        Publisher dbwVar = (Publisher) ObjectHelper.m9873a(this.nextSupplier.apply(th), "The nextSupplier returned a null Publisher");
                        long j = this.produced;
                        if (j != 0) {
                            produced(j);
                        }
                        dbwVar.subscribe(this);
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        this.downstream.onError(new CompositeException(th, th2));
                    }
                } else {
                    this.downstream.onError(th);
                }
            } else if (this.done) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.once = true;
                this.downstream.onComplete();
            }
        }
    }
}
