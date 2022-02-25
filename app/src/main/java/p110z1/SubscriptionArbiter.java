package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bsj */
/* loaded from: classes3.dex */
public class SubscriptionArbiter extends AtomicInteger implements dby {
    private static final long serialVersionUID = -2189523197179400958L;
    dby actual;
    final boolean cancelOnReplace;
    volatile boolean cancelled;
    long requested;
    protected boolean unbounded;
    final AtomicReference<dby> missedSubscription = new AtomicReference<>();
    final AtomicLong missedRequested = new AtomicLong();
    final AtomicLong missedProduced = new AtomicLong();

    public SubscriptionArbiter(boolean z) {
        this.cancelOnReplace = z;
    }

    public final void setSubscription(dby dbyVar) {
        if (this.cancelled) {
            dbyVar.cancel();
            return;
        }
        ObjectHelper.m9873a(dbyVar, "s is null");
        if (get() != 0 || !compareAndSet(0, 1)) {
            dby andSet = this.missedSubscription.getAndSet(dbyVar);
            if (andSet != null && this.cancelOnReplace) {
                andSet.cancel();
            }
            drain();
            return;
        }
        dby dbyVar2 = this.actual;
        if (dbyVar2 != null && this.cancelOnReplace) {
            dbyVar2.cancel();
        }
        this.actual = dbyVar;
        long j = this.requested;
        if (decrementAndGet() != 0) {
            drainLoop();
        }
        if (j != 0) {
            dbyVar.request(j);
        }
    }

    @Override // p110z1.dby
    public final void request(long j) {
        if (SubscriptionHelper.validate(j) && !this.unbounded) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                BackpressureHelper.m9449a(this.missedRequested, j);
                drain();
                return;
            }
            long j2 = this.requested;
            if (j2 != cjm.f20759b) {
                long a = BackpressureHelper.m9450a(j2, j);
                this.requested = a;
                if (a == cjm.f20759b) {
                    this.unbounded = true;
                }
            }
            dby dbyVar = this.actual;
            if (decrementAndGet() != 0) {
                drainLoop();
            }
            if (dbyVar != null) {
                dbyVar.request(j);
            }
        }
    }

    public final void produced(long j) {
        if (!this.unbounded) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                BackpressureHelper.m9449a(this.missedProduced, j);
                drain();
                return;
            }
            long j2 = this.requested;
            if (j2 != cjm.f20759b) {
                long j3 = j2 - j;
                long j4 = 0;
                if (j3 < 0) {
                    SubscriptionHelper.reportMoreProduced(j3);
                } else {
                    j4 = j3;
                }
                this.requested = j4;
            }
            if (decrementAndGet() != 0) {
                drainLoop();
            }
        }
    }

    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            drain();
        }
    }

    final void drain() {
        if (getAndIncrement() == 0) {
            drainLoop();
        }
    }

    final void drainLoop() {
        dby dbyVar = null;
        long j = 0;
        int i = 1;
        do {
            dby dbyVar2 = this.missedSubscription.get();
            if (dbyVar2 != null) {
                dbyVar2 = this.missedSubscription.getAndSet(null);
            }
            long j2 = this.missedRequested.get();
            if (j2 != 0) {
                j2 = this.missedRequested.getAndSet(0L);
            }
            long j3 = this.missedProduced.get();
            if (j3 != 0) {
                j3 = this.missedProduced.getAndSet(0L);
            }
            dby dbyVar3 = this.actual;
            if (this.cancelled) {
                if (dbyVar3 != null) {
                    dbyVar3.cancel();
                    this.actual = null;
                }
                if (dbyVar2 != null) {
                    dbyVar2.cancel();
                }
            } else {
                long j4 = this.requested;
                if (j4 != cjm.f20759b) {
                    j4 = BackpressureHelper.m9450a(j4, j2);
                    if (j4 != cjm.f20759b) {
                        j4 -= j3;
                        if (j4 < 0) {
                            SubscriptionHelper.reportMoreProduced(j4);
                            j4 = 0;
                        }
                    }
                    this.requested = j4;
                }
                if (dbyVar2 != null) {
                    if (dbyVar3 != null && this.cancelOnReplace) {
                        dbyVar3.cancel();
                    }
                    this.actual = dbyVar2;
                    if (j4 != 0) {
                        j = BackpressureHelper.m9450a(j, j4);
                        dbyVar = dbyVar2;
                    }
                } else if (!(dbyVar3 == null || j2 == 0)) {
                    j = BackpressureHelper.m9450a(j, j2);
                    dbyVar = dbyVar3;
                }
            }
            i = addAndGet(-i);
        } while (i != 0);
        if (j != 0) {
            dbyVar.request(j);
        }
    }

    public final boolean isUnbounded() {
        return this.unbounded;
    }

    public final boolean isCancelled() {
        return this.cancelled;
    }
}
