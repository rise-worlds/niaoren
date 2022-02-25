package p110z1;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bcd */
/* loaded from: classes3.dex */
public final class FlowableOnBackpressureError<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableOnBackpressureError(Flowable<T> arvVar) {
        super(arvVar);
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4135a(dbxVar));
    }

    /* compiled from: FlowableOnBackpressureError.java */
    /* renamed from: z1.bcd$a */
    /* loaded from: classes3.dex */
    static final class C4135a<T> extends AtomicLong implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -3176480756392482682L;
        boolean done;
        final Subscriber<? super T> downstream;
        dby upstream;

        C4135a(Subscriber<? super T> dbxVar) {
            this.downstream = dbxVar;
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
                if (get() != 0) {
                    this.downstream.onNext(t);
                    BackpressureHelper.m9446c(this, 1L);
                    return;
                }
                onError(new MissingBackpressureException("could not emit value due to lack of requests"));
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
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this, j);
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.upstream.cancel();
        }
    }
}
