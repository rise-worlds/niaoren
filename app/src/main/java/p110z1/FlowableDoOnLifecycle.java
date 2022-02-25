package p110z1;

/* renamed from: z1.bai */
/* loaded from: classes3.dex */
public final class FlowableDoOnLifecycle<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    private final Consumer<? super dby> f18049c;

    /* renamed from: d */
    private final LongConsumer f18050d;

    /* renamed from: e */
    private final Action f18051e;

    public FlowableDoOnLifecycle(Flowable<T> arvVar, Consumer<? super dby> aumVar, LongConsumer auwVar, Action augVar) {
        super(arvVar);
        this.f18049c = aumVar;
        this.f18050d = auwVar;
        this.f18051e = augVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4058a(dbxVar, this.f18049c, this.f18050d, this.f18051e));
    }

    /* compiled from: FlowableDoOnLifecycle.java */
    /* renamed from: z1.bai$a */
    /* loaded from: classes3.dex */
    static final class C4058a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f18052a;

        /* renamed from: b */
        final Consumer<? super dby> f18053b;

        /* renamed from: c */
        final LongConsumer f18054c;

        /* renamed from: d */
        final Action f18055d;

        /* renamed from: e */
        dby f18056e;

        C4058a(Subscriber<? super T> dbxVar, Consumer<? super dby> aumVar, LongConsumer auwVar, Action augVar) {
            this.f18052a = dbxVar;
            this.f18053b = aumVar;
            this.f18055d = augVar;
            this.f18054c = auwVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            try {
                this.f18053b.accept(dbyVar);
                if (SubscriptionHelper.validate(this.f18056e, dbyVar)) {
                    this.f18056e = dbyVar;
                    this.f18052a.onSubscribe(this);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                dbyVar.cancel();
                this.f18056e = SubscriptionHelper.CANCELLED;
                EmptySubscription.error(th, this.f18052a);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.f18052a.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f18056e != SubscriptionHelper.CANCELLED) {
                this.f18052a.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.f18056e != SubscriptionHelper.CANCELLED) {
                this.f18052a.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            try {
                this.f18054c.mo9883a(j);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
            this.f18056e.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            dby dbyVar = this.f18056e;
            if (dbyVar != SubscriptionHelper.CANCELLED) {
                this.f18056e = SubscriptionHelper.CANCELLED;
                try {
                    this.f18055d.mo9442a();
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    RxJavaPlugins.m9212a(th);
                }
                dbyVar.cancel();
            }
        }
    }
}
