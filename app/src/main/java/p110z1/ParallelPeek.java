package p110z1;

/* renamed from: z1.boe */
/* loaded from: classes3.dex */
public final class ParallelPeek<T> extends ParallelFlowable<T> {

    /* renamed from: a */
    final ParallelFlowable<T> f19658a;

    /* renamed from: b */
    final Consumer<? super T> f19659b;

    /* renamed from: c */
    final Consumer<? super T> f19660c;

    /* renamed from: d */
    final Consumer<? super Throwable> f19661d;

    /* renamed from: e */
    final Action f19662e;

    /* renamed from: f */
    final Action f19663f;

    /* renamed from: g */
    final Consumer<? super dby> f19664g;

    /* renamed from: h */
    final LongConsumer f19665h;

    /* renamed from: i */
    final Action f19666i;

    public ParallelPeek(ParallelFlowable<T> bubVar, Consumer<? super T> aumVar, Consumer<? super T> aumVar2, Consumer<? super Throwable> aumVar3, Action augVar, Action augVar2, Consumer<? super dby> aumVar4, LongConsumer auwVar, Action augVar3) {
        this.f19658a = bubVar;
        this.f19659b = (Consumer) ObjectHelper.m9873a(aumVar, "onNext is null");
        this.f19660c = (Consumer) ObjectHelper.m9873a(aumVar2, "onAfterNext is null");
        this.f19661d = (Consumer) ObjectHelper.m9873a(aumVar3, "onError is null");
        this.f19662e = (Action) ObjectHelper.m9873a(augVar, "onComplete is null");
        this.f19663f = (Action) ObjectHelper.m9873a(augVar2, "onAfterTerminated is null");
        this.f19664g = (Consumer) ObjectHelper.m9873a(aumVar4, "onSubscribe is null");
        this.f19665h = (LongConsumer) ObjectHelper.m9873a(auwVar, "onRequest is null");
        this.f19666i = (Action) ObjectHelper.m9873a(augVar3, "onCancel is null");
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public void mo9236a(Subscriber<? super T>[] dbxVarArr) {
        if (m9227b(dbxVarArr)) {
            int length = dbxVarArr.length;
            Subscriber<? super T>[] dbxVarArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                dbxVarArr2[i] = new C4641a(dbxVarArr[i], this);
            }
            this.f19658a.mo9236a(dbxVarArr2);
        }
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19658a.mo9267a();
    }

    /* compiled from: ParallelPeek.java */
    /* renamed from: z1.boe$a */
    /* loaded from: classes3.dex */
    static final class C4641a<T> implements FlowableSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f19667a;

        /* renamed from: b */
        final ParallelPeek<T> f19668b;

        /* renamed from: c */
        dby f19669c;

        /* renamed from: d */
        boolean f19670d;

        C4641a(Subscriber<? super T> dbxVar, ParallelPeek<T> boeVar) {
            this.f19667a = dbxVar;
            this.f19668b = boeVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            try {
                this.f19668b.f19665h.mo9883a(j);
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
            this.f19669c.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            try {
                this.f19668b.f19666i.mo9442a();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                RxJavaPlugins.m9212a(th);
            }
            this.f19669c.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19669c, dbyVar)) {
                this.f19669c = dbyVar;
                try {
                    this.f19668b.f19664g.accept(dbyVar);
                    this.f19667a.onSubscribe(this);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    dbyVar.cancel();
                    this.f19667a.onSubscribe(EmptySubscription.INSTANCE);
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.f19670d) {
                try {
                    this.f19668b.f19659b.accept(t);
                    this.f19667a.onNext(t);
                    try {
                        this.f19668b.f19660c.accept(t);
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        onError(th);
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    onError(th2);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19670d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19670d = true;
            try {
                this.f19668b.f19661d.accept(th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                th = new CompositeException(th, th2);
            }
            this.f19667a.onError(th);
            try {
                this.f19668b.f19663f.mo9442a();
            } catch (Throwable th3) {
                Exceptions.m9983b(th3);
                RxJavaPlugins.m9212a(th3);
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19670d) {
                this.f19670d = true;
                try {
                    this.f19668b.f19662e.mo9442a();
                    this.f19667a.onComplete();
                    try {
                        this.f19668b.f19663f.mo9442a();
                    } catch (Throwable th) {
                        Exceptions.m9983b(th);
                        RxJavaPlugins.m9212a(th);
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    this.f19667a.onError(th2);
                }
            }
        }
    }
}
