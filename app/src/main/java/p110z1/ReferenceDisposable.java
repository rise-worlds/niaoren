package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.atu */
/* loaded from: classes3.dex */
abstract class ReferenceDisposable<T> extends AtomicReference<T> implements Disposable {
    private static final long serialVersionUID = 6537757548749041217L;

    protected abstract void onDisposed(@AbstractC3889atm T t);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReferenceDisposable(T t) {
        super(ObjectHelper.m9873a((Object) t, "value is null"));
    }

    @Override // p110z1.Disposable
    public final void dispose() {
        T andSet;
        if (get() != null && (andSet = getAndSet(null)) != null) {
            onDisposed(andSet);
        }
    }

    @Override // p110z1.Disposable
    public final boolean isDisposed() {
        return get() == null;
    }
}
