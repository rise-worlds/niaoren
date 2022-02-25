package p110z1;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awf */
/* loaded from: classes3.dex */
public final class BlockingObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    public static final Object TERMINATED = new Object();
    private static final long serialVersionUID = -4875965440900746268L;
    final Queue<Object> queue;

    public BlockingObserver(Queue<Object> queue) {
        this.queue = queue;
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        DisposableHelper.setOnce(this, atrVar);
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        this.queue.offer(NotificationLite.next(t));
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        this.queue.offer(NotificationLite.error(th));
    }

    @Override // p110z1.Observer
    public void onComplete() {
        this.queue.offer(NotificationLite.complete());
    }

    @Override // p110z1.Disposable
    public void dispose() {
        if (DisposableHelper.dispose(this)) {
            this.queue.offer(TERMINATED);
        }
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }
}
