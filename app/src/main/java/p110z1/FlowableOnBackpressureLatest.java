package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bce */
/* loaded from: classes3.dex */
public final class FlowableOnBackpressureLatest<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableOnBackpressureLatest(Flowable<T> arvVar) {
        super(arvVar);
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        this.f17812b.m11187a((FlowableSubscriber) new C4136a(dbxVar));
    }

    /* compiled from: FlowableOnBackpressureLatest.java */
    /* renamed from: z1.bce$a */
    /* loaded from: classes3.dex */
    static final class C4136a<T> extends AtomicInteger implements FlowableSubscriber<T>, dby {
        private static final long serialVersionUID = 163080509307634843L;
        volatile boolean cancelled;
        volatile boolean done;
        final Subscriber<? super T> downstream;
        Throwable error;
        dby upstream;
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<T> current = new AtomicReference<>();

        C4136a(Subscriber<? super T> dbxVar) {
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
            this.current.lazySet(t);
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
                drain();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.current.lazySet(null);
                }
            }
        }

        void drain() {
            boolean z;
            if (getAndIncrement() == 0) {
                Subscriber<? super T> dbxVar = this.downstream;
                AtomicLong atomicLong = this.requested;
                AtomicReference<T> atomicReference = this.current;
                int i = 1;
                do {
                    long j = 0;
                    while (true) {
                        z = false;
                        if (j == atomicLong.get()) {
                            break;
                        }
                        boolean z2 = this.done;
                        Object obj = (T) atomicReference.getAndSet(null);
                        boolean z3 = obj == null;
                        if (!checkTerminated(z2, z3, dbxVar, atomicReference)) {
                            if (z3) {
                                break;
                            }
                            dbxVar.onNext(obj);
                            j++;
                        } else {
                            return;
                        }
                    }
                    if (j == atomicLong.get()) {
                        boolean z4 = this.done;
                        if (atomicReference.get() == null) {
                            z = true;
                        }
                        if (checkTerminated(z4, z, dbxVar, atomicReference)) {
                            return;
                        }
                    }
                    if (j != 0) {
                        BackpressureHelper.m9446c(atomicLong, j);
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> dbxVar, AtomicReference<T> atomicReference) {
            if (this.cancelled) {
                atomicReference.lazySet(null);
                return true;
            } else if (!z) {
                return false;
            } else {
                Throwable th = this.error;
                if (th != null) {
                    atomicReference.lazySet(null);
                    dbxVar.onError(th);
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    dbxVar.onComplete();
                    return true;
                }
            }
        }
    }
}
