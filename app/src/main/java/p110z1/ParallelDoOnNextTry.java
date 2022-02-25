package p110z1;

/* renamed from: z1.bnv */
/* loaded from: classes3.dex */
public final class ParallelDoOnNextTry<T> extends ParallelFlowable<T> {

    /* renamed from: a */
    final ParallelFlowable<T> f19587a;

    /* renamed from: b */
    final Consumer<? super T> f19588b;

    /* renamed from: c */
    final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> f19589c;

    public ParallelDoOnNextTry(ParallelFlowable<T> bubVar, Consumer<? super T> aumVar, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
        this.f19587a = bubVar;
        this.f19588b = aumVar;
        this.f19589c = auiVar;
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public void mo9236a(Subscriber<? super T>[] dbxVarArr) {
        if (m9227b(dbxVarArr)) {
            int length = dbxVarArr.length;
            Subscriber<? super T>[] dbxVarArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                Subscriber<? super T> dbxVar = dbxVarArr[i];
                if (dbxVar instanceof ConditionalSubscriber) {
                    dbxVarArr2[i] = new C4620a((ConditionalSubscriber) dbxVar, this.f19588b, this.f19589c);
                } else {
                    dbxVarArr2[i] = new C4621b(dbxVar, this.f19588b, this.f19589c);
                }
            }
            this.f19587a.mo9236a(dbxVarArr2);
        }
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19587a.mo9267a();
    }

    /* compiled from: ParallelDoOnNextTry.java */
    /* renamed from: z1.bnv$b */
    /* loaded from: classes3.dex */
    static final class C4621b<T> implements ConditionalSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super T> f19596a;

        /* renamed from: b */
        final Consumer<? super T> f19597b;

        /* renamed from: c */
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> f19598c;

        /* renamed from: d */
        dby f19599d;

        /* renamed from: e */
        boolean f19600e;

        C4621b(Subscriber<? super T> dbxVar, Consumer<? super T> aumVar, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
            this.f19596a = dbxVar;
            this.f19597b = aumVar;
            this.f19598c = auiVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f19599d.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f19599d.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19599d, dbyVar)) {
                this.f19599d = dbyVar;
                this.f19596a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.f19599d.request(1L);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.f19600e) {
                return false;
            }
            long j = 0;
            while (true) {
                try {
                    this.f19597b.accept(t);
                    this.f19596a.onNext(t);
                    return true;
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    try {
                        j++;
                        switch ((ParallelFailureHandling) ObjectHelper.m9873a(this.f19598c.apply(Long.valueOf(j), th), "The errorHandler returned a null item")) {
                            case RETRY:
                                break;
                            case SKIP:
                                return false;
                            case STOP:
                                cancel();
                                onComplete();
                                return false;
                            default:
                                cancel();
                                onError(th);
                                return false;
                        }
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        cancel();
                        onError(new CompositeException(th, th2));
                        return false;
                    }
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19600e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19600e = true;
            this.f19596a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19600e) {
                this.f19600e = true;
                this.f19596a.onComplete();
            }
        }
    }

    /* compiled from: ParallelDoOnNextTry.java */
    /* renamed from: z1.bnv$a */
    /* loaded from: classes3.dex */
    static final class C4620a<T> implements ConditionalSubscriber<T>, dby {

        /* renamed from: a */
        final ConditionalSubscriber<? super T> f19591a;

        /* renamed from: b */
        final Consumer<? super T> f19592b;

        /* renamed from: c */
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> f19593c;

        /* renamed from: d */
        dby f19594d;

        /* renamed from: e */
        boolean f19595e;

        C4620a(ConditionalSubscriber<? super T> aviVar, Consumer<? super T> aumVar, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
            this.f19591a = aviVar;
            this.f19592b = aumVar;
            this.f19593c = auiVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f19594d.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f19594d.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19594d, dbyVar)) {
                this.f19594d = dbyVar;
                this.f19591a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!tryOnNext(t) && !this.f19595e) {
                this.f19594d.request(1L);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.f19595e) {
                return false;
            }
            long j = 0;
            while (true) {
                try {
                    this.f19592b.accept(t);
                    return this.f19591a.tryOnNext(t);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    try {
                        j++;
                        switch ((ParallelFailureHandling) ObjectHelper.m9873a(this.f19593c.apply(Long.valueOf(j), th), "The errorHandler returned a null item")) {
                            case RETRY:
                                break;
                            case SKIP:
                                return false;
                            case STOP:
                                cancel();
                                onComplete();
                                return false;
                            default:
                                cancel();
                                onError(th);
                                return false;
                        }
                    } catch (Throwable th2) {
                        Exceptions.m9983b(th2);
                        cancel();
                        onError(new CompositeException(th, th2));
                        return false;
                    }
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19595e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19595e = true;
            this.f19591a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19595e) {
                this.f19595e = true;
                this.f19591a.onComplete();
            }
        }
    }
}
