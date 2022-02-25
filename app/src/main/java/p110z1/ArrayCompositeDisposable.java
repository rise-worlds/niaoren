package p110z1;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: z1.auy */
/* loaded from: classes3.dex */
public final class ArrayCompositeDisposable extends AtomicReferenceArray<Disposable> implements Disposable {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeDisposable(int i) {
        super(i);
    }

    public boolean setResource(int i, Disposable atrVar) {
        Disposable atrVar2;
        do {
            atrVar2 = get(i);
            if (atrVar2 == DisposableHelper.DISPOSED) {
                atrVar.dispose();
                return false;
            }
        } while (!compareAndSet(i, atrVar2, atrVar));
        if (atrVar2 == null) {
            return true;
        }
        atrVar2.dispose();
        return true;
    }

    public Disposable replaceResource(int i, Disposable atrVar) {
        Disposable atrVar2;
        do {
            atrVar2 = get(i);
            if (atrVar2 == DisposableHelper.DISPOSED) {
                atrVar.dispose();
                return null;
            }
        } while (!compareAndSet(i, atrVar2, atrVar));
        return atrVar2;
    }

    @Override // p110z1.Disposable
    public void dispose() {
        Disposable andSet;
        if (get(0) != DisposableHelper.DISPOSED) {
            int length = length();
            for (int i = 0; i < length; i++) {
                if (!(get(i) == DisposableHelper.DISPOSED || (andSet = getAndSet(i, DisposableHelper.DISPOSED)) == DisposableHelper.DISPOSED || andSet == null)) {
                    andSet.dispose();
                }
            }
        }
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return get(0) == DisposableHelper.DISPOSED;
    }
}
