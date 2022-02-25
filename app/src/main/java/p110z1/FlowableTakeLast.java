package p110z1;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bdr */
/* loaded from: classes3.dex */
public final class FlowableTakeLast<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final int f18376c;

    public FlowableTakeLast(Flowable<T> arvVar, int i) {
        super(arvVar);
        this.f18376c = i;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4209a(dbxVar, this.f18376c));
    }

    /* compiled from: FlowableTakeLast.java */
    /* renamed from: z1.bdr$a */
    /* loaded from: classes3.dex */
    static final class C4209a<T> extends ArrayDeque<T> implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 7240042530241604978L;
        volatile boolean cancelled;
        final int count;
        volatile boolean done;
        final Subscriber<? super T> downstream;
        dby upstream;
        final AtomicLong requested = new AtomicLong();
        final AtomicInteger wip = new AtomicInteger();

        C4209a(Subscriber<? super T> dbxVar, int i) {
            this.downstream = dbxVar;
            this.count = i;
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
            if (this.count == size()) {
                poll();
            }
            offer(t);
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                drain();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
        }

        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                Subscriber<? super T> dbxVar = this.downstream;
                long j = this.requested.get();
                while (!this.cancelled) {
                    if (this.done) {
                        long j2 = 0;
                        while (j2 != j) {
                            if (!this.cancelled) {
                                Object obj = (T) poll();
                                if (obj == null) {
                                    dbxVar.onComplete();
                                    return;
                                } else {
                                    dbxVar.onNext(obj);
                                    j2++;
                                }
                            } else {
                                return;
                            }
                        }
                        if (!(j2 == 0 || j == cjm.f20759b)) {
                            j = this.requested.addAndGet(-j2);
                        }
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
}
