package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bdz */
/* loaded from: classes3.dex */
public final class FlowableThrottleLatest<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18399c;

    /* renamed from: d */
    final TimeUnit f18400d;

    /* renamed from: e */
    final Scheduler f18401e;

    /* renamed from: f */
    final boolean f18402f;

    public FlowableThrottleLatest(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        super(arvVar);
        this.f18399c = j;
        this.f18400d = timeUnit;
        this.f18401e = astVar;
        this.f18402f = z;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new RunnableC4217a(dbxVar, this.f18399c, this.f18400d, this.f18401e.mo9034b(), this.f18402f));
    }

    /* compiled from: FlowableThrottleLatest.java */
    /* renamed from: z1.bdz$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4217a<T> extends AtomicInteger implements Runnable, FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -8296689127439125014L;
        volatile boolean cancelled;
        volatile boolean done;
        final Subscriber<? super T> downstream;
        final boolean emitLast;
        long emitted;
        Throwable error;
        final AtomicReference<T> latest = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        final long timeout;
        volatile boolean timerFired;
        boolean timerRunning;
        final TimeUnit unit;
        dby upstream;
        final Scheduler.AbstractC3881c worker;

        RunnableC4217a(Subscriber<? super T> dbxVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar, boolean z) {
            this.downstream = dbxVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
            this.emitLast = z;
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
            this.latest.set(t);
            drain();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
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
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
            this.worker.dispose();
            if (getAndIncrement() == 0) {
                this.latest.lazySet(null);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.timerFired = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                AtomicReference<T> atomicReference = this.latest;
                AtomicLong atomicLong = this.requested;
                Subscriber<? super T> dbxVar = this.downstream;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = this.done;
                    if (!z || this.error == null) {
                        boolean z2 = atomicReference.get() == null;
                        if (z) {
                            if (z2 || !this.emitLast) {
                                atomicReference.lazySet(null);
                                dbxVar.onComplete();
                            } else {
                                T andSet = atomicReference.getAndSet(null);
                                long j = this.emitted;
                                if (j != atomicLong.get()) {
                                    this.emitted = j + 1;
                                    dbxVar.onNext(andSet);
                                    dbxVar.onComplete();
                                } else {
                                    dbxVar.onError(new MissingBackpressureException("Could not emit final value due to lack of requests"));
                                }
                            }
                            this.worker.dispose();
                            return;
                        }
                        if (z2) {
                            if (this.timerFired) {
                                this.timerRunning = false;
                                this.timerFired = false;
                            }
                        } else if (!this.timerRunning || this.timerFired) {
                            T andSet2 = atomicReference.getAndSet(null);
                            long j2 = this.emitted;
                            if (j2 != atomicLong.get()) {
                                dbxVar.onNext(andSet2);
                                this.emitted = j2 + 1;
                                this.timerFired = false;
                                this.timerRunning = true;
                                this.worker.mo9030a(this, this.timeout, this.unit);
                            } else {
                                this.upstream.cancel();
                                dbxVar.onError(new MissingBackpressureException("Could not emit value due to lack of requests"));
                                this.worker.dispose();
                                return;
                            }
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        atomicReference.lazySet(null);
                        dbxVar.onError(this.error);
                        this.worker.dispose();
                        return;
                    }
                }
                atomicReference.lazySet(null);
            }
        }
    }
}
