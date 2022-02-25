package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.avb */
/* loaded from: classes3.dex */
public enum DisposableHelper implements Disposable {
    DISPOSED;

    @Override // p110z1.Disposable
    public void dispose() {
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return true;
    }

    public static boolean isDisposed(Disposable atrVar) {
        return atrVar == DISPOSED;
    }

    public static boolean set(AtomicReference<Disposable> atomicReference, Disposable atrVar) {
        Disposable atrVar2;
        do {
            atrVar2 = atomicReference.get();
            if (atrVar2 == DISPOSED) {
                if (atrVar == null) {
                    return false;
                }
                atrVar.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(atrVar2, atrVar));
        if (atrVar2 == null) {
            return true;
        }
        atrVar2.dispose();
        return true;
    }

    public static boolean setOnce(AtomicReference<Disposable> atomicReference, Disposable atrVar) {
        ObjectHelper.m9873a(atrVar, "d is null");
        if (atomicReference.compareAndSet(null, atrVar)) {
            return true;
        }
        atrVar.dispose();
        if (atomicReference.get() == DISPOSED) {
            return false;
        }
        reportDisposableSet();
        return false;
    }

    public static boolean replace(AtomicReference<Disposable> atomicReference, Disposable atrVar) {
        Disposable atrVar2;
        do {
            atrVar2 = atomicReference.get();
            if (atrVar2 == DISPOSED) {
                if (atrVar == null) {
                    return false;
                }
                atrVar.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(atrVar2, atrVar));
        return true;
    }

    public static boolean dispose(AtomicReference<Disposable> atomicReference) {
        Disposable andSet;
        Disposable atrVar = atomicReference.get();
        DisposableHelper avbVar = DISPOSED;
        if (atrVar == avbVar || (andSet = atomicReference.getAndSet(avbVar)) == avbVar) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.dispose();
        return true;
    }

    public static boolean validate(Disposable atrVar, Disposable atrVar2) {
        if (atrVar2 == null) {
            RxJavaPlugins.m9212a(new NullPointerException("next is null"));
            return false;
        } else if (atrVar == null) {
            return true;
        } else {
            atrVar2.dispose();
            reportDisposableSet();
            return false;
        }
    }

    public static void reportDisposableSet() {
        RxJavaPlugins.m9212a(new ProtocolViolationException("Disposable already set!"));
    }

    public static boolean trySet(AtomicReference<Disposable> atomicReference, Disposable atrVar) {
        if (atomicReference.compareAndSet(null, atrVar)) {
            return true;
        }
        if (atomicReference.get() != DISPOSED) {
            return false;
        }
        atrVar.dispose();
        return false;
    }
}
