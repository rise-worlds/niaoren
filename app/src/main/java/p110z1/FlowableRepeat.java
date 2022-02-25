package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bcq */
/* loaded from: classes3.dex */
public final class FlowableRepeat<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18273c;

    public FlowableRepeat(Flowable<T> arvVar, long j) {
        super(arvVar);
        this.f18273c = j;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        SubscriptionArbiter bsjVar = new SubscriptionArbiter(false);
        dbxVar.onSubscribe(bsjVar);
        long j = this.f18273c;
        long j2 = cjm.f20759b;
        if (j != cjm.f20759b) {
            j2 = j - 1;
        }
        new C4156a(dbxVar, j2, bsjVar, this.f17812b).subscribeNext();
    }

    /* compiled from: FlowableRepeat.java */
    /* renamed from: z1.bcq$a */
    /* loaded from: classes3.dex */
    static final class C4156a<T> extends AtomicInteger implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Subscriber<? super T> downstream;
        long produced;
        long remaining;

        /* renamed from: sa */
        final SubscriptionArbiter f18274sa;
        final Publisher<? extends T> source;

        C4156a(Subscriber<? super T> dbxVar, long j, SubscriptionArbiter bsjVar, Publisher<? extends T> dbwVar) {
            this.downstream = dbxVar;
            this.f18274sa = bsjVar;
            this.source = dbwVar;
            this.remaining = j;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            this.f18274sa.setSubscription(dbyVar);
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
            long j = this.remaining;
            if (j != cjm.f20759b) {
                this.remaining = j - 1;
            }
            if (j != 0) {
                subscribeNext();
            } else {
                this.downstream.onComplete();
            }
        }

        void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.f18274sa.isCancelled()) {
                    long j = this.produced;
                    if (j != 0) {
                        this.produced = 0L;
                        this.f18274sa.produced(j);
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
