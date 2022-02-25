package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bof */
/* loaded from: classes3.dex */
public final class ParallelReduce<T, R> extends ParallelFlowable<R> {

    /* renamed from: a */
    final ParallelFlowable<? extends T> f19671a;

    /* renamed from: b */
    final Callable<R> f19672b;

    /* renamed from: c */
    final BiFunction<R, ? super T, R> f19673c;

    public ParallelReduce(ParallelFlowable<? extends T> bubVar, Callable<R> callable, BiFunction<R, ? super T, R> auiVar) {
        this.f19671a = bubVar;
        this.f19672b = callable;
        this.f19673c = auiVar;
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public void mo9236a(Subscriber<? super R>[] dbxVarArr) {
        if (m9227b(dbxVarArr)) {
            int length = dbxVarArr.length;
            Subscriber<? super Object>[] dbxVarArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                try {
                    dbxVarArr2[i] = new C4642a(dbxVarArr[i], ObjectHelper.m9873a(this.f19672b.call(), "The initialSupplier returned a null value"), this.f19673c);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    m9553a(dbxVarArr, th);
                    return;
                }
            }
            this.f19671a.mo9236a(dbxVarArr2);
        }
    }

    /* renamed from: a */
    void m9553a(Subscriber<?>[] dbxVarArr, Throwable th) {
        for (Subscriber<?> dbxVar : dbxVarArr) {
            EmptySubscription.error(th, dbxVar);
        }
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19671a.mo9267a();
    }

    /* compiled from: ParallelReduce.java */
    /* renamed from: z1.bof$a */
    /* loaded from: classes3.dex */
    static final class C4642a<T, R> extends DeferredScalarSubscriber<T, R> {
        private static final long serialVersionUID = 8200530050639449080L;
        R accumulator;
        boolean done;
        final BiFunction<R, ? super T, R> reducer;

        C4642a(Subscriber<? super R> dbxVar, R r, BiFunction<R, ? super T, R> auiVar) {
            super(dbxVar);
            this.accumulator = r;
            this.reducer = auiVar;
        }

        @Override // p110z1.DeferredScalarSubscriber, p110z1.FlowableSubscriber, p110z1.Subscriber
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
                    this.accumulator = (R) ObjectHelper.m9873a(this.reducer.apply(this.accumulator, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.DeferredScalarSubscriber, p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.accumulator = null;
            this.downstream.onError(th);
        }

        @Override // p110z1.DeferredScalarSubscriber, p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                R r = this.accumulator;
                this.accumulator = null;
                complete(r);
            }
        }

        @Override // p110z1.DeferredScalarSubscriber, p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
