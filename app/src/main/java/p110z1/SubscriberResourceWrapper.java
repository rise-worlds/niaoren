package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bsa */
/* loaded from: classes3.dex */
public final class SubscriberResourceWrapper<T> extends AtomicReference<Disposable> implements FlowableSubscriber<T>, Disposable, dby {
    private static final long serialVersionUID = -8612022020200669122L;
    final Subscriber<? super T> downstream;
    final AtomicReference<dby> upstream = new AtomicReference<>();

    public SubscriberResourceWrapper(Subscriber<? super T> dbxVar) {
        this.downstream = dbxVar;
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.setOnce(this.upstream, dbyVar)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        this.downstream.onNext(t);
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        DisposableHelper.dispose(this);
        this.downstream.onError(th);
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        DisposableHelper.dispose(this);
        this.downstream.onComplete();
    }

    @Override // p110z1.dby
    public void request(long j) {
        if (SubscriptionHelper.validate(j)) {
            this.upstream.get().request(j);
        }
    }

    @Override // p110z1.Disposable
    public void dispose() {
        SubscriptionHelper.cancel(this.upstream);
        DisposableHelper.dispose(this);
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.upstream.get() == SubscriptionHelper.CANCELLED;
    }

    @Override // p110z1.dby
    public void cancel() {
        dispose();
    }

    public void setResource(Disposable atrVar) {
        DisposableHelper.set(this, atrVar);
    }
}
