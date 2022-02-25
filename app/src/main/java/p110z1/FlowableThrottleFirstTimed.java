package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import p110z1.Scheduler;

/* renamed from: z1.bdy */
/* loaded from: classes3.dex */
public final class FlowableThrottleFirstTimed<T> extends AbstractFlowableWithUpstream<T, T> {

    /* renamed from: c */
    final long f18396c;

    /* renamed from: d */
    final TimeUnit f18397d;

    /* renamed from: e */
    final Scheduler f18398e;

    public FlowableThrottleFirstTimed(Flowable<T> arvVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        super(arvVar);
        this.f18396c = j;
        this.f18397d = timeUnit;
        this.f18398e = astVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new RunnableC4216a(new SerializedSubscriber(dbxVar), this.f18396c, this.f18397d, this.f18398e.mo9034b()));
    }

    /* compiled from: FlowableThrottleFirstTimed.java */
    /* renamed from: z1.bdy$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4216a<T> extends AtomicLong implements Runnable, FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = -9102637559663639004L;
        boolean done;
        final Subscriber<? super T> downstream;
        volatile boolean gate;
        final long timeout;
        final SequentialDisposable timer = new SequentialDisposable();
        final TimeUnit unit;
        dby upstream;
        final Scheduler.AbstractC3881c worker;

        RunnableC4216a(Subscriber<? super T> dbxVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar) {
            this.downstream = dbxVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
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
            if (!this.done && !this.gate) {
                this.gate = true;
                if (get() != 0) {
                    this.downstream.onNext(t);
                    BackpressureHelper.m9446c(this, 1L);
                    Disposable atrVar = this.timer.get();
                    if (atrVar != null) {
                        atrVar.dispose();
                    }
                    this.timer.replace(this.worker.mo9030a(this, this.timeout, this.unit));
                    return;
                }
                this.done = true;
                cancel();
                this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.gate = false;
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
            this.worker.dispose();
        }

        @Override // p110z1.Subscriber
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
                this.worker.dispose();
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
            this.worker.dispose();
        }
    }
}
