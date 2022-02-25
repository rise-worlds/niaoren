package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bcu */
/* loaded from: classes3.dex */
public final class FlowableRetryBiPredicate<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final BiPredicate<? super Integer, ? super Throwable> f18296c;

    public FlowableRetryBiPredicate(Flowable<T> arvVar, BiPredicate<? super Integer, ? super Throwable> aujVar) {
        super(arvVar);
        this.f18296c = aujVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        SubscriptionArbiter bsjVar = new SubscriptionArbiter(false);
        dbxVar.onSubscribe(bsjVar);
        new C4176a(dbxVar, this.f18296c, bsjVar, this.f17812b).subscribeNext();
    }

    /* compiled from: FlowableRetryBiPredicate.java */
    /* renamed from: z1.bcu$a */
    /* loaded from: classes3.dex */
    static final class C4176a<T> extends AtomicInteger implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Subscriber<? super T> downstream;
        final BiPredicate<? super Integer, ? super Throwable> predicate;
        long produced;
        int retries;

        /* renamed from: sa */
        final SubscriptionArbiter f18297sa;
        final Publisher<? extends T> source;

        C4176a(Subscriber<? super T> dbxVar, BiPredicate<? super Integer, ? super Throwable> aujVar, SubscriptionArbiter bsjVar, Publisher<? extends T> dbwVar) {
            this.downstream = dbxVar;
            this.f18297sa = bsjVar;
            this.source = dbwVar;
            this.predicate = aujVar;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            this.f18297sa.setSubscription(dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            try {
                BiPredicate<? super Integer, ? super Throwable> aujVar = this.predicate;
                int i = this.retries + 1;
                this.retries = i;
                if (!aujVar.mo9871a(Integer.valueOf(i), th)) {
                    this.downstream.onError(th);
                } else {
                    subscribeNext();
                }
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.downstream.onComplete();
        }

        void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.f18297sa.isCancelled()) {
                    long j = this.produced;
                    if (j != 0) {
                        this.produced = 0L;
                        this.f18297sa.produced(j);
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
