package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.bsf */
/* loaded from: classes3.dex */
public final class BooleanSubscription extends AtomicBoolean implements dby {
    private static final long serialVersionUID = -8127758972444290902L;

    @Override // p110z1.dby
    public void request(long j) {
        SubscriptionHelper.validate(j);
    }

    @Override // p110z1.dby
    public void cancel() {
        lazySet(true);
    }

    public boolean isCancelled() {
        return get();
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    public String toString() {
        return "BooleanSubscription(cancelled=" + get() + ")";
    }
}
