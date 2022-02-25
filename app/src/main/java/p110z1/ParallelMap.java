package p110z1;

/* renamed from: z1.boc */
/* loaded from: classes3.dex */
public final class ParallelMap<T, R> extends ParallelFlowable<R> {

    /* renamed from: a */
    final ParallelFlowable<T> f19634a;

    /* renamed from: b */
    final Function<? super T, ? extends R> f19635b;

    public ParallelMap(ParallelFlowable<T> bubVar, Function<? super T, ? extends R> aunVar) {
        this.f19634a = bubVar;
        this.f19635b = aunVar;
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public void mo9236a(Subscriber<? super R>[] dbxVarArr) {
        if (m9227b(dbxVarArr)) {
            int length = dbxVarArr.length;
            Subscriber<? super T>[] dbxVarArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                Subscriber<? super R> dbxVar = dbxVarArr[i];
                if (dbxVar instanceof ConditionalSubscriber) {
                    dbxVarArr2[i] = new C4636a((ConditionalSubscriber) dbxVar, this.f19635b);
                } else {
                    dbxVarArr2[i] = new C4637b(dbxVar, this.f19635b);
                }
            }
            this.f19634a.mo9236a(dbxVarArr2);
        }
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19634a.mo9267a();
    }

    /* compiled from: ParallelMap.java */
    /* renamed from: z1.boc$b */
    /* loaded from: classes3.dex */
    static final class C4637b<T, R> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super R> f19640a;

        /* renamed from: b */
        final Function<? super T, ? extends R> f19641b;

        /* renamed from: c */
        dby f19642c;

        /* renamed from: d */
        boolean f19643d;

        C4637b(Subscriber<? super R> dbxVar, Function<? super T, ? extends R> aunVar) {
            this.f19640a = dbxVar;
            this.f19641b = aunVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f19642c.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f19642c.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19642c, dbyVar)) {
                this.f19642c = dbyVar;
                this.f19640a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f19643d) {
                try {
                    this.f19640a.onNext(ObjectHelper.m9873a(this.f19641b.apply(t), "The mapper returned a null value"));
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19643d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19643d = true;
            this.f19640a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19643d) {
                this.f19643d = true;
                this.f19640a.onComplete();
            }
        }
    }

    /* compiled from: ParallelMap.java */
    /* renamed from: z1.boc$a */
    /* loaded from: classes3.dex */
    static final class C4636a<T, R> implements ConditionalSubscriber<T>, dby {

        /* renamed from: a */
        final ConditionalSubscriber<? super R> f19636a;

        /* renamed from: b */
        final Function<? super T, ? extends R> f19637b;

        /* renamed from: c */
        dby f19638c;

        /* renamed from: d */
        boolean f19639d;

        C4636a(ConditionalSubscriber<? super R> aviVar, Function<? super T, ? extends R> aunVar) {
            this.f19636a = aviVar;
            this.f19637b = aunVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f19638c.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f19638c.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19638c, dbyVar)) {
                this.f19638c = dbyVar;
                this.f19636a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f19639d) {
                try {
                    this.f19636a.onNext(ObjectHelper.m9873a(this.f19637b.apply(t), "The mapper returned a null value"));
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.f19639d) {
                return false;
            }
            try {
                return this.f19636a.tryOnNext(ObjectHelper.m9873a(this.f19637b.apply(t), "The mapper returned a null value"));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                cancel();
                onError(th);
                return false;
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19639d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19639d = true;
            this.f19636a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19639d) {
                this.f19639d = true;
                this.f19636a.onComplete();
            }
        }
    }
}
