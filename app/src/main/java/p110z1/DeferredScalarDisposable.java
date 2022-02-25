package p110z1;

/* renamed from: z1.awi */
/* loaded from: classes3.dex */
public class DeferredScalarDisposable<T> extends BasicIntQueueDisposable<T> {
    static final int DISPOSED = 4;
    static final int FUSED_CONSUMED = 32;
    static final int FUSED_EMPTY = 8;
    static final int FUSED_READY = 16;
    static final int TERMINATED = 2;
    private static final long serialVersionUID = -5502432239815349361L;
    protected final Observer<? super T> downstream;
    protected T value;

    public DeferredScalarDisposable(Observer<? super T> assVar) {
        this.downstream = assVar;
    }

    @Override // p110z1.QueueFuseable
    public final int requestFusion(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    public final void complete(T t) {
        int i = get();
        if ((i & 54) == 0) {
            Observer<? super T> assVar = this.downstream;
            if (i == 8) {
                this.value = t;
                lazySet(16);
                assVar.onNext(null);
            } else {
                lazySet(2);
                assVar.onNext(t);
            }
            if (get() != 4) {
                assVar.onComplete();
            }
        }
    }

    public final void error(Throwable th) {
        if ((get() & 54) != 0) {
            RxJavaPlugins.m9212a(th);
            return;
        }
        lazySet(2);
        this.downstream.onError(th);
    }

    public final void complete() {
        if ((get() & 54) == 0) {
            lazySet(2);
            this.downstream.onComplete();
        }
    }

    @Override // p110z1.SimpleQueue
    @atn
    public final T poll() throws Exception {
        if (get() != 16) {
            return null;
        }
        T t = this.value;
        this.value = null;
        lazySet(32);
        return t;
    }

    @Override // p110z1.SimpleQueue
    public final boolean isEmpty() {
        return get() != 16;
    }

    @Override // p110z1.SimpleQueue
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    @Override // p110z1.Disposable
    public void dispose() {
        set(4);
        this.value = null;
    }

    public final boolean tryDispose() {
        return getAndSet(4) != 4;
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return get() == 4;
    }
}
