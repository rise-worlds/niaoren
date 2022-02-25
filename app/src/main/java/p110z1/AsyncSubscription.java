package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bsc */
/* loaded from: classes3.dex */
public final class AsyncSubscription extends AtomicLong implements Disposable, dby {
    private static final long serialVersionUID = 7028635084060361255L;
    final AtomicReference<dby> actual;
    final AtomicReference<Disposable> resource;

    public AsyncSubscription() {
        this.resource = new AtomicReference<>();
        this.actual = new AtomicReference<>();
    }

    public AsyncSubscription(Disposable atrVar) {
        this();
        this.resource.lazySet(atrVar);
    }

    @Override // p110z1.dby
    public void request(long j) {
        SubscriptionHelper.deferredRequest(this.actual, this, j);
    }

    @Override // p110z1.dby
    public void cancel() {
        dispose();
    }

    @Override // p110z1.Disposable
    public void dispose() {
        SubscriptionHelper.cancel(this.actual);
        DisposableHelper.dispose(this.resource);
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.actual.get() == SubscriptionHelper.CANCELLED;
    }

    public boolean setResource(Disposable atrVar) {
        return DisposableHelper.set(this.resource, atrVar);
    }

    public boolean replaceResource(Disposable atrVar) {
        return DisposableHelper.replace(this.resource, atrVar);
    }

    public void setSubscription(dby dbyVar) {
        SubscriptionHelper.deferredSetOnce(this.actual, this, dbyVar);
    }
}
