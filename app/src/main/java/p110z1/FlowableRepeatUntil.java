package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bcr */
/* loaded from: classes3.dex */
public final class FlowableRepeatUntil<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final BooleanSupplier f18275c;

    public FlowableRepeatUntil(Flowable<T> arvVar, BooleanSupplier aukVar) {
        super(arvVar);
        this.f18275c = aukVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        SubscriptionArbiter bsjVar = new SubscriptionArbiter(false);
        dbxVar.onSubscribe(bsjVar);
        new C4157a(dbxVar, this.f18275c, bsjVar, this.f17812b).subscribeNext();
    }

    /* compiled from: FlowableRepeatUntil.java */
    /* renamed from: z1.bcr$a */
    /* loaded from: classes3.dex */
    static final class C4157a<T> extends AtomicInteger implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Subscriber<? super T> downstream;
        long produced;

        /* renamed from: sa */
        final SubscriptionArbiter f18276sa;
        final Publisher<? extends T> source;
        final BooleanSupplier stop;

        C4157a(Subscriber<? super T> dbxVar, BooleanSupplier aukVar, SubscriptionArbiter bsjVar, Publisher<? extends T> dbwVar) {
            this.downstream = dbxVar;
            this.f18276sa = bsjVar;
            this.source = dbwVar;
            this.stop = aukVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            this.f18276sa.setSubscription(dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            try {
                if (this.stop.getAsBoolean()) {
                    this.downstream.onComplete();
                } else {
                    subscribeNext();
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.downstream.onError(th);
            }
        }

        void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.f18276sa.isCancelled()) {
                    long j = this.produced;
                    if (j != 0) {
                        this.produced = 0L;
                        this.f18276sa.produced(j);
                    }
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }
}
