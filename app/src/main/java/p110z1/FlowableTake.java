package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.bdq */
/* loaded from: classes3.dex */
public final class FlowableTake<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18375c;

    public FlowableTake(Flowable<T> arvVar, long j) {
        super(arvVar);
        this.f18375c = j;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4208a(dbxVar, this.f18375c));
    }

    /* compiled from: FlowableTake.java */
    /* renamed from: z1.bdq$a */
    /* loaded from: classes3.dex */
    static final class C4208a<T> extends AtomicBoolean implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -5636543848937116287L;
        boolean done;
        final Subscriber<? super T> downstream;
        final long limit;
        long remaining;
        dby upstream;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C4208a(Subscriber<? super T> dbxVar, long j) {
            this.downstream = dbxVar;
            this.limit = j;
            this.remaining = j;
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            if (SubscriptionHelper.validate(this.upstream, dbyVar)) {
                this.upstream = dbyVar;
                if (this.limit == 0) {
                    dbyVar.cancel();
                    this.done = true;
                    EmptySubscription.complete(this.downstream);
                    return;
                }
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.done) {
                long j = this.remaining;
                this.remaining = j - 1;
                if (j > 0) {
                    boolean z = this.remaining == 0;
                    this.downstream.onNext(t);
                    if (z) {
                        this.upstream.cancel();
                        onComplete();
                    }
                }
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.upstream.cancel();
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                if (get() || !compareAndSet(false, true) || j < this.limit) {
                    this.upstream.request(j);
                } else {
                    this.upstream.request(cjm.f20759b);
                }
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
        }
    }
}
