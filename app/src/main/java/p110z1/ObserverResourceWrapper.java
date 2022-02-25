package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bns */
/* loaded from: classes3.dex */
public final class ObserverResourceWrapper<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -8612022020200669122L;
    final Observer<? super T> downstream;
    final AtomicReference<Disposable> upstream = new AtomicReference<>();

    public ObserverResourceWrapper(Observer<? super T> assVar) {
        this.downstream = assVar;
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        if (DisposableHelper.setOnce(this.upstream, atrVar)) {
            this.downstream.onSubscribe(this);
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        this.downstream.onNext(t);
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        dispose();
        this.downstream.onError(th);
    }

    @Override // p110z1.Observer
    public void onComplete() {
        dispose();
        this.downstream.onComplete();
    }

    @Override // p110z1.Disposable
    public void dispose() {
        DisposableHelper.dispose(this.upstream);
        DisposableHelper.dispose(this);
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.upstream.get() == DisposableHelper.DISPOSED;
    }

    public void setResource(Disposable atrVar) {
        DisposableHelper.set(this, atrVar);
    }
}
