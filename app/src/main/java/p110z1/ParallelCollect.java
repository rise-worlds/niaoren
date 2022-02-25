package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bnt */
/* loaded from: classes3.dex */
public final class ParallelCollect<T, C> extends ParallelFlowable<C> {

    /* renamed from: a */
    final ParallelFlowable<? extends T> f19580a;

    /* renamed from: b */
    final Callable<? extends C> f19581b;

    /* renamed from: c */
    final BiConsumer<? super C, ? super T> f19582c;

    public ParallelCollect(ParallelFlowable<? extends T> bubVar, Callable<? extends C> callable, BiConsumer<? super C, ? super T> auhVar) {
        this.f19580a = bubVar;
        this.f19581b = callable;
        this.f19582c = auhVar;
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public void mo9236a(Subscriber<? super C>[] dbxVarArr) {
        if (m9227b(dbxVarArr)) {
            int length = dbxVarArr.length;
            Subscriber<? super Object>[] dbxVarArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                try {
                    dbxVarArr2[i] = new C4618a(dbxVarArr[i], ObjectHelper.m9873a(this.f19581b.call(), "The initialSupplier returned a null value"), this.f19582c);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    m9555a(dbxVarArr, th);
                    return;
                }
            }
            this.f19580a.mo9236a(dbxVarArr2);
        }
    }

    /* renamed from: a */
    void m9555a(Subscriber<?>[] dbxVarArr, Throwable th) {
        for (Subscriber<?> dbxVar : dbxVarArr) {
            EmptySubscription.error(th, dbxVar);
        }
    }

    @Override // p110z1.ParallelFlowable
    /* renamed from: a */
    public int mo9267a() {
        return this.f19580a.mo9267a();
    }

    /* compiled from: ParallelCollect.java */
    /* renamed from: z1.bnt$a */
    /* loaded from: classes3.dex */
    static final class C4618a<T, C> extends DeferredScalarSubscriber<T, C> {
        private static final long serialVersionUID = -4767392946044436228L;
        C collection;
        final BiConsumer<? super C, ? super T> collector;
        boolean done;

        C4618a(Subscriber<? super C> dbxVar, C c, BiConsumer<? super C, ? super T> auhVar) {
            super(dbxVar);
            this.collection = c;
            this.collector = auhVar;
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
                    this.collector.mo9895a((C) this.collection, t);
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
            this.collection = null;
            this.downstream.onError(th);
        }

        @Override // p110z1.DeferredScalarSubscriber, p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                C c = this.collection;
                this.collection = null;
                complete(c);
            }
        }

        @Override // p110z1.DeferredScalarSubscriber, p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
