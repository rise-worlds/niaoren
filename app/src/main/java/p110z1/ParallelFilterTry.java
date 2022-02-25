package p110z1;

/* renamed from: z1.bnx */
/* loaded from: classes3.dex */
public final class ParallelFilterTry<T> extends ParallelFlowable<T> {

    /* renamed from: a */
    final ParallelFlowable<T> f19608a;

    /* renamed from: b */
    final Predicate<? super T> f19609b;

    /* renamed from: c */
    final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> f19610c;

    public ParallelFilterTry(ParallelFlowable<T> bubVar, Predicate<? super T> auxVar, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
        this.f19608a = bubVar;
        this.f19609b = auxVar;
        this.f19610c = auiVar;
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
                    dbxVarArr2[i] = new C4627b((ConditionalSubscriber) dbxVar, this.f19609b, this.f19610c);
                } else {
                    dbxVarArr2[i] = new C4628c(dbxVar, this.f19609b, this.f19610c);
                }
            }
            this.f19608a.mo9236a(dbxVarArr2);
        }
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19608a.mo9267a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelFilterTry.java */
    /* renamed from: z1.bnx$a */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC4626a<T> implements ConditionalSubscriber<T>, dby {

        /* renamed from: a */
        final Predicate<? super T> f19612a;

        /* renamed from: b */
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> f19613b;

        /* renamed from: c */
        dby f19614c;

        /* renamed from: d */
        boolean f19615d;

        AbstractC4626a(Predicate<? super T> auxVar, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
            this.f19612a = auxVar;
            this.f19613b = auiVar;
        }

        @Override // p110z1.dby
        public final void request(long j) {
            this.f19614c.request(j);
        }

        @Override // p110z1.dby
        public final void cancel() {
            this.f19614c.cancel();
        }

        @Override // p110z1.Subscriber
        public final void onNext(T t) {
            if (!tryOnNext(t) && !this.f19615d) {
                this.f19614c.request(1L);
            }
        }
    }

    /* compiled from: ParallelFilterTry.java */
    /* renamed from: z1.bnx$c */
    /* loaded from: classes3.dex */
    static final class C4628c<T> extends AbstractC4626a<T> {

        /* renamed from: e */
        final Subscriber<? super T> f19617e;

        C4628c(Subscriber<? super T> dbxVar, Predicate<? super T> auxVar, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
            super(auxVar, auiVar);
            this.f19617e = dbxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19614c, dbyVar)) {
                this.f19614c = dbyVar;
                this.f19617e.onSubscribe(this);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.f19615d) {
                return false;
            }
            long j = 0;
            while (true) {
                try {
                    if (!this.f19612a.test(t)) {
                        return false;
                    }
                    this.f19617e.onNext(t);
                    return true;
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    try {
                        j++;
                        switch ((ParallelFailureHandling) ObjectHelper.m9873a(this.f19613b.apply(Long.valueOf(j), th), "The errorHandler returned a null item")) {
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
            if (this.f19615d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19615d = true;
            this.f19617e.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19615d) {
                this.f19615d = true;
                this.f19617e.onComplete();
            }
        }
    }

    /* compiled from: ParallelFilterTry.java */
    /* renamed from: z1.bnx$b */
    /* loaded from: classes3.dex */
    static final class C4627b<T> extends AbstractC4626a<T> {

        /* renamed from: e */
        final ConditionalSubscriber<? super T> f19616e;

        C4627b(ConditionalSubscriber<? super T> aviVar, Predicate<? super T> auxVar, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> auiVar) {
            super(auxVar, auiVar);
            this.f19616e = aviVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19614c, dbyVar)) {
                this.f19614c = dbyVar;
                this.f19616e.onSubscribe(this);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (this.f19615d) {
                return false;
            }
            long j = 0;
            while (true) {
                try {
                    return this.f19612a.test(t) && this.f19616e.tryOnNext(t);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    try {
                        j++;
                        switch ((ParallelFailureHandling) ObjectHelper.m9873a(this.f19613b.apply(Long.valueOf(j), th), "The errorHandler returned a null item")) {
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
            if (this.f19615d) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19615d = true;
            this.f19616e.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19615d) {
                this.f19615d = true;
                this.f19616e.onComplete();
            }
        }
    }
}
