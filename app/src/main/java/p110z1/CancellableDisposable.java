package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.auz */
/* loaded from: classes3.dex */
public final class CancellableDisposable extends AtomicReference<Cancellable> implements Disposable {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableDisposable(Cancellable aulVar) {
        super(aulVar);
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return get() == null;
    }

    @Override // p110z1.Disposable
    public void dispose() {
        Cancellable andSet;
        if (get() != null && (andSet = getAndSet(null)) != null) {
            try {
                andSet.m9950a();
            } catch (Exception e) {
                Exceptions.m9983b(e);
                RxJavaPlugins.m9212a(e);
            }
        }
    }
}
