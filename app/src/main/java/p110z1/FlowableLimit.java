package p110z1;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bbq */
/* loaded from: classes3.dex */
public final class FlowableLimit<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18190c;

    public FlowableLimit(Flowable<T> arvVar, long j) {
        super(arvVar);
        this.f18190c = j;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4116a(dbxVar, this.f18190c));
    }

    /* compiled from: FlowableLimit.java */
    /* renamed from: z1.bbq$a */
    /* loaded from: classes3.dex */
    static final class C4116a<T> extends AtomicLong implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 2288246011222124525L;
        final Subscriber<? super T> downstream;
        long remaining;
        dby upstream;

        C4116a(Subscriber<? super T> dbxVar, long j) {
            this.downstream = dbxVar;
            this.remaining = j;
            lazySet(j);
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (!SubscriptionHelper.validate(this.upstream, dbyVar)) {
                return;
            }
            if (this.remaining == 0) {
                dbyVar.cancel();
                EmptySubscription.complete(this.downstream);
                return;
            }
            this.upstream = dbyVar;
            this.downstream.onSubscribe(this);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            long j = this.remaining;
            if (j > 0) {
                long j2 = j - 1;
                this.remaining = j2;
                this.downstream.onNext(t);
                if (j2 == 0) {
                    this.upstream.cancel();
                    this.downstream.onComplete();
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.remaining > 0) {
                this.remaining = 0L;
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (this.remaining > 0) {
                this.remaining = 0L;
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            long j2;
            long j3;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = get();
                    if (j2 != 0) {
                        j3 = j2 <= j ? j2 : j;
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j2 - j3));
                this.upstream.request(j3);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
        }
    }
}
