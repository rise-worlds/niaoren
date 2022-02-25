package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awp */
/* loaded from: classes3.dex */
public final class InnerQueuedObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -5417183359794346637L;
    volatile boolean done;
    int fusionMode;
    final InnerQueuedObserverSupport<T> parent;
    final int prefetch;
    SimpleQueue<T> queue;

    public InnerQueuedObserver(InnerQueuedObserverSupport<T> awqVar, int i) {
        this.parent = awqVar;
        this.prefetch = i;
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        if (DisposableHelper.setOnce(this, atrVar)) {
            if (atrVar instanceof QueueDisposable) {
                QueueDisposable avrVar = (QueueDisposable) atrVar;
                int requestFusion = avrVar.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = avrVar;
                    this.done = true;
                    this.parent.innerComplete(this);
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = avrVar;
                    return;
                }
            }
            this.queue = QueueDrainHelper.m9377a(-this.prefetch);
        }
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        if (this.fusionMode == 0) {
            this.parent.innerNext(this, t);
        } else {
            this.parent.drain();
        }
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        this.parent.innerError(this, th);
    }

    @Override // p110z1.Observer
    public void onComplete() {
        this.parent.innerComplete(this);
    }

    @Override // p110z1.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public SimpleQueue<T> queue() {
        return this.queue;
    }

    public int fusionMode() {
        return this.fusionMode;
    }
}
