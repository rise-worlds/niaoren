package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.azi */
/* loaded from: classes3.dex */
public final class FlowableCollect<T, U> extends AbstractFlowableWithUpstream<T, U> {

    /* renamed from: c */
    final Callable<? extends U> f17934c;

    /* renamed from: d */
    final BiConsumer<? super U, ? super T> f17935d;

    public FlowableCollect(Flowable<T> arvVar, Callable<? extends U> callable, BiConsumer<? super U, ? super T> auhVar) {
        super(arvVar);
        this.f17934c = callable;
        this.f17935d = auhVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super U> dbxVar) {
        try {
            this.f17812b.m11187a((FlowableSubscriber) new C4007a(dbxVar, ObjectHelper.m9873a(this.f17934c.call(), "The initial value supplied is null"), this.f17935d));
        } catch (Throwable th) {
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* compiled from: FlowableCollect.java */
    /* renamed from: z1.azi$a */
    /* loaded from: classes3.dex */
    static final class C4007a<T, U> extends DeferredScalarSubscription<U> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -3589550218733891694L;
        final BiConsumer<? super U, ? super T> collector;
        boolean done;

        /* renamed from: u */
        final U f17936u;
        dby upstream;

        C4007a(Subscriber<? super U> dbxVar, U u, BiConsumer<? super U, ? super T> auhVar) {
            super(dbxVar);
            this.collector = auhVar;
            this.f17936u = u;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
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
                    this.collector.mo9895a((U) this.f17936u, t);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.upstream.cancel();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                complete(this.f17936u);
            }
        }

        @Override // p110z1.DeferredScalarSubscription, p110z1.dby
        public void cancel() {
            super.cancel();
            this.upstream.cancel();
        }
    }
}
