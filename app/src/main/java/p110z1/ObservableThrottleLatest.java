package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bna */
/* loaded from: classes3.dex */
public final class ObservableThrottleLatest<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19442b;

    /* renamed from: c */
    final TimeUnit f19443c;

    /* renamed from: d */
    final Scheduler f19444d;

    /* renamed from: e */
    final boolean f19445e;

    public ObservableThrottleLatest(Observable<T> aslVar, long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        super(aslVar);
        this.f19442b = j;
        this.f19443c = timeUnit;
        this.f19444d = astVar;
        this.f19445e = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new RunnableC4577a(assVar, this.f19442b, this.f19443c, this.f19444d.mo9034b(), this.f19445e));
    }

    /* compiled from: ObservableThrottleLatest.java */
    /* renamed from: z1.bna$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4577a<T> extends AtomicInteger implements Runnable, Observer<T>, Disposable {
        private static final long serialVersionUID = -8296689127439125014L;
        volatile boolean cancelled;
        volatile boolean done;
        final Observer<? super T> downstream;
        final boolean emitLast;
        Throwable error;
        final AtomicReference<T> latest = new AtomicReference<>();
        final long timeout;
        volatile boolean timerFired;
        boolean timerRunning;
        final TimeUnit unit;
        Disposable upstream;
        final Scheduler.AbstractC3881c worker;

        RunnableC4577a(Observer<? super T> assVar, long j, TimeUnit timeUnit, Scheduler.AbstractC3881c cVar, boolean z) {
            this.downstream = assVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
            this.emitLast = z;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.latest.set(t);
            drain();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.worker.dispose();
            if (getAndIncrement() == 0) {
                this.latest.lazySet(null);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.timerFired = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                AtomicReference<T> atomicReference = this.latest;
                Observer<? super T> assVar = this.downstream;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = this.done;
                    if (!z || this.error == null) {
                        boolean z2 = atomicReference.get() == null;
                        if (z) {
                            T andSet = atomicReference.getAndSet(null);
                            if (!z2 && this.emitLast) {
                                assVar.onNext(andSet);
                            }
                            assVar.onComplete();
                            this.worker.dispose();
                            return;
                        }
                        if (z2) {
                            if (this.timerFired) {
                                this.timerRunning = false;
                                this.timerFired = false;
                            }
                        } else if (!this.timerRunning || this.timerFired) {
                            assVar.onNext(atomicReference.getAndSet(null));
                            this.timerFired = false;
                            this.timerRunning = true;
                            this.worker.mo9030a(this, this.timeout, this.unit);
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        atomicReference.lazySet(null);
                        assVar.onError(this.error);
                        this.worker.dispose();
                        return;
                    }
                }
                atomicReference.lazySet(null);
            }
        }
    }
}
