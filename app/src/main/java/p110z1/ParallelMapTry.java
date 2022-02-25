package p110z1;

/* renamed from: z1.bod */
/* loaded from: classes3.dex */
public final class ParallelMapTry<T, R> extends ParallelFlowable<R> {

    /* renamed from: a */
    final ParallelFlowable<T> f19644a;

    /* renamed from: b */
    final Function<? super T, ? extends R> f19645b;

    /* renamed from: c */
    final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> f19646c;

    public ParallelMapTry(ParallelFlowable<T> bubVar, Function<? super T, ? extends R> aunVar, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
        this.f19644a = bubVar;
        this.f19645b = aunVar;
        this.f19646c = auiVar;
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
                    dbxVarArr2[i] = new C4639a((ConditionalSubscriber) dbxVar, this.f19645b, this.f19646c);
                } else {
                    dbxVarArr2[i] = new C4640b(dbxVar, this.f19645b, this.f19646c);
                }
            }
            this.f19644a.mo9236a(dbxVarArr2);
        }
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19644a.mo9267a();
    }

    /* compiled from: ParallelMapTry.java */
    /* renamed from: z1.bod$b */
    /* loaded from: classes3.dex */
    static final class C4640b<T, R> implements ConditionalSubscriber<T>, dby {

        /* renamed from: a */
        final Subscriber<? super R> f19653a;

        /* renamed from: b */
        final Function<? super T, ? extends R> f19654b;

        /* renamed from: c */
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> f19655c;

        /* renamed from: d */
        dby f19656d;

        /* renamed from: e */
        boolean f19657e;

        C4640b(Subscriber<? super R> dbxVar, Function<? super T, ? extends R> aunVar, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
            this.f19653a = dbxVar;
            this.f19654b = aunVar;
            this.f19655c = auiVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f19656d.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f19656d.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19656d, dbyVar)) {
                this.f19656d = dbyVar;
                this.f19653a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!tryOnNext(t) && !this.f19657e) {
                this.f19656d.request(1L);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.f19657e) {
                return false;
            }
            long j = 0;
            while (true) {
                try {
                    this.f19653a.onNext(ObjectHelper.m9873a(this.f19654b.apply(t), "The mapper returned a null value"));
                    return true;
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    try {
                        j++;
                        switch ((ParallelFailureHandling) ObjectHelper.m9873a(this.f19655c.apply(Long.valueOf(j), th), "The errorHandler returned a null item")) {
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
            if (this.f19657e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19657e = true;
            this.f19653a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19657e) {
                this.f19657e = true;
                this.f19653a.onComplete();
            }
        }
    }

    /* compiled from: ParallelMapTry.java */
    /* renamed from: z1.bod$a */
    /* loaded from: classes3.dex */
    static final class C4639a<T, R> implements ConditionalSubscriber<T>, dby {

        /* renamed from: a */
        final ConditionalSubscriber<? super R> f19648a;

        /* renamed from: b */
        final Function<? super T, ? extends R> f19649b;

        /* renamed from: c */
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> f19650c;

        /* renamed from: d */
        dby f19651d;

        /* renamed from: e */
        boolean f19652e;

        C4639a(ConditionalSubscriber<? super R> aviVar, Function<? super T, ? extends R> aunVar, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
            this.f19648a = aviVar;
            this.f19649b = aunVar;
            this.f19650c = auiVar;
        }

        @Override // p110z1.dby
        public void request(long j) {
            this.f19651d.request(j);
        }

        @Override // p110z1.dby
        public void cancel() {
            this.f19651d.cancel();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19651d, dbyVar)) {
                this.f19651d = dbyVar;
                this.f19648a.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!tryOnNext(t) && !this.f19652e) {
                this.f19651d.request(1L);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.f19652e) {
                return false;
            }
            long j = 0;
            while (true) {
                try {
                    return this.f19648a.tryOnNext(ObjectHelper.m9873a(this.f19649b.apply(t), "The mapper returned a null value"));
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    try {
                        j++;
                        switch ((ParallelFailureHandling) ObjectHelper.m9873a(this.f19650c.apply(Long.valueOf(j), th), "The errorHandler returned a null item")) {
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
            if (this.f19652e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19652e = true;
            this.f19648a.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19652e) {
                this.f19652e = true;
                this.f19648a.onComplete();
            }
        }
    }
}
