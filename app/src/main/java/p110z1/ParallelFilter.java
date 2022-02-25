package p110z1;

/* renamed from: z1.bnw */
/* loaded from: classes3.dex */
public final class ParallelFilter<T> extends ParallelFlowable<T> {

    /* renamed from: a */
    final ParallelFlowable<T> f19601a;

    /* renamed from: b */
    final Predicate<? super T> f19602b;

    public ParallelFilter(ParallelFlowable<T> bubVar, Predicate<? super T> auxVar) {
        this.f19601a = bubVar;
        this.f19602b = auxVar;
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
                    dbxVarArr2[i] = new C4623b((ConditionalSubscriber) dbxVar, this.f19602b);
                } else {
                    dbxVarArr2[i] = new C4624c(dbxVar, this.f19602b);
                }
            }
            this.f19601a.mo9236a(dbxVarArr2);
        }
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19601a.mo9267a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ParallelFilter.java */
    /* renamed from: z1.bnw$a */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC4622a<T> implements ConditionalSubscriber<T>, dby {

        /* renamed from: a */
        final Predicate<? super T> f19603a;

        /* renamed from: b */
        dby f19604b;

        /* renamed from: c */
        boolean f19605c;

        AbstractC4622a(Predicate<? super T> auxVar) {
            this.f19603a = auxVar;
        }

        @Override // p110z1.dby
        public final void request(long j) {
            this.f19604b.request(j);
        }

        @Override // p110z1.dby
        public final void cancel() {
            this.f19604b.cancel();
        }

        @Override // p110z1.Subscriber
        public final void onNext(T t) {
            if (!tryOnNext(t) && !this.f19605c) {
                this.f19604b.request(1L);
            }
        }
    }

    /* compiled from: ParallelFilter.java */
    /* renamed from: z1.bnw$c */
    /* loaded from: classes3.dex */
    static final class C4624c<T> extends AbstractC4622a<T> {

        /* renamed from: d */
        final Subscriber<? super T> f19607d;

        C4624c(Subscriber<? super T> dbxVar, Predicate<? super T> auxVar) {
            super(auxVar);
            this.f19607d = dbxVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19604b, dbyVar)) {
                this.f19604b = dbyVar;
                this.f19607d.onSubscribe(this);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (!this.f19605c) {
                try {
                    if (this.f19603a.test(t)) {
                        this.f19607d.onNext(t);
                        return true;
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    cancel();
                    onError(th);
                    return false;
                }
            }
            return false;
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19605c) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19605c = true;
            this.f19607d.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19605c) {
                this.f19605c = true;
                this.f19607d.onComplete();
            }
        }
    }

    /* compiled from: ParallelFilter.java */
    /* renamed from: z1.bnw$b */
    /* loaded from: classes3.dex */
    static final class C4623b<T> extends AbstractC4622a<T> {

        /* renamed from: d */
        final ConditionalSubscriber<? super T> f19606d;

        C4623b(ConditionalSubscriber<? super T> aviVar, Predicate<? super T> auxVar) {
            super(auxVar);
            this.f19606d = aviVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.f19604b, dbyVar)) {
                this.f19604b = dbyVar;
                this.f19606d.onSubscribe(this);
            }
        }

        @Override // p110z1.ConditionalSubscriber
        public boolean tryOnNext(T t) {
            if (!this.f19605c) {
                try {
                    if (this.f19603a.test(t)) {
                        return this.f19606d.tryOnNext(t);
                    }
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    cancel();
                    onError(th);
                    return false;
                }
            }
            return false;
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.f19605c) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19605c = true;
            this.f19606d.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.f19605c) {
                this.f19605c = true;
                this.f19606d.onComplete();
            }
        }
    }
}
