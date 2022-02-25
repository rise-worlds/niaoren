package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.brz */
/* loaded from: classes3.dex */
public class StrictSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, dby {
    private static final long serialVersionUID = -4945028590049415624L;
    volatile boolean done;
    final Subscriber<? super T> downstream;
    final AtomicThrowable error = new AtomicThrowable();
    final AtomicLong requested = new AtomicLong();
    final AtomicReference<dby> upstream = new AtomicReference<>();
    final AtomicBoolean once = new AtomicBoolean();

    public StrictSubscriber(Subscriber<? super T> dbxVar) {
        this.downstream = dbxVar;
    }

    @Override // p110z1.dby
    public void request(long j) {
        if (j <= 0) {
            cancel();
            onError(new IllegalArgumentException("§3.9 violated: positive request amount required but it was " + j));
            return;
        }
        SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
    }

    @Override // p110z1.dby
    public void cancel() {
        if (!this.done) {
            SubscriptionHelper.cancel(this.upstream);
        }
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (this.once.compareAndSet(false, true)) {
            this.downstream.onSubscribe(this);
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dbyVar);
            return;
        }
        dbyVar.cancel();
        cancel();
        onError(new IllegalStateException("§2.12 violated: onSubscribe must be called at most once"));
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        HalfSerializer.m9424a(this.downstream, t, this, this.error);
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        this.done = true;
        HalfSerializer.m9423a((Subscriber<?>) this.downstream, th, (AtomicInteger) this, this.error);
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        this.done = true;
        HalfSerializer.m9422a(this.downstream, this, this.error);
    }
}
