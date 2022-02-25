package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bcv */
/* loaded from: classes3.dex */
public final class FlowableRetryPredicate<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final Predicate<? super Throwable> f18298c;

    /* renamed from: d */
    final long f18299d;

    public FlowableRetryPredicate(Flowable<T> arvVar, long j, Predicate<? super Throwable> auxVar) {
        super(arvVar);
        this.f18298c = auxVar;
        this.f18299d = j;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        SubscriptionArbiter bsjVar = new SubscriptionArbiter(false);
        dbxVar.onSubscribe(bsjVar);
        new C4177a(dbxVar, this.f18299d, this.f18298c, bsjVar, this.f17812b).subscribeNext();
    }

    /* compiled from: FlowableRetryPredicate.java */
    /* renamed from: z1.bcv$a */
    /* loaded from: classes3.dex */
    static final class C4177a<T> extends AtomicInteger implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Subscriber<? super T> downstream;
        final Predicate<? super Throwable> predicate;
        long produced;
        long remaining;

        /* renamed from: sa */
        final SubscriptionArbiter f18300sa;
        final Publisher<? extends T> source;

        C4177a(Subscriber<? super T> dbxVar, long j, Predicate<? super Throwable> auxVar, SubscriptionArbiter bsjVar, Publisher<? extends T> dbwVar) {
            this.downstream = dbxVar;
            this.f18300sa = bsjVar;
            this.source = dbwVar;
            this.predicate = auxVar;
            this.remaining = j;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            this.f18300sa.setSubscription(dbyVar);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            long j = this.remaining;
            if (j != cjm.f20759b) {
                this.remaining = j - 1;
            }
            if (j == 0) {
                this.downstream.onError(th);
                return;
            }
            try {
                if (!this.predicate.test(th)) {
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
                while (!this.f18300sa.isCancelled()) {
                    long j = this.produced;
                    if (j != 0) {
                        this.produced = 0L;
                        this.f18300sa.produced(j);
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
