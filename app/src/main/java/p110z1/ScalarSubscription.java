package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bsi */
/* loaded from: classes3.dex */
public final class ScalarSubscription<T> extends AtomicInteger implements QueueSubscription<T> {
    static final int CANCELLED = 2;
    static final int NO_REQUEST = 0;
    static final int REQUESTED = 1;
    private static final long serialVersionUID = -3830916580126663321L;
    final Subscriber<? super T> subscriber;
    final T value;

    @Override // p110z1.QueueFuseable
    public int requestFusion(int i) {
        return i & 1;
    }

    public ScalarSubscription(Subscriber<? super T> dbxVar, T t) {
        this.subscriber = dbxVar;
        this.value = t;
    }

    @Override // p110z1.dby
    public void request(long j) {
        if (SubscriptionHelper.validate(j) && compareAndSet(0, 1)) {
            Subscriber<? super T> dbxVar = this.subscriber;
            dbxVar.onNext((T) this.value);
            if (get() != 2) {
                dbxVar.onComplete();
            }
        }
    }

    @Override // p110z1.dby
    public void cancel() {
        lazySet(2);
    }

    public boolean isCancelled() {
        return get() == 2;
    }

    @Override // p110z1.SimpleQueue
    public boolean offer(T t) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // p110z1.SimpleQueue
    public boolean offer(T t, T t2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // p110z1.SimpleQueue
    @atn
    public T poll() {
        if (get() != 0) {
            return null;
        }
        lazySet(1);
        return this.value;
    }

    @Override // p110z1.SimpleQueue
    public boolean isEmpty() {
        return get() != 0;
    }

    @Override // p110z1.SimpleQueue
    public void clear() {
        lazySet(1);
    }
}
