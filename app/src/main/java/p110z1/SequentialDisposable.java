package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.avf */
/* loaded from: classes3.dex */
public final class SequentialDisposable extends AtomicReference<Disposable> implements Disposable {
    private static final long serialVersionUID = -754898800686245608L;

    public SequentialDisposable() {
    }

    public SequentialDisposable(Disposable atrVar) {
        lazySet(atrVar);
    }

    public boolean update(Disposable atrVar) {
        return DisposableHelper.set(this, atrVar);
    }

    public boolean replace(Disposable atrVar) {
        return DisposableHelper.replace(this, atrVar);
    }

    @Override // p110z1.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }
}
